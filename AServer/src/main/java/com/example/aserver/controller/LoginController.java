package com.example.aserver.controller;


import com.example.aserver.anntion.Demo;
import com.example.aserver.anntion.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RestController
public class LoginController {



    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;


    @GetMapping("login")
    public String login(){

        Demo demo = new Demo();
        Class<? extends Demo> aClass = demo.getClass();
        Info info = aClass.getAnnotation(Info.class);
        log.info("info.defaultDBValue(): {}" ,info.defaultDBValue());
        log.info("info.name(): {}",info.name());


        Map map = new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        return map.toString();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "pool",method = RequestMethod.GET)
    public void threadPoolTest(){
        redisTemplate.opsForList().leftPush("k1","v1");
        redisTemplate.opsForList().leftPush("k1","v2");
        redisTemplate.opsForList().leftPush("k1","v3");
        redisTemplate.opsForList().leftPush("k1","v4");

        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"A").start();
        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"B").start();
        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"C").start();
        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"D").start();

        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"E").start();

        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"F").start();

        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"G").start();

        new Thread(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName()+"-"+redisTemplate.opsForList().rightPop("k1"));
        },"H").start();

    }

}
