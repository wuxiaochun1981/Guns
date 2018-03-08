package com.stylefeng.guns.modular.queryLog.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.QueryLogInfo;
import com.stylefeng.guns.modular.queryLog.service.IQueryLogInfoService;

import java.util.List;

/**
 * 请求日志控制器
 *
 * @author fengshuonan
 * @Date 2018-03-08 16:39:00
 */
@Controller
@RequestMapping("/queryLogInfo")
public class QueryLogInfoController extends BaseController {

    private String PREFIX = "/queryLog/queryLogInfo/";

    @Autowired
    private IQueryLogInfoService queryLogInfoService;

    /**
     * 跳转到请求日志首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "queryLogInfo.html";
    }

    /**
     * 跳转到添加请求日志
     */
    @RequestMapping("/queryLogInfo_add")
    public String queryLogInfoAdd() {
        return PREFIX + "queryLogInfo_add.html";
    }

    /**
     * 跳转到修改请求日志
     */
    @RequestMapping("/queryLogInfo_update/{queryLogInfoId}")
    public String queryLogInfoUpdate(@PathVariable Integer queryLogInfoId, Model model) {
        QueryLogInfo queryLogInfo = queryLogInfoService.selectById(queryLogInfoId);
        model.addAttribute("item",queryLogInfo);
        LogObjectHolder.me().set(queryLogInfo);
        return PREFIX + "queryLogInfo_edit.html";
    }

    /**
     * 获取请求日志列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<QueryLogInfo> list = queryLogInfoService.selectList(null);
        for(QueryLogInfo queryLogInfo:list){
            if(StringUtils.isNotBlank(queryLogInfo.getParams())){
                queryLogInfo.setParams(SecurityUtil.decrypt(queryLogInfo.getParams()));
            }
        }
        return list;
    }

    /**
     * 新增请求日志
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(QueryLogInfo queryLogInfo) {
        queryLogInfoService.insert(queryLogInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除请求日志
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer queryLogInfoId) {
        queryLogInfoService.deleteById(queryLogInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改请求日志
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(QueryLogInfo queryLogInfo) {
        queryLogInfoService.updateById(queryLogInfo);
        return SUCCESS_TIP;
    }

    /**
     * 请求日志详情
     */
    @RequestMapping(value = "/detail/{queryLogInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("queryLogInfoId") Integer queryLogInfoId) {
        return queryLogInfoService.selectById(queryLogInfoId);
    }
}
