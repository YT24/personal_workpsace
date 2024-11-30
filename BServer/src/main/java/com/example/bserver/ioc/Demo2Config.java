package com.example.bserver.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class Demo2Config {
    @Bean
    public Demo2Service demo2Service(){
        System.out.println("demo2config 加载了");
        return new Demo2Service();
    }

}