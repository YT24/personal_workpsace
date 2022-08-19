package com.example.aserver;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AServerApplication.class, args);
    }


    @Bean
    @Scope(value = "prototype")
    public IRule loadBalanceRule()
    {
        return new NacosRule();
    }
}
