/**
 * LoginInterceptor.java
 * Created at 2018-06-06
 * Created by ZuoJian
 * Copyright(C)2018SAIC|SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.example.yangt.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//public class LoginInterceptorConfig extends WebMvcConfigurationSupport {
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor setBean2() {
        System.out.println("注入了LoginInterceptor");
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //addPathPatterns()添加拦截路径
        //excludePathPatterns() 添加不拦截的路径
        //添加注册登录拦截器{被拦截访问路径才会进入prehandle方法}
        registry.addInterceptor(setBean2())/*.addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns)*/;

        //如果有多个拦截器可以注册多个...


    }

}
