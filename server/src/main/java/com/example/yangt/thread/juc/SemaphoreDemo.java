package com.example.yangt.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {


    public static void main(String[] args) {
        scrambleForParkingSpace();
    }

    /**
     * 模拟车位停车
     */
    private static void scrambleForParkingSpace() {
        Semaphore semaphore = new Semaphore(3);//模拟资源类 三个车位 六辆车
        for (int i = 1; i <=6; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢占到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
