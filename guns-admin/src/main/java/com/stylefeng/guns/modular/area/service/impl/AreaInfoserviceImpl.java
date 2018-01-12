package com.stylefeng.guns.modular.area.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.modular.area.service.IAreaInfoService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.stylefeng.guns.modular.area.service.impl
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/1/12 16:34
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
@Service
public class AreaInfoserviceImpl implements IAreaInfoService {

    protected final static Logger logger = LoggerFactory.getLogger(AreaInfoserviceImpl.class);

    /**
     * 省信息
     */
    private static JSONArray provinceList = new JSONArray();

    /**
     * 城市信息
     */
    private static Map<String,JSONArray> cityMap = new HashMap<>();

    /**
     * 区县信息
     */
    private static Map<String,JSONArray> countyMap = new HashMap<>();

    private static byte[] lock = new byte[0];

    @Override
    public JSONArray getProvince() throws IOException {
        if( provinceList.size()==0){
            synchronized (lock){
                if(provinceList.size()==0){
                    loadProvince();
                }
            }
        }
        return provinceList;
    }

    @Override
    public JSONArray getCity(String province) throws IOException {
        JSONArray cityArray = cityMap.get(province);
        if(cityArray==null){
            synchronized (lock){
                cityArray = cityMap.get(province);
                if(cityArray==null){
                    cityArray = loadCity(province);
                    cityMap.put(province,cityArray);
                }
            }
        }
        return cityArray;
    }

    @Override
    public JSONArray getCounty(String province,String city) throws IOException {
        String key = province + "_" + city;
        JSONArray countyArray = countyMap.get(key);
        if(countyArray==null){
            synchronized (lock){
                countyArray = countyMap.get(key);
                if(countyArray==null){
                    countyArray = loadCounty(province,city);
                    countyMap.put(key,countyArray);
                }
            }
        }
        return countyArray;
    }

    private void loadProvince() throws IOException {
        String str = FileUtils.readFileToString(new File("F:\\git\\Guns\\guns-admin\\src\\main\\resources\\city.json"),"UTF-8");
        JSONArray  arr =  JSON.parseArray(str);
        for (int i = 0; i < arr.size(); i++) {
            JSONObject provinceObj =  arr.getJSONObject(i);
            String provinceName = provinceObj.getString("name");
            JSONObject province = new JSONObject();
            province.put("id",provinceName);
            province.put("name",provinceName);
            provinceList.add(province);
        }
    }

    private JSONArray loadCity(String provinceName) throws IOException {
        String str = FileUtils.readFileToString(new File("F:\\git\\Guns\\guns-admin\\src\\main\\resources\\city.json"),"UTF-8");
        JSONArray cityArray = new JSONArray();
        JSONArray  arr =  JSON.parseArray(str);
        for (int i = 0; i < arr.size(); i++) {
            JSONObject provinceObj =  arr.getJSONObject(i);
            String provinceNameTemp = provinceObj.getString("name");
            if(StringUtils.equals(provinceNameTemp,provinceName)){
                JSONArray cityList = provinceObj.getJSONArray("city");
                for (int j = 0; j < cityList.size(); j++) {
                    JSONObject cityObj =  cityList.getJSONObject(j);
                    String cityName = cityObj.getString("name");
                    JSONObject city = new JSONObject();
                    city.put("id",cityName);
                    city.put("name",cityName);
                    cityArray.add(city);
                }
            }
        }
        return cityArray;
    }

    private JSONArray loadCounty(String provinceName,String cityName) throws IOException {
        String str = FileUtils.readFileToString(new File("F:\\git\\Guns\\guns-admin\\src\\main\\resources\\city.json"),"UTF-8");
        JSONArray countyArray = new JSONArray();
        JSONArray  arr =  JSON.parseArray(str);
        for (int i = 0; i < arr.size(); i++) {
            JSONObject provinceObj =  arr.getJSONObject(i);
            String provinceNameTemp = provinceObj.getString("name");
            if(StringUtils.equals(provinceNameTemp,provinceName)){
                JSONArray cityList = provinceObj.getJSONArray("city");
                for (int j = 0; j < cityList.size(); j++) {
                    JSONObject cityObj = cityList.getJSONObject(j);
                    String cityNameTemp = cityObj.getString("name");
                    if(StringUtils.equals(cityNameTemp,cityName)){
                        JSONArray countyList = cityObj.getJSONArray("area");
                        for (int k = 0; k < countyList.size(); k++) {
                            String countyName = countyList.getString(k);
                            JSONObject county = new JSONObject();
                            county.put("id",countyName);
                            county.put("name",countyName);
                            countyArray.add(county);
                        }
                    }
                }
            }
        }
        return countyArray;
    }


}
