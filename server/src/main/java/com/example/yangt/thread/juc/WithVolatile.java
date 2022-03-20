package com.example.yangt.thread.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WithVolatile {
    volatile List list = Collections.synchronizedList(new LinkedList());

    public  void add(Object o){
        list.add(o);
    }

    public  int size(){return list.size();}


    public static void main(String[] args) {
        /*WithVolatile withVolatile = new WithVolatile();

        new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {
                withVolatile.add(new Object());
                System.out.println("add:" + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        },"T1").start();

        new Thread(() -> {
            while(true){
                if (withVolatile.size() == 5){
                    break;
                }
            }
            System.out.println("T2结束了");

        },"T2").start();*/
        WithVolatile withVolatile = new WithVolatile();
        final Object o = new Object();

        System.out.println("T2启动了》。。。。");
        new Thread(() ->{
            synchronized (o){
                if(withVolatile.size()!=5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T2 结束了。。。");
                o.notify(); // 通知T1 执行
            }
        },"T2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("T1启动了》。。。。");
        new Thread(() ->{
            synchronized (o){
                for (int i = 0; i < 10; i++) {
                    withVolatile.add(new Object());
                    System.out.println("add : "+i);

                    if(withVolatile.size() == 5){
                        o.notify();
                        try { // 让出锁 让T2 执行
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"T1").start();
    }
}
