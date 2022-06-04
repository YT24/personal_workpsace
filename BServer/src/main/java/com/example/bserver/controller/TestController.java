package com.example.bserver.controller;

import org.springframework.util.Assert;
import org.springframework.validation.BindException;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestController {


    public static void main(String[] args) throws InterruptedException {
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

    public boolean tryRelease(int arg) {
        throw new RuntimeException();
    }
}
