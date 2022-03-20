package com.example.yangt.spring.aop;

import org.springframework.web.bind.annotation.GetMapping;

public class AopTest {

    public static void main(String[] args) {

    }

    @GetMapping("aop")
    public String testAop(){

        return "SUCCESS";
    }
}
