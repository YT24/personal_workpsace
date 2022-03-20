package com.example.yangt.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        demo1();
        demo2();
        Lock lock = new ReentrantLock();

    }

    private static void demo1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //准备完毕……运动员都阻塞在这，等待号令
                    System.out.println("【" + Thread.currentThread().getName() + "】" + "开始准备……");
                    countDownLatch.await();
                    System.out.println("【" + Thread.currentThread().getName() + "】" + "开始执行……");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(2000);// 裁判准备发令
        for (int i = 0; i <3 ; i++) {
            countDownLatch.countDown();// 发令枪：执行发令
        }
    }

    private static void demo2() {
        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println(".......主线程开始执行.......");
        //第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行 完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程："+Thread.currentThread().getName()+"执行 完毕");
                latch.countDown();
            }
        });
        es2.shutdown();


        System.out.println("......等待两个线程执行完毕.....");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}
