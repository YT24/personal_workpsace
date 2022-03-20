package com.example.yangt.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class ABADemo {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,1);


    public static void main(String[] args) {
        log.info("==================ABA问题产生====================");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
               log.info("==========================="+atomicReference.compareAndSet(100,2021)+"\t"+atomicReference.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
        log.info("=========================ABA问题解决================");


        new Thread(()->{
            int stam = atomicStampedReference.getStamp();
            log.info("=====T3============第一次版本号："+stam);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,2021,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(2021,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            log.info("=====T3============ABA后的版本号:"+atomicStampedReference.getStamp());
        },"T3").start();
        new Thread(()->{
            int stam = atomicStampedReference.getStamp();
            log.info("=====T4============版本号："+stam);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = atomicStampedReference.compareAndSet(100,2021,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
           log.info("=====T4=============执行结果："+flag);
        },"T4").start();
    }
}
