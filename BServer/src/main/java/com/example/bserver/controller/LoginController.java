package com.example.bserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bserver.expection.ResponseResult;
import com.example.bserver.jwt.config.JwtService;
import com.example.bserver.vo.LoginVo;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


@RestController
public class LoginController {

    @Autowired
    JwtService jwtService;

    @Autowired
    public Cache cache;



    @GetMapping("/login")
    public ResponseResult jwt(@RequestBody @Validated LoginVo loginVo){
        String token = jwtService.createToken(loginVo.getUserName());
        return ResponseResult.success(token);
    }


    @PutMapping("/cache")
    public ResponseResult setCache(@RequestParam("key") String key,@RequestParam("value") String value){
        cache.put(key,value);
        return ResponseResult.success();
    }

    @GetMapping("/cache")
    public ResponseResult getCache(@RequestParam("key") String key){
        ConcurrentMap concurrentMap = cache.asMap();
        Map map = new HashMap<>();
        map.put("all", JSONObject.toJSONString(concurrentMap));
        map.put(key, cache.getIfPresent(key));
        return ResponseResult.success(map);
    }
}
