package com.stylefeng.guns.modular.area.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * com.stylefeng.guns.modular.area.service
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/1/12 16:33
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
public interface IAreaInfoService {

    /**
     * 获取省份数据
     * @return
     */
    public JSONArray getProvince() throws IOException;

    /**
     * 通过省获取城市信息
     * @param province
     * @return
     * @throws IOException
     */
    public JSONArray getCity(String province) throws IOException;


    /**
     * 获取 区县信息
     * @param province
     * @param province
     * @return
     * @throws IOException
     */
    public JSONArray getCounty(String province,String city) throws IOException;
}
