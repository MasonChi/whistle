package com.github.whistle.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by child.
 * Date: 2016/12/13.
 * Description: string工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 校验空值
     *
     * @param object 对象
     * @return 如果object == null, 返回空字符串, 否则返回对象.toString()
     */
    public static String validateNull(Object object) {
        return null == object ? "" : object.toString();
    }

    /**
     * 判断object或多个object是否存在空值元素
     *
     * @param objects 对象数组
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean hasBlank(Object... objects) {
        boolean result = false;
        for (Object object : objects) {
            if (object == null || "".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 判断object或多个object都是空值
     *
     * @param objects 对象数组
     * @return 全部是Blank, 则返回true
     */
    public static boolean isBlank(Object... objects) {
        boolean result = true;
        for (Object object : objects) {
            if (object != null && !"".equals(object.toString().trim())) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 判断object或多个object都是非空值
     *
     * @param objects 对象数组
     * @return 全部不是Blank, 则返回true
     */
    public static boolean isNotBlank(Object... objects) {
        return !hasBlank(objects);
    }

    /**
     * 判断一个字符串在数组中存在几个
     *
     * @param strings 字符串数组
     * @param baseStr 查询字符串
     * @return 个数
     */
    public static int indexOf(String[] strings, String baseStr) {
        if (null == baseStr || baseStr.length() == 0 || null == strings) {
            return 0;
        }

        return indexOf(Arrays.asList(strings), baseStr);
    }

    /**
     * 判断一个字符串在List中存在几个
     *
     * @param strings 字符串数组
     * @param baseStr 查询字符串
     * @return 个数
     */
    public static int indexOf(List<String> strings, String baseStr) {

        if (null == strings || strings.isEmpty() || isBlank(baseStr)) {
            return 0;
        }

        int i = 0;
        for (String string : strings) {
            boolean result = baseStr.equals(string);
            i = result ? ++i : i;
        }
        return i;
    }

    /**
     * 字符串encoding
     *
     * @param arg 请求参数
     * @return encoding
     * @throws UnsupportedEncodingException
     */
    public static String encoding(String arg) throws UnsupportedEncodingException {
        return URLEncoder.encode(arg, "utf-8");
    }

    /**
     * 字符串decode
     *
     * @param arg 请求参数
     * @return decode
     * @throws UnsupportedEncodingException
     */
    public static String decode(String arg) throws UnsupportedEncodingException {
        return URLDecoder.decode(arg, "UTF-8");
    }

    /**
     * 设置JSON字符串返回
     *
     * @param key   json key
     * @param value json value
     * @return json字符串
     */
    public static String getJsonString(String key, String value) {

        if (StringUtils.isNotBlank(key, value)) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(key, value);
            return jsonObject.toString();
        }
        return "";
    }

    /**
     * 判断一个string是否是JSONObject, 是返回JSONObject, 不是返回null
     *
     * @param object 对象
     * @return JSONObject/null
     */
    public static JSONObject isJsonObject(String object) {
        if (isBlank(object)) {
            return null;
        }

        try {
            return (JSONObject) JSON.toJSON(object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断一个string是否是JSONArray, 是返回JSONArray, 不是返回null
     *
     * @param object 对象
     * @return JSONObject/null
     */
    public static JSONArray isJsonArray(String object) {
        if (isBlank(object)) {
            return null;
        }

        try {
            return JSON.parseArray(object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把Map转换成get请求参数类型
     * 如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     *
     * @param map 转化的对象
     * @return get请求参数的拼接
     */
    public static String mapToGet(Map<?, ?> map) {
        String result = "";

        if (map == null || map.size() == 0) {
            return result;
        }

        Set<?> keys = map.keySet();
        for (Object key : keys) {
            result += (key + "=" + map.get(key) + "&");
        }

        return isBlank(result) ? result : result.substring(0, result.length() - 1);
    }

    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     *
     * @param get get参数字符串
     * @return map
     */
    public static Map<String, Object> getToMap(String get) {
        if (isBlank(get)) {
            return null;
        }

        get = get.trim();
        if (get.startsWith("?")) {
            get = get.substring(1);
        }

        List<String> args = Arrays.asList(get.split("&"));
        if (isBlank(args)) {
            return null;
        }

        Map<String, Object> result = new HashMap<String, Object>();
        for (String arg : args) {
            if (!isBlank(arg) && arg.indexOf("=") > 0) {
                String[] keyValue = arg.split("=");

                String key = keyValue[0];
                String value = "";
                for (int i = 1; i < keyValue.length; i++) {
                    value += keyValue[i] + "=";
                }
                value = value.length() > 0 ? value.substring(0, value.length() - 1) : value;
                result.put(key, value);
            }
        }

        return result;
    }

    /**
     * 替换字符串
     *
     * @param str        完整字符串
     * @param nowStr     需替换的字符串
     * @param replaceStr 替换的字符串
     * @return 替换后的完整的字符串
     */
    public static String replaceString(String str, String nowStr, String replaceStr) {

        nowStr = StringUtils.isBlank(nowStr) ? "" : nowStr;
        replaceStr = StringUtils.isBlank(replaceStr) ? "" : replaceStr;

        if (StringUtils.isNotBlank(str)) {
            return str.replaceAll(nowStr, replaceStr);
        }

        return "";
    }

    /**
     * 把string数组转换成Set
     *
     * @param args 数组
     * @return set
     */
    public static TreeSet<String> arrayToSet(String[] args) {
        return arrayToSet(Arrays.asList(args));
    }

    /**
     * 把List转换成Set
     *
     * @param list 列表
     * @return set
     */
    public static TreeSet<String> arrayToSet(List<String> list) {
        TreeSet<String> result = new TreeSet<String>();
        if (null == list || list.isEmpty()) {
            return result;
        }
        for (String e : list) {
            result.add(e);
        }
        return result;
    }
}
