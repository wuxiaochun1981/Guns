package com.stylefeng.guns.rest.common.util;

/**
 * com.stylefeng.guns.rest.common.util
 *
 * <p>
 * Copyright: Copyright (c) 2019-05-21 15:25
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
public class TimeUtil {

    /**
     * 获得给定时间的中文表示,如上午,早上,晚上
     *
     * @param cal   给定日期
     * @param hours 给定时间分割,如{5,9,11,13,18,23}
     * @param names 对于相应的时间,返回相应的名称,如{"深夜","早上","上午","中午","下午","晚上"}
     * @return 给定时间对应的中文名称,超过最后一个,也返回第一个名称
     */
    public static String getTimeName(java.util.Calendar cal, int[] hours, String[] names) {
        int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        for (int i = 0; i < hours.length; i++) {
            if (hour < hours[i]){
                return names[i];
            }
        }
        //比最后一个还大,返回第一个名称
        return names[0];
    }


    /**
     * @return hours={5,9,11,13,18,23} names={"深夜","早上","上午","中午","下午","晚上"}所对应的当前时间
     *
     */
    public static String getTimeName() {
        int[] hours = {5, 9, 11, 13, 18, 23};
        String[] names = {"深夜", "早上", "上午", "中午", "下午", "晚上"};
        return getTimeName(java.util.Calendar.getInstance(), hours, names);
    }


}
