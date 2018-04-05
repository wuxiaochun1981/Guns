package com.stylefeng.guns.rest.modular.api.controller;

import com.stylefeng.guns.rest.modular.api.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.stylefeng.guns.rest.modular.api.controller
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/21 17:28
 * <p>
 * Company:
 * <p>
 *
 * @author wuxiaochun
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @ResponseBody
    @RequestMapping("/msg")
    public String deptAdd() {
        return msgService.getMsg(null);
    }


}
