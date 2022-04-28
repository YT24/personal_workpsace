package com.example.bserver.controller;

import com.example.bserver.fegin.NacosFegin;
import com.example.bserver.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RefreshScope // nacos 配置修改时自动刷新
@RestController
public class ConsumerController {


//    @Autowired
//    private KafkaTopicService kafkaTopicService;
//
//    @Autowired
//    private KafkaSender kafkaSender;
//
//    @Autowired
//    private NacosFegin nacosFegin;


    @Value("${login.username:null}")
    private String username;


    @GetMapping("/test")
    public String login() {
        System.out.println("username：" + username);
        return "SUCCESS";
    }
//
//
//    @PostMapping("/kafka/topic")
//    public String createTopic(){
//        kafkaTopicService.createTopic("kafka-topic-1");
//        return "SUCCESS";
//    }
//
//    @GetMapping("/kafka/topic")
//    public Object queryTopic() throws ExecutionException, InterruptedException {
//
//        return kafkaTopicService.queryTopicList();
//    }
//
//
//    @PostMapping("/kafka/msg")
//    public void msgSend() throws ExecutionException, InterruptedException {
//
//        kafkaSender.sendStr("123456789qwertyuio");
//
//    }


    static ThreadLocal<String> localVar = new ThreadLocal<>();

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        // 清除本地内存中的本地变量
       // localVar.remove();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                localVar.set("localVar--1");
                //调用打印方法
                print("thread1");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());

            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                localVar.set("localVar--2");
                //调用打印方法
                print("thread2");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());

            }
        });
        t2.start();

    }

}