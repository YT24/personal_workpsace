package com.example.shardingjdbc.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@AllArgsConstructor
public class InterceptorConfig extends WebMvcConfigurerAdapter {


    @Autowired
    private IdempotentInteceptor idempotentInteceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(idempotentInteceptor).addPathPatterns("/**");
    }
}

 