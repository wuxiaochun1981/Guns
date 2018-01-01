package com.stylefeng.guns.generator.engine;


import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.generator.engine.base.GunsTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用的模板生成引擎
 *
 * @author fengshuonan
 * @date 2017-05-09 20:32
 */
public class SimpleTemplateEngine extends GunsTemplateEngine {

    protected final static Logger logger = LoggerFactory.getLogger(SimpleTemplateEngine.class);

    private String tempPath = "guns-generator/src/resources/";

    @Override
    protected void generatePageEditHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(tempPath + super.getContextConfig().getTemplatePrefixPath() + "/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    @Override
    protected void generatePageAddHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(tempPath + super.getContextConfig().getTemplatePrefixPath() + "/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    @Override
    protected void generatePageInfoJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(tempPath + super.getContextConfig().getTemplatePrefixPath() + "/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    @Override
    protected void generatePageJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(tempPath + super.getContextConfig().getTemplatePrefixPath() + "/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    @Override
    protected void generatePageHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPagePathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
          generateFile(tempPath + super.getContextConfig().getTemplatePrefixPath() + "/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    @Override
    protected void generateController() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getControllerConfig().getControllerPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        logger.info("模板路径：" + super.getContextConfig().getTemplatePrefixPath() + "/Controller.java.btl");
        generateFile(tempPath  + super.getContextConfig().getTemplatePrefixPath() + "/Controller.java.btl", controllerPath);
        System.out.println("生成控制器成功!");
    }

    @Override
    protected void generateSqls() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + super.sqlConfig.getSqlPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile(tempPath + super.getContextConfig().getTemplatePrefixPath() + "/menu_sql.sql.btl", path);
        System.out.println("生成sql成功!");
    }

    public static void main(String[] args) {
        SimpleTemplateEngine simpleTemplateEngine = new SimpleTemplateEngine();
        simpleTemplateEngine.generateController();
    }
}
