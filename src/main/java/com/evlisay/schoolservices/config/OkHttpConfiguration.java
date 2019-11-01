package com.evlisay.schoolservices.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: EvilSay
 * @Date: 2019/7/8 21:42
 */
@Configuration
@Slf4j
public class OkHttpConfiguration {

    //创建一个单线程用户的线程池.
    @Bean
    public ConnectionPool pool(){
        return new ConnectionPool(200,5,TimeUnit.SECONDS);
    }

    @Bean
    public OkHttpClient OkHttpClient(){
        log.info("okHttp实例化");
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true) //是否开启缓存
                .connectionPool(pool()) //连接池
                .connectTimeout(10L,TimeUnit.SECONDS)
                .readTimeout(10L,TimeUnit.SECONDS)
                .build();
    }

}

