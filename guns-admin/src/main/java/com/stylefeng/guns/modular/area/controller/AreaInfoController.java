package com.stylefeng.guns.modular.area.controller;

import com.stylefeng.guns.modular.area.service.IAreaInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * com.stylefeng.guns.modular.area.controller
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/1/12 16:30
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
@Controller
@RequestMapping("/areaInfo")
public class AreaInfoController {

    protected final static Logger logger = LoggerFactory.getLogger(AreaInfoController.class);

    private String PREFIX = "/area/areaInfo/";

    @Autowired
    private IAreaInfoService areaInfoService;

    /**
     * 获取省信息列表
     */
    @RequestMapping(value = "/province")
    @ResponseBody
    public Object provinceList() throws IOException {
        return areaInfoService.getProvince();
    }
}
