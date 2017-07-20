package com.github.whistle.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by child.
 * Date: 2016/12/14.
 * Description: http请求工具类
 */
public class HttpClientUtils {

    // 编码
    public static final String CHARSET = "UTF-8";

    // 超时时间
    private static final int TIMEOUT = 10000;

    /**
     * get请求并获取请求结果,若请求成功，则返回true
     *
     * @param domain get请求域名
     * @param path   get请求路径
     * @return boolean
     */
    public static boolean sendGet(String domain, String path) {
        HttpGet get = buildGet(domain, path);
        try {

            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
        return false;
    }

    /**
     * get请求并获取请求结果
     *
     * @param domain get请求域名
     * @param path   get请求路径
     * @param clazz  response反射类
     * @param <T>    返回类型
     * @return T
     */
    public static <T> T sendGet(String domain, String path, Class<T> clazz) {
        HttpGet get = buildGet(domain, path);
        try {
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String text = EntityUtils.toString(response.getEntity(), CHARSET);
                return JSONObject.parseObject(text, clazz);
            }
        } catch (Exception e) {
            StringWriter out = new StringWriter();
            e.printStackTrace(new PrintWriter(out));
        } finally {
            get.releaseConnection();
        }
        return null;
    }

    /**
     * post请求获取请求结果，若请求成功，则返回true
     *
     * @param path    路径 不包含域名
     * @param request 请求参数体
     * @return boolean
     */
    public static boolean sendPost(String domain, String path, Object request) {
        HttpPost post = buildPost(domain, path, request);
        try {
            HttpClient client = HttpClients.createMinimal();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return false;
    }

    /**
     * post请求获取请求结果，并返回请求结果
     *
     * @param path    路径 不包含域名
     * @param request 请求参数体
     * @param clazz   response反射类
     * @param <T>     返回类型
     * @return T
     */
    public static <T> T sendPost(String domain, String path, Object request, Class<T> clazz) {
        HttpPost post = buildPost(domain, path, request);
        try {
            HttpClient client = HttpClients.createMinimal();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String text = EntityUtils.toString(response.getEntity(), CHARSET);
                return JSONObject.parseObject(text, clazz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    /**
     * 构建get请求体
     *
     * @param domain get请求域名
     * @param path   get请求路径
     * @return httpGet
     */
    private static HttpGet buildGet(String domain, String path) {
        HttpGet get = new HttpGet(domain + "/" + path);
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();
        get.setConfig(config);

        return get;
    }

    /**
     * 构建post请求体
     *
     * @param path    路径 不包含域名
     * @param request 请求参数体
     * @return httpPost
     */
    private static HttpPost buildPost(String domain, String path, Object request) {
        HttpPost post = new HttpPost(domain + "/" + path);
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();
        post.setConfig(config);

        // 请求参数为json格式
        StringEntity entity = new StringEntity(JSONArray.toJSONString(request), CHARSET);
        post.setEntity(entity);

        return post;
    }
}
