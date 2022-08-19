package com.example.aserver.controller;


import com.example.aserver.anntion.Demo;
import com.example.aserver.anntion.Info;
import com.example.aserver.entity.properties.ThreadPoolProperties;
import com.example.aserver.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
public class LoginController {



    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;




    @GetMapping("te")
    public String te(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }



        return null;
    }

    public static void main(String[] args) {
        ServiceLoader<LoginService> properties = ServiceLoader.load(LoginService.class);
        properties.forEach(loginService -> {
            System.out.println("登录方式："+loginService.login(null,null));
        });

    }


    @GetMapping("login")
    public String login(){

        ServiceLoader<ThreadPoolProperties> properties = ServiceLoader.load(ThreadPoolProperties.class);
        properties.forEach(threadPoolProperties -> {
            System.out.println(threadPoolProperties.getCorePoolSize());
        });


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
