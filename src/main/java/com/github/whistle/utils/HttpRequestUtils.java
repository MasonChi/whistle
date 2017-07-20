package com.github.whistle.utils;

import org.apache.commons.codec.CharEncoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by child.
 * Date: 2016/12/18.
 * Description:
 */
public class HttpRequestUtils {

    /**
     * 向指定URL发送GET请求
     *
     * @param url          发送请求的URL
     * @param param        请求参数，请求参数应该是 name=child&age=20 的形式。
     * @param charEncoding 若为null, 默认为UTF-8
     * @return URL 所代表远程资源的响应结果
     */
    @SuppressWarnings("unused")
    public static String sendGet(String url, String param, String charEncoding) {

        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + (param != null ? ("?" + param) : "");
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("accept-language",
                    "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            connection.setRequestProperty("accept-encoding", "gzip, deflate");
            connection.setRequestProperty(
                    "user-agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:32.0) Gecko/20100101 Firefox/32.0");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),
                    org.apache.commons.lang3.StringUtils.isNotBlank(charEncoding)
                            ? charEncoding : CharEncoding.UTF_8));
            StringBuilder line = new StringBuilder();
            char[] cbuf = new char[1024];
            int len;
            while ((len = in.read(cbuf)) != -1) {
                line.append(cbuf, 0, len);
            }
            result = line.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送POST请求
     *
     * @param url    发送请求的 URL
     * @param object 请求的参数
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    public static String sendPost(String url, Object object) {
        Map<String, Object> params = HttpRequestUtils.convertObjToMap(object);
        return sendPost(url, params);
    }

    /**
     * 将对象转化为map
     *
     * @param obj 对象
     * @return Map
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> convertObjToMap(Object obj) {
        Map<String, Object> reMap = new HashMap<>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                try {
                    Field f = obj.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(field.getName(), o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

    /**
     * 向指定URL发送POST请求
     *
     * @param url   发送请求的 URL
     * @param param 请求的参数, {name:child, age:20} 的形式。
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    public static String sendPost(String url, Map<String, Object> param) {
        String params = StringUtils.mapToGet(param);
        return sendPost(url, params);
    }

    /**
     * 向指定URL发送POST请求
     *
     * @param url   发送请求的 URL
     * @param param 请求的参数, name=child&age=20 的形式。
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    public static String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (org.apache.commons.lang3.StringUtils.isNotBlank(param)) {
                out.write(param);
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
