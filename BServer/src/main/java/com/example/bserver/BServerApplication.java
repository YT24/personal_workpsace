package com.example.bserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients("com.example.bserver.fegin")
@SpringBootApplication(scanBasePackages = "com.example.bserver")
@MapperScan("com.example.bserver.mapper")
public class BServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BServerApplication.class, args);
    }

}
