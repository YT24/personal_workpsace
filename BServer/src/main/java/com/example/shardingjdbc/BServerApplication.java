package com.example.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients("com.example.shardingjdbc.fegin")
@SpringBootApplication(scanBasePackages = "com.example.shardingjdbc",exclude = {DataSourceAutoConfiguration.class})
public class BServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BServerApplication.class, args);
    }

}
