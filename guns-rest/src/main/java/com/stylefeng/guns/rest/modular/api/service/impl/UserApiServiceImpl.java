package com.stylefeng.guns.rest.modular.api.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.UserInfoMapper;
import com.stylefeng.guns.rest.common.persistence.model.QueryLogInfo;
import com.stylefeng.guns.rest.common.persistence.model.UserInfo;
import com.stylefeng.guns.rest.common.util.SecurityUtil;
import com.stylefeng.guns.rest.config.Constant;
import com.stylefeng.guns.rest.modular.api.service.IUserApiService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * com.stylefeng.guns.rest.modular.api.service.impl
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/2 10:33
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
@Service
public class UserApiServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserApiService{

    /** 日志 */
    private static final Log logger = LogFactory.getLog(UserApiServiceImpl.class);

    @Value("${cycredit.server}")
    private String server;

    @Value("${cycredit.appKey}")
    private String appKey;

    @Value("${cycredit.appSecret}")
    private String appSecret;

    @Value("${cycredit.defaultTimeOut}")
    private Integer defaultTimeOut=10000;


    /**
     * 生成digitalSignature 数据
     * @param parameter
     * @return
     */
    public String getDigitalSignature(Map<String, Object> parameter) {
        StringBuilder digitalSignature = new StringBuilder(200);
        digitalSignature.append("appSecret");
        //零时存放排序的key
        List<String> keyList = new ArrayList();
        //set转list
        CollectionUtils.addAll(keyList,parameter.keySet().iterator());
        //排序
        Collections.sort(keyList);
        //生成digitalSignature
        for (String key : keyList) {
            Object value = parameter.get(key);
            digitalSignature.append(key).append(value);
        }
        return  MD5Util.encrypt(digitalSignature.toString());
    }

    /**
     * 获取时间
     * @return
     */
    private static String subTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    @Override
    public JSONObject person(Map<String, Object> params) {
        JSONObject result = new JSONObject();

        String appId = (String)params.get("appId");
        String ip = (String)params.get("ip");
        params.remove("appId");
        params.remove("ip");

        JSONObject jSONObject = queryInfo(server + "/query/person",params);
//        JSONObject jSONObject = new JSONObject();
//        jSONObject.put("code",200);
//        jSONObject.put("trade_no","234234234");

        //状态码
        String code = jSONObject.getString("code");
        //订单号
        String tradeNo = jSONObject.getString("trade_no");
        Map<String, Object> paramsTemp = new HashMap<>();
        paramsTemp.put("appid",appId);
        paramsTemp.put("status",0);
        if(StringUtils.equals(Constant.ResponseCode.ok,code)){
            paramsTemp.put("accessCount",1);
            this.baseMapper.updateQueryCount(paramsTemp);
            saveQueryLog(ip,appId,tradeNo,0,params);
        }else{
            paramsTemp.put("failCount",1);
            this.baseMapper.updateQueryCount(paramsTemp);
            saveQueryLog(ip,appId,tradeNo,1,params);
        }
        result.put("code",jSONObject.getString("code"));
        result.put("result",jSONObject.getString("result"));
        return result;
    }
;
    @Override
    public JSONObject company(Map<String, Object> params) {
        return null;
    }

    /**
     *  查询信息
     * @param url url地址
     * @param params
     * @return
     */
    public JSONObject queryInfo(String url,Map<String, Object> params){
        params.put("appKey",appKey);
        String digitalSignature = getDigitalSignature(params);
        params.put("digitalSignature", digitalSignature);
        String result = HttpUtil.get(url, params,defaultTimeOut);
        if(StringUtils.isNotBlank(result)){
            return JSON.parseObject(result);
        }
        return null;
    }

    /**
     * 保存log日志
     * @param ip
     * @param appid
     * @param status
     * @param tradeNo
     * @param params
     * @return
     */
    private boolean saveQueryLog(String ip,String appid,String tradeNo,Integer status,Map<String, Object> params){
        boolean result = false;
        QueryLogInfo  queryLogInfo = new  QueryLogInfo();
        queryLogInfo.setAppid(appid);
        queryLogInfo.setIp(ip);
        queryLogInfo.setStatus(status);
        queryLogInfo.setTradeNo(tradeNo);
        queryLogInfo.setCreateTime(new Date());
        queryLogInfo.setParams(SecurityUtil.encrypt(JSON.toJSONString(params)));
        result = queryLogInfo.insert();
        return result;
    }

    public static void main(String[] args) {
//        UserApiServiceImpl usi = new UserApiServiceImpl();
//
//        Map<String,Object> testMap=new HashMap<>();
//
//        testMap.put("bbb","1");
//        testMap.put("aaaa","2");
//        testMap.put("cccc","3");
//        testMap.put("dddd","4");
//        System.out.println( usi.getDigitalSignature(testMap));

    }
}
