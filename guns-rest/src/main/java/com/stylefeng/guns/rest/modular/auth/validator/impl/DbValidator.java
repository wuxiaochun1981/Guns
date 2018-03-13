package com.stylefeng.guns.rest.modular.auth.validator.impl;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.dao.UserInfoMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserInfo;
import com.stylefeng.guns.rest.common.util.MD5;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 账号密码验证
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class DbValidator implements IReqValidator {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(DbValidator.class);


    @Autowired
    UserInfoMapper userInfoMapper;

    @Value("${cycredit.errorAmount}")
    private Integer errorAmount;

    @Override
    public boolean validate(Credence credence) {
        //参数不能为空
        if(StringUtils.isBlank(credence.getCredenceCode()) || StringUtils.isBlank(credence.getCredenceName()) || StringUtils.isBlank(credence.getCredenceTime())){
            return false;
        }

        //验证时间字符串是否合法
        if(!validate(credence.getCredenceTime())){
            return false;
        }

        List<UserInfo> userInfos = userInfoMapper.selectList(new EntityWrapper<UserInfo>().eq("appid", credence.getCredenceName()).eq("status",0));
        if (userInfos != null && userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            String digitalSignature = getDigitalSignature(credence.getCredenceName(),userInfo.getUserKey(),credence.getCredenceTime());
            if(StringUtils.equalsIgnoreCase(digitalSignature,credence.getCredenceCode())){
                return true;
            }
        }
        return false;
    }

    /**
     * 验证当前时间和服务器时间相差是否合理
     * @param timestampStr
     * @return
     */
    public boolean validate(String timestampStr) {
        try{
            Date date = new Date(Long.valueOf(timestampStr));
            long cont = DateUtil.between(new Date(),date, DateUnit.MINUTE);
            if(errorAmount>cont){
                return true;
            }
            return false;
        }catch (Exception e){
            logger.error("验证用户的请求的时间出错",e);
            return false;
        }
    }

    /**
     * 验证用户数据是否合法
     * @param appid appid
     * @param userKey 用户userkey
     * @param timestamp 时间
     */
    private String getDigitalSignature(String appid,String userKey,String timestamp){
        StringBuffer digitalSignature = new StringBuffer();
        digitalSignature.append(appid).append(userKey).append(timestamp);
        return MD5.getMD5(digitalSignature.toString());
    }

    public static void main(String[] args) {
        //1520691015242
        //1520691036899
        Date date = new Date(1520691015242l);

        DateUtil.between(date,new Date(), DateUnit.MINUTE);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(DateUtil.between(date,new Date(), DateUnit.MINUTE));
        System.out.println(System.currentTimeMillis());
    }
}
