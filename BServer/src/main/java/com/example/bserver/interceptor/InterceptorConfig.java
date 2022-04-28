package com.example.bserver.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@AllArgsConstructor
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Inteceptor1 inteceptor1;

    @Autowired
    private Inteceptor2 inteceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inteceptor2).addPathPatterns("/**");
        registry.addInterceptor(inteceptor1).addPathPatterns("/**");
    }
}

 