package com.example.yangt.thread.juc;




import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        callGragon();
    }
    private static void callGragon() throws InterruptedException {
        // 当有7个线程进入，执行ServenDragon 线程方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new SevenDragon());//收集龙珠
        for (int i = 1; i <=7 ; i++) {
            final int tempInt = i;
            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                System.out.println("线程："+Thread.currentThread().getName()+"\t收集到第："+tempInt+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
