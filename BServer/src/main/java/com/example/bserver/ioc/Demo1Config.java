package com.example.bserver.ioc;

import com.example.bserver.ioc.Demo1Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class Demo1Config {

    @Bean
    public Demo1Service demo1Service(){
        System.out.println("demo1config 加载了");
        return new Demo1Service();
    }

}