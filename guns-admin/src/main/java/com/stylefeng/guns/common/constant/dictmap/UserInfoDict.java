package com.stylefeng.guns.common.constant.dictmap;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.stylefeng.guns.common.constant.dictmap.base.AbstractDictMap;

import java.util.Date;

/**
 * com.stylefeng.guns.common.constant.dictmap
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/12 16:15
 * <p>
 * Company:
 * <p>
 *
 * @author wuxiaochun
 * @version 1.0.0
 */
public class UserInfoDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id","编号");
        put("userName","用户名");
        put("phone","手机号");
        put("appid","用户ppid");
        put("userKey","用户key");
        put("accessCount","成功访问数量");
        put("failCount","失败访问次数");
        put("status","状态");
        put("createUser","创建人");
        put("createTime","创建时间");

    }


    @Override
    protected void initBeWrapped() {
    }
}
