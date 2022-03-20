package com.example.yangt.utils;


import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class HttpClientUtils {

    /**
     * 发送Get请求
     *
     * @param url            请求地址 带参数
     * @param requestHeaders 请求头
     * @return 返回结果
     */
    public static String get(String url, Map<String, String> requestHeaders) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = "";
        //添加请求头
        if (requestHeaders != null) {
            for (String key : requestHeaders.keySet()) {
                httpGet.addHeader(key, requestHeaders.get(key));
            }
        }
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 发送post请求
     *
     * @param url            请求地址
     * @param json           请求参数
     * @param requestHeaders 请求头
     * @return 第三方返回结果
     */
    public static String post(String url, String json, Map<String, String> requestHeaders) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = "";
        //添加请求头
        /*String esbUsername = "username";
        String esbPassword = "password";
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((esbUsername + ":" + esbPassword).getBytes()));*/
        if (requestHeaders != null) {
            for (String key : requestHeaders.keySet()) {
                httpPost.addHeader(key, requestHeaders.get(key));
            }
        }
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        //执行
        try {
            httpResponse = httpClient.execute(httpPost);
            //判断状态值为200
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }else{
                result = httpResponse.getStatusLine().getReasonPhrase();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            result = e.getLocalizedMessage();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 发送XML Post请求
     *
     * @param url            请求地址
     * @param xml            请求参数
     * @param requestHeaders 请求头
     * @return 返回结果
     */
    public static String postXml(String url, String xml, Map<String, String> requestHeaders) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = "";
        //添加请求头
        httpPost.addHeader("Content-Type", "text/xml");
        if (requestHeaders != null) {
            for (String key : requestHeaders.keySet()) {
                httpPost.addHeader(key, requestHeaders.get(key));
            }
        }
        httpPost.setEntity(new StringEntity(xml, ContentType.APPLICATION_XML));
        //执行
        try {
            httpResponse = httpClient.execute(httpPost);
            //判断状态值为200
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 发送put请求
     *
     * @param url            请求地址
     * @param json           请求json
     * @param requestHeaders 请求头
     * @return 返回结果
     */
    public static String put(String url, String json, Map<String, String> requestHeaders) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        CloseableHttpResponse httpResponse = null;
        String result = "";

        //添加请求头
        httpPut.addHeader("Content-Type", "application/json;charset=utf-8");
        if (requestHeaders != null) {
            for (String key : requestHeaders.keySet()) {
                httpPut.addHeader(key, requestHeaders.get(key));
            }
        }
        httpPut.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        //执行
        try {
            httpResponse = httpClient.execute(httpPut);
            //判断状态值为200
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }

        return result;
    }

    /**
     * 发送delete请求
     *
     * @param url            请求地址
     * @param requestHeaders 请求头
     * @return 返回结果
     */
    public static String delete(String url, Map<String, String> requestHeaders) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse httpResponse = null;
        String result = "";
        //添加请求头
        if (requestHeaders != null) {
            for (String key : requestHeaders.keySet()) {
                httpDelete.addHeader(key, requestHeaders.get(key));
            }
        }
        //执行
        try {
            httpResponse = httpClient.execute(httpDelete);
            //判断状态值为200
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 关闭 CloseableHttpClient CloseableHttpResponse
     *
     * @param httpClient
     * @param httpResponse
     */
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        try {
            httpClient.close();
            if (httpResponse != null) {
                httpResponse.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


    }



}
