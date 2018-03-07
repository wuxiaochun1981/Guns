package com.stylefeng.guns.rest.modular.api.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.stylefeng.guns.rest.config.Constant;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.api.service.IUserApiService;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.sun.deploy.net.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * com.stylefeng.guns.modular.api.controller
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/2/28 14:22
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/api/query")
public class UserApiController {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(UserApiController.class);

    @Autowired
    private IUserApiService iUserApiService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;


    @RequestMapping(value = "/person", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String person(String idCode, String phoneNumber, String realName, String serviceType, String timestamp, HttpServletRequest request){
        String  responseStr = "";
        try{
            String appId = getAppId(request);
            logger.info("个人查询: appId=" + appId + " idCode=" + idCode + " phoneNumber=" + phoneNumber + " realName=" + realName + " serviceType=" + serviceType + " timestamp=" + timestamp);

            if(StringUtils.isBlank(appId)){
                responseStr = getResponse(Constant.ResponseCode.tokenTimeOut,Constant.ResponseMsg.tokenTimeOut);
                return responseStr;
            }

            if(!StringUtils.equals(serviceType,Constant.Query.fanzui)){
                responseStr = getResponse(Constant.ResponseCode.validationNot,"serviceType参数不正确");
                return responseStr;
            }
            // 请求参数(client里面会自动加密,所以这里请使用明文)、
            Map<String, Object> params = new HashMap();
            params.put("appId",appId);
            params.put("idCode",idCode);
            params.put("phoneNumber",phoneNumber);
            params.put("realName",realName);
            params.put("serviceType",serviceType);
            params.put("timestamp",timestamp);
            //验证参数
            if(checkParams(params)){
                JSONObject jsonObject = iUserApiService.person(params);
                responseStr = jsonObject.toJSONString();
            }else{
                responseStr = getResponse(Constant.ResponseCode.validationNot,Constant.ResponseMsg.validationNot);
            }
        }catch (Exception e){
            logger.error("个人查询服务出错",e);
            responseStr = getResponse(Constant.ResponseCode.systemError,Constant.ResponseMsg.systemError);
        }
        return responseStr;
    }

    @RequestMapping(value = "/company", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String company(String entInfo,String serviceType,String timestamp,HttpServletRequest request){
        String  responseStr = "";

        try{
            String appId = getAppId(request);
            logger.info("个人查询: appId=" + appId + " entInfo=" + entInfo + " serviceType=" + serviceType + " timestamp=" + timestamp);

            if(StringUtils.isBlank(appId)){
                responseStr = getResponse(Constant.ResponseCode.tokenTimeOut,Constant.ResponseMsg.tokenTimeOut);
                return responseStr;
            }

            if(!StringUtils.equals(serviceType,Constant.Query.fanzui)){
                responseStr = getResponse(Constant.ResponseCode.validationNot,"serviceType参数不正确");
                return responseStr;
            }
            // 请求参数(client里面会自动加密,所以这里请使用明文)、
            Map<String, Object> params = new HashMap();
            params.put("appId",appId);
            params.put("entInfo",entInfo);
            params.put("serviceType",serviceType);
            params.put("timestamp",timestamp);
            //验证参数
            if(checkParams(params)){
                JSONObject jsonObject = iUserApiService.company(params);
                responseStr = jsonObject.toJSONString();
            }else{
                responseStr = getResponse(Constant.ResponseCode.validationNot,Constant.ResponseMsg.validationNot);
            }
        }catch (Exception e){
            logger.error("企业查询服务出错");
            responseStr = getResponse(Constant.ResponseCode.systemError,Constant.ResponseMsg.systemError);
        }
        return responseStr;
    }



    /**
     * 检测参数值
     * @param params
     * @return
     */
    private Boolean checkParams(Map<String, Object> params) {
        Boolean isVerified = true;
        for (String key : params.keySet()) {
            if (Objects.isNull(params.get(key)) || StringUtils.isBlank(params.get(key).toString())) {
                isVerified = false;
                String msg = "参数" + key + " 不能为空！";
                logger.warn(msg);
            }
        }
        return isVerified;
    }

    /**
     * 通过token 获去用户appId
     * @param request
     * @return
     */
    private String getAppId(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        String appid = jwtTokenUtil.getUsernameFromToken(token);
        return appid;
    }

    /**
     * 返回结果
     * @param code
     * @param msg
     * @param jsonObject
     * @return
     */
    private String getResponse(String code, String msg, JSONObject jsonObject){
        JSONObject result = new JSONObject();
        result.put("code",code);
        result.put("msg",msg);
        if(jsonObject==null){
            result.put("result",new JSONObject());
        }else{
            result.put("result",jsonObject);
        }
        return result.toJSONString();
    }

    /**
     * 返回结果
     * @param code
     * @param msg
     * @return
     */
    private String getResponse(String code, String msg){
        return getResponse(code,msg,null);
    }

}
