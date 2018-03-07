package com.stylefeng.guns.rest.modular.api.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.rest.common.persistence.model.UserInfo;

import java.util.Map;

/**
 * com.stylefeng.guns.rest.modular.api.service
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/2 10:29
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
public interface IUserApiService extends IService<UserInfo> {


    /**
     * 个人查询服务
     * @param params
     * @return
     */
    public JSONObject person(Map<String, Object> params);

    /**企业查询服务
     *
     * @param params
     * @return
     */
    public JSONObject company(Map<String, Object> params);


}
