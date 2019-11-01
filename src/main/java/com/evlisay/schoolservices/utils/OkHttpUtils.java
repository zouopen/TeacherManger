package com.evlisay.schoolservices.utils;

import com.evlisay.schoolservices.constant.SchoolLoginAddress;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 22:03
 */
@Component
@Slf4j
public class OkHttpUtils {

    private final OkHttpClient okHttpClient;

    @Autowired
    public OkHttpUtils(OkHttpClient okHttpClient) {

        this.okHttpClient = okHttpClient;

    }


    /**
     *
     * @param url 请求的url
     * @return 返回的值
     */
    public String get(String url,String referer,String cookie){
        Request request = new Request
                .Builder()
                .addHeader("Referer",referer)
                .addHeader("Cookie",cookie)
                .url(url)
                .build();

        return TryException(request);
    }

    /**
     * 获取图片的访问
     * @param url
     * @return
     */
    public byte[] getCheckCode(String url,String setCookie) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie",setCookie)
                .build();
        try {
            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Cookie的
     * @param url
     * @return
     */
    public Response getCookie(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Post
     * @param url 请求的URL
     * @param params 请求的参数
     * @return 返回的值
     */
    public String post(String url, Map<String,String> params,String referer,String cookie){
        FormBody.Builder form = new FormBody.Builder();

        if (params != null && params.keySet().size() > 0){
            for (String key : params.keySet()){
                form.add(key,params.get(key));
            }
        }

        Request request = new Request
                .Builder()
                .url(url)
                .addHeader("Referer",SchoolLoginAddress.HEADER + referer)
                .addHeader("Cookie",cookie)
                .post(form.build())
                .build();
        return TryException(request);
    }

    public Response postLogin(String url, Map<String,String> params,String cookie){
        FormBody.Builder form = new FormBody.Builder();

        if (params != null && params.keySet().size() > 0){
            for (String key : params.keySet()){
                form.add(key,params.get(key));
            }
        }

        Request request = new Request
                .Builder()
                .url(url)
                .addHeader("Cookie",cookie)
                .post(form.build())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     *
     * @param url 请求Url
     * @param params 请求参数
     * @param fileType  请求文件
     * @return 返回的值
     */
    public String postFile(String url,Map<String,Object> params,String fileType,String referer){

        MultipartBody.Builder builder = new MultipartBody.Builder();

        if (params != null && params.keySet().size() > 0){
            for (String key : params.keySet()){
                if (params.get(key) instanceof File){
                    File file = (File) params.get(key);
                    builder.addFormDataPart(key,file.getName(),RequestBody.create(MediaType.parse(fileType),file));
                }
                builder.addFormDataPart(key,params.get(key).toString());
            }
        }
        Request request = new Request
                .Builder()
                .url(url)
                .addHeader("Referer",SchoolLoginAddress.BASE_URL + referer)
                .post(builder.build())
                .build();

        return TryException(request);
    }

    private String TryException(Request request){
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            log.info("返回体的网址:"+ response.networkResponse().request().url());
            log.info("返回体Referer："+response.networkResponse().request().header("Referer"));
            log.info("返回体Cookie："+response.networkResponse().request().header("Cookie"));
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            assert response != null;
            response.close();
        }
        return null;
    }
}
