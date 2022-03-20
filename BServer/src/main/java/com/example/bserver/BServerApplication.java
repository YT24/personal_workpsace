package com.example.bserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.example.bserver.fegin")
@SpringBootApplication(scanBasePackages = "com.example.bserver")
public class BServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BServerApplication.class, args);
    }

}
