package com.stylefeng.guns.rest.config;

/**
 * com.stylefeng.guns.rest.config
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/5 13:46
 * <p>
 * Company:
 * <p>
 *
 * @author wuxiaochun
 * @version 1.0.0
 */
public class Constant {


    /**
     * 缓存
     */
    public interface Cache {

        /**
         * 系统级缓存
         */
        String constant = "CONSTANT";

        /**
         * token缓存
         */
        String tokenCache = "tokenCache";
    }

    /**
     * 请求类型
     */
    public interface Query {
        /** 多借贷 */
        String duotou = "duotou";
        /** 金融逾期 */
        String jinrong = "jinrong";
        /** 犯罪信息 */
        String fanzui = "fanzui";
        /** 高法失信 */
        String gaofa = "gaofa";
    }

    /**
     * 返回结果类型
     */
    public interface ResponseCode {
        /** 成功 */
        String ok = "200";
        /** 余额不足 */
        String insufficientBalance = "403";
        /** 尚未授权 */
        String unauthorized = "405";
        /** 用户不存在 */
        String nonExistent = "408";
        /** 校验不通过/请求失败 */
        String validationNot = "400";
        /** 内部错误 */
        String systemError = "500";
        /** token过期 */
        String tokenTimeOut = "700";
    }

    /**
     * 返回结果类型
     */
    public interface ResponseMsg {
        /** 成功 */
        String ok = " 成功";
        /** 余额不足 */
        String insufficientBalance = "余额不足";
        /** 尚未授权 */
        String unauthorized = "尚未授权";
        /** 用户不存在 */
        String nonExistent = "用户不存在";
        /** 校验不通过/请求失败 */
        String validationNot = "校验不通过/请求失败";
        /** 内部错误 */
        String systemError = "系统错误请联系管理员";
        /** token过期 */
        String tokenTimeOut = "token过期";

    }

}
