package com.stylefeng.guns.modular.userManager.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.UserInfo;
import com.stylefeng.guns.modular.userManager.service.IUserInfoService;

import java.util.Date;
import java.util.UUID;

/**
 * 用户权限管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-03 18:18:46
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    private String PREFIX = "/userManager/userInfo/";

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 跳转到用户权限管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userInfo.html";
    }

    /**
     * 跳转到添加用户权限管理
     */
    @RequestMapping("/userInfo_add")
    public String userInfoAdd() {
        return PREFIX + "userInfo_add.html";
    }

    /**
     * 跳转到修改用户权限管理
     */
    @RequestMapping("/userInfo_update/{userInfoId}")
    public String userInfoUpdate(@PathVariable Integer userInfoId, Model model) {
        UserInfo userInfo = userInfoService.selectById(userInfoId);
        model.addAttribute("item",userInfo);
        LogObjectHolder.me().set(userInfo);
        return PREFIX + "userInfo_edit.html";
    }

    /**
     * 获取用户权限管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return userInfoService.selectList(null);
    }

    /**
     * 新增用户权限管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserInfo userInfo) {
        userInfo.setTokenKey(UUID.randomUUID().toString());
        userInfo.setCreateUser(ShiroKit.getUser().getAccount());
        userInfo.setCreateTime(new Date());
        userInfo.setAccessCount(0);
        userInfo.setFailCount(0);
        userInfoService.insert(userInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除用户权限管理
     */
    @RequestMapping(value = "/delete")
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
     * 修改用户权限管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserInfo userInfo) {
        userInfoService.updateById(userInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 用户权限管理详情
     */
    @RequestMapping(value = "/detail/{userInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("userInfoId") Integer userInfoId) {
        return userInfoService.selectById(userInfoId);
    }
}
