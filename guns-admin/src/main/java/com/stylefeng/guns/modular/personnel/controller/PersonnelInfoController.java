package com.stylefeng.guns.modular.personnel.controller;

import com.stylefeng.guns.GunsApplication;
import com.stylefeng.guns.core.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.PersonnelInfo;
import com.stylefeng.guns.modular.personnel.service.IPersonnelInfoService;

/**
 * infoManager控制器
 *
 * @author fengshuonan
 * @Date 2017-12-29 14:02:07
 */
@Controller
@RequestMapping("/personnelInfo")
public class PersonnelInfoController extends BaseController {

    protected final static Logger logger = LoggerFactory.getLogger(PersonnelInfoController.class);

    private String PREFIX = "/personnel/personnelInfo/";

    @Autowired
    private IPersonnelInfoService personnelInfoService;

    /**
     * 跳转到infoManager首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "personnelInfo.html";
    }

    /**
     * 跳转到添加infoManager
     */
    @RequestMapping("/personnelInfo_add")
    public String personnelInfoAdd() {
        return PREFIX + "personnelInfo_add.html";
    }

    /**
     * 跳转到修改infoManager
     */
    @RequestMapping("/personnelInfo_update/{personnelInfoId}")
    public String personnelInfoUpdate(@PathVariable Integer personnelInfoId, Model model) {
        PersonnelInfo personnelInfo = personnelInfoService.selectById(personnelInfoId);
        model.addAttribute("item",personnelInfo);
        LogObjectHolder.me().set(personnelInfo);
        return PREFIX + "personnelInfo_edit.html";
    }

    /**
     * 获取infoManager列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return personnelInfoService.selectList(null);
    }

    /**
     * 新增infoManager
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PersonnelInfo personnelInfo) {
        logger.info("添加数据");
        personnelInfoService.insert(personnelInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除infoManager
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer personnelInfoId) {
        personnelInfoService.deleteById(personnelInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改infoManager
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PersonnelInfo personnelInfo) {
        personnelInfoService.updateById(personnelInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * infoManager详情
     */
    @RequestMapping(value = "/detail/{personnelInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("personnelInfoId") Integer personnelInfoId) {
        return personnelInfoService.selectById(personnelInfoId);
    }
}
