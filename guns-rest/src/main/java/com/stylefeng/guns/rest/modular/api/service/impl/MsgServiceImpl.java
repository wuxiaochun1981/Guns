package com.stylefeng.guns.rest.modular.api.service.impl;

import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import com.stylefeng.guns.rest.common.util.LunarCalendar;
import com.stylefeng.guns.rest.common.util.SolarTerm;
import com.stylefeng.guns.rest.modular.api.service.MsgService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * com.stylefeng.guns.rest.modular.api.service.impl
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/21 17:32
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wuxiaochun@jd.com
 * @version 1.0.0
 */
public class MsgServiceImpl implements MsgService {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(MsgServiceImpl.class);

    @Autowired
    private SolarTerm st;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    private String defaultCity = "北京";

    @Override
    public String getMsg(String city) {
        if(StringUtils.isBlank(city)){
            city = defaultCity;
        }
        StringBuilder msg = new StringBuilder();
        msg.append(sdf.format(new Date())).append(" ");
        msg.append("农历 ").append(LunarCalendar.getInstance().getMonthAndDay());
        msg.append(st.getSolar(null));
        JSONObject weatherJson = getWeatherJson(city);
        logger.info("查询 wthrcdn.etouch.cn 天气结果=" + weatherJson.toString());
        if(weatherJson.getJSONObject("resp")!= null){
            if(StringUtils.isBlank(weatherJson.getStr("error"))){
                msg.append(getDayWeather(weatherJson));
                msg.append(getEnvironment(weatherJson));


            }
        }
        return null;
    }

    public JSONObject getWeatherJson(String city){
        String url="http://wthrcdn.etouch.cn/WeatherApi";
        Map<String,Object> map = new HashMap<>();
        map.put("city",city);
        String xml = HttpUtil.get(url,map,5000);
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj;
    }

    /**
     * 获取天气信息
     * @param dayJson
     * @return
     */
    public static String getDayWeather(JSONObject dayJson){
        StringBuilder msg = new StringBuilder();
        JSONObject respObj = dayJson.getJSONObject("resp");
        if(respObj!=null){
            JSONObject forecastObj = respObj.getJSONObject("forecast");
            if(forecastObj !=null){
                JSONArray weatherObjs = forecastObj.getJSONArray("weather");
                if(weatherObjs.size()>0){
                    JSONObject weatherObj = weatherObjs.getJSONObject(0);
                    if(weatherObj!=null){
                        JSONObject dayObj = weatherObj.getJSONObject("day");
                        String type = dayObj.getStr("type");
                        String high = StringUtils.replaceAll(weatherObj.getStr("high"),"高温 ","最高气温");
                        msg.append("今天白天 ").append(type).append(" ").append(dayObj.getStr("fengxiang")).append(dayObj.getStr("fengli")).append(",");
                        msg.append(high).append(";");

                        JSONObject nightObj = weatherObj.getJSONObject("night");
                        type = nightObj.getStr("type");
                        String low = StringUtils.replaceAll(weatherObj.getStr("low"),"低温 ","最低气温");
                        msg.append("夜间 ").append(type).append(" ").append(nightObj.getStr("fengxiang")).append(nightObj.getStr("fengli")).append(",");
                        msg.append(low).append(";");

                    }
                }
            }
        }
        return msg.toString();
    }

    /**
     * 获取环境信息
     * @param dayJson
     * @return
     */
    public static String getEnvironment(JSONObject dayJson){
        StringBuilder msg = new StringBuilder();
        JSONObject respObj = dayJson.getJSONObject("resp");
        if(respObj!=null){
           String shidu = respObj.getStr("shidu");
           JSONObject environmentObj = respObj.getJSONObject("environment");

            msg.append("湿度:").append(shidu).append(" pm25: ").append(environmentObj.getStr("pm25"))
               .append("(").append(environmentObj.getStr("quality")).append(")");
            String suggest = environmentObj.getStr("suggest");
            if(StringUtils.isNotBlank(suggest)){
                msg.append(" 温馨提示:[").append(suggest).append("]");
            }
        }
        return msg.toString();
    }


    public static void main(String[] args) throws ParseException {
        String url="http://wthrcdn.etouch.cn/WeatherApi";

        Map<String,Object> map = new HashMap<>();
        map.put("city","北京");
        String xml = HttpUtil.get(url,map,5000);

//        String xml="{\"resp\": {\n" +
//                "    \"shidu\": \"28%\",\n" +
//                "    \"zhishus\": {\"zhishu\": [\n" +
//                "        {\n" +
//                "            \"name\": \"晨练指数\",\n" +
//                "            \"detail\": \"天气不错，空气清新，是您晨练的大好时机，建议不同年龄段的人们积极参加户外健身活动。\",\n" +
//                "            \"value\": \"适宜\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"舒适度\",\n" +
//                "            \"detail\": \"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。\",\n" +
//                "            \"value\": \"舒适\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"穿衣指数\",\n" +
//                "            \"detail\": \"建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。\",\n" +
//                "            \"value\": \"较舒适\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"感冒指数\",\n" +
//                "            \"detail\": \"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。\",\n" +
//                "            \"value\": \"较易发\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"晾晒指数\",\n" +
//                "            \"detail\": \"天气不错，适宜晾晒。赶紧把久未见阳光的衣物搬出来吸收一下太阳的味道吧！\",\n" +
//                "            \"value\": \"适宜\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"旅游指数\",\n" +
//                "            \"detail\": \"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。\",\n" +
//                "            \"value\": \"适宜\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"紫外线强度\",\n" +
//                "            \"detail\": \"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\",\n" +
//                "            \"value\": \"中等\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"洗车指数\",\n" +
//                "            \"detail\": \"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\",\n" +
//                "            \"value\": \"较适宜\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"运动指数\",\n" +
//                "            \"detail\": \"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。\",\n" +
//                "            \"value\": \"较适宜\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"约会指数\",\n" +
//                "            \"detail\": \"虽然有点风，但情侣们可以放心外出，不用担心天气来调皮捣乱而影响了兴致。\",\n" +
//                "            \"value\": \"较适宜\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"雨伞指数\",\n" +
//                "            \"detail\": \"天气较好，不会降水，因此您可放心出门，无须带雨伞。\",\n" +
//                "            \"value\": \"不带伞\"\n" +
//                "        }\n" +
//                "    ]},\n" +
//                "    \"fengli\": \"4级\",\n" +
//                "    \"city\": \"北京\",\n" +
//                "    \"fengxiang\": \"东南风\",\n" +
//                "    \"forecast\": {\"weather\": [\n" +
//                "        {\n" +
//                "            \"date\": \"28日星期三\",\n" +
//                "            \"high\": \"高温 25℃\",\n" +
//                "            \"low\": \"低温 9℃\",\n" +
//                "            \"night\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"晴\"\n" +
//                "            },\n" +
//                "            \"day\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"东风\",\n" +
//                "                \"type\": \"多云\"\n" +
//                "            }\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"date\": \"29日星期四\",\n" +
//                "            \"high\": \"高温 19℃\",\n" +
//                "            \"low\": \"低温 7℃\",\n" +
//                "            \"night\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"晴\"\n" +
//                "            },\n" +
//                "            \"day\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"晴\"\n" +
//                "            }\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"date\": \"30日星期五\",\n" +
//                "            \"high\": \"高温 19℃\",\n" +
//                "            \"low\": \"低温 8℃\",\n" +
//                "            \"night\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"晴\"\n" +
//                "            },\n" +
//                "            \"day\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"晴\"\n" +
//                "            }\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"date\": \"31日星期六\",\n" +
//                "            \"high\": \"高温 23℃\",\n" +
//                "            \"low\": \"低温 10℃\",\n" +
//                "            \"night\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"东北风\",\n" +
//                "                \"type\": \"多云\"\n" +
//                "            },\n" +
//                "            \"day\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"多云\"\n" +
//                "            }\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"date\": \"1日星期天\",\n" +
//                "            \"high\": \"高温 25℃\",\n" +
//                "            \"low\": \"低温 12℃\",\n" +
//                "            \"night\": {\n" +
//                "                \"fengli\": \"3-4级\",\n" +
//                "                \"fengxiang\": \"北风\",\n" +
//                "                \"type\": \"多云\"\n" +
//                "            },\n" +
//                "            \"day\": {\n" +
//                "                \"fengli\": \"<3级\",\n" +
//                "                \"fengxiang\": \"南风\",\n" +
//                "                \"type\": \"多云\"\n" +
//                "            }\n" +
//                "        }\n" +
//                "    ]},\n" +
//                "    \"wendu\": 21,\n" +
//                "    \"sunset_2\": \"\",\n" +
//                "    \"yesterday\": {\n" +
//                "        \"low_1\": \"低温 11℃\",\n" +
//                "        \"date_1\": \"27日星期二\",\n" +
//                "        \"day_1\": {\n" +
//                "            \"type_1\": \"晴\",\n" +
//                "            \"fx_1\": \"南风\",\n" +
//                "            \"fl_1\": \"<3级\"\n" +
//                "        },\n" +
//                "        \"high_1\": \"高温 27℃\",\n" +
//                "        \"night_1\": {\n" +
//                "            \"type_1\": \"多云\",\n" +
//                "            \"fx_1\": \"东北风\",\n" +
//                "            \"fl_1\": \"<3级\"\n" +
//                "        }\n" +
//                "    },\n" +
//                "    \"sunset_1\": \"18:35\",\n" +
//                "    \"sunrise_2\": \"\",\n" +
//                "    \"environment\": {\n" +
//                "        \"no2\": 31,\n" +
//                "        \"pm25\": 148,\n" +
//                "        \"o3\": 75,\n" +
//                "        \"so2\": 8,\n" +
//                "        \"aqi\": 500,\n" +
//                "        \"pm10\": 1289,\n" +
//                "        \"suggest\": \"老年人病人应留在室内，停止体力消耗，一般人群避免户外活动\",\n" +
//                "        \"time\": \"14:00:00\",\n" +
//                "        \"co\": 1,\n" +
//                "        \"MajorPollutants\": \"颗粒物(PM10)\",\n" +
//                "        \"quality\": \"严重污染\"\n" +
//                "    },\n" +
//                "    \"updatetime\": \"14:16\",\n" +
//                "    \"sunrise_1\": \"06:06\"\n" +
//                "}}";

        /* 第一种方法，使用JSON-JAVA提供的方法 */
//        //将xml转为json
//        JSONObject xmlJSONObj = XML.toJSONObject(xml);
//
////        JSONObject xmlJSONObj =new JSONObject(xml);
////                //设置缩进
//        String jsonPrettyPrintString = xmlJSONObj.toJSONString(4);
//
//        System.out.println(jsonPrettyPrintString);
//        System.out.println(getEnvironment(xmlJSONObj));


//        String html = HttpUtil.get("http://www.bjjtgl.gov.cn/");
        String html = HttpUtil.get("http://www.bjjtgl.gov.cn/zhuanti/10weihao/index.html");

        System.out.println(html);


        //输出格式化后的json

    }
}
