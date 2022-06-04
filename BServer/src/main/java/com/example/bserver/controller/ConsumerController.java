package com.example.bserver.controller;

import com.example.bserver.annotation.AutoIdempotent;
import com.example.bserver.aop.UserToHolder;
import com.example.bserver.beancreate.User;
import com.example.bserver.event.NoticeEvent;
import com.example.bserver.token.TokenService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RefreshScope // nacos 配置修改时自动刷新
@RestController
public class ConsumerController {



    @Value("${login.username:null}")
    private String username;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private User user;

    @AutoIdempotent
    @GetMapping("/test")
    public String login(@RequestBody Map map) throws InterruptedException {

        return UserToHolder.getUserTo().toString();
    }

    @GetMapping("/token")
    public String login() throws InterruptedException {

        return tokenService.getToken();
    }



    static ThreadLocal<String> localVar = new ThreadLocal<>();
    // 实现父子线程共享
    static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        // 清除本地内存中的本地变量
        // localVar.remove();
    }

    public static void main1(String[] args) {
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


        InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set(6);
        System.out.println("父线程获取数据：" + threadLocal.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        threadLocal.set(6);
        executorService.submit(() -> {
            System.out.println("第一次从线程池中获取数据：" + threadLocal.get());
        });

        threadLocal.set(7);
        executorService.submit(() -> {
            System.out.println("第二次从线程池中获取数据：" + threadLocal.get());
        });


    }


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
//            LinkedList list = new LinkedList();
//            list.add("1");
//
//            Queue queue = new LinkedBlockingQueue();
//            queue.add(1);
//            queue.add(2);
//            queue.add(3);
//            System.out.println(queue.poll());

            //LinkedHashMap map = new LinkedHashMap();
            HashMap<String,Object> map = new HashMap();
            ConcurrentHashMap<String,Object> cmap = new ConcurrentHashMap();
            cmap.put("A",1);
            map.put("a",1);
            map.put("b",2);
            map.put("c",3);
            //1
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
            }
            //2 
            for (String o : map.keySet()) {
                
            }
            //3
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                entry.getKey();
                entry.getValue();
            }

            Collections.synchronizedMap(map);

            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.stream().forEach(integer -> {
                System.out.println(integer);
            });

        }finally {
            lock.unlock();
        }


    }
}