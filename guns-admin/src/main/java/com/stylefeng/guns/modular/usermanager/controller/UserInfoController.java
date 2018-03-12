package com.stylefeng.guns.modular.usermanager.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.annotion.BussinessLog;
import com.stylefeng.guns.common.constant.dictmap.RoleDict;
import com.stylefeng.guns.common.constant.dictmap.UserInfoDict;
import com.stylefeng.guns.common.persistence.model.UserInfo;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.usermanager.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 接口权限管理控制器
 *
 * @author wuxiaochun
 * @Date 2018-02-28 15:03:40
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(UserInfoController.class);

    private String PREFIX = "/usermanager/userInfo/";

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 跳转到接口权限管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userInfo.html";
    }

    /**
     * 跳转到添加接口权限管理
     */
    @RequestMapping("/userInfo_add")
    public String userInfoAdd() {
        return PREFIX + "userInfo_add.html";
    }

    /**
     * 跳转到修改接口权限管理
     */
    @RequestMapping("/userInfo_update/{userInfoId}")
    public String userInfoUpdate(@PathVariable Integer userInfoId, Model model) {
        UserInfo userInfo = userInfoService.selectById(userInfoId);
        model.addAttribute("item",userInfo);
        LogObjectHolder.me().set(userInfo);
        return PREFIX + "userInfo_edit.html";
    }

    /**
     * 获取接口权限管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        System.out.println("condition：" + condition);
        EntityWrapper entityWrapper = new EntityWrapper<UserInfo>();
        entityWrapper.ne("status",2);
        if(StringUtils.isNotBlank(condition)){
            entityWrapper.andNew().like("user_name",condition).or().like("phone",condition).or().like("appid",condition);
        }
        return userInfoService.selectList(entityWrapper);
    }

    /**
     * 新增接口权限管理
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加用户接口权限", key = "id", dict = UserInfoDict.class)
    @ResponseBody
    public Object add(UserInfo userInfo) {
        userInfo.setuserKey(UUID.randomUUID().toString());
        userInfo.setCreateUser(ShiroKit.getUser().getAccount());
        userInfo.setCreateTime(new Date());
        userInfo.setAccessCount(0);
        userInfo.setFailCount(0);
        userInfoService.insert(userInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除接口权限管理
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除用户接口权限", key = "id", dict = UserInfoDict.class)
    @ResponseBody
    public Object delete(@RequestParam Integer userInfoId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoId);
        userInfo.setStatus(2);
        userInfoService.updateById(userInfo);
//        userInfoService.deleteById(userInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改接口权限管理
     */
    @RequestMapping(value = "/update")
    @BussinessLog(value = "编辑用户接口权限", key = "id", dict = UserInfoDict.class)
    @ResponseBody
    public Object update(UserInfo userInfo) {
        userInfoService.updateById(userInfo);
        return SUCCESS_TIP;
    }

    /**
     * 接口权限管理详情
     */
    @RequestMapping(value = "/detail/{userInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("userInfoId") Integer userInfoId) {
        return userInfoService.selectById(userInfoId);
    }
    /**
     * 验证appId是否重复
     */
    @RequestMapping(value = "/checkDuplicate", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object checkDuplicate(String appid,Long id){
        EntityWrapper entityWrapper = new EntityWrapper<UserInfo>();
        entityWrapper.eq("appid",appid).ne("status",2);
        if(id !=null){
            entityWrapper.ne("id",id);
        }
        List<UserInfo> list = userInfoService.selectList(entityWrapper);
        if(list != null && list.size()>0){
            return getResultInfo(false);
        }else{
            return getResultInfo(true);
        }
    }
}
