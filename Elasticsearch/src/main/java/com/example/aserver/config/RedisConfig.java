package com.example.aserver.config;

import com.example.aserver.controller.Demo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "redis", value = "enable", havingValue = "false")
@Configuration
public class RedisConfig {


    @Bean
    public Demo demo() {
        return new Demo();
    }
}
