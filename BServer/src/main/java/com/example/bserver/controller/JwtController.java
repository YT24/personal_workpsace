package com.example.bserver.controller;

import com.example.bserver.jwt.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    JwtService jwtService;


    @RequestMapping("/get")
    public void jwt(){
        String token = jwtService.createToken("1234-21345-2134-2134");
        System.out.println(token);
    }
}
