package com.example.yangt.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，synchronized，ReentrantLock
 */
public class LockDemo {

    public static void main(String[] args) {
        lock2();
    }

    public static void lock1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("synchronized 第1次获取锁，这个锁是：" + this);
                    int index = 1;
                    while (true) {
                        synchronized (this) {
                            System.out.println("synchronized 第" + (++index) + "次获取锁，这个锁是：" + this);
                        }
                        if (index == 10) {
                            break;
                        }
                    }
                }
            }
        }).start();
    }

    public static void lock2(){
        ReentrantLock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("ReentrantLock 第1次获取锁，这个锁是：" + lock);
                    int index = 1;
                    while (true) {
                        try {
                            lock.lock();
                            System.out.println("ReentrantLock 第" + (++index) + "次获取锁，这个锁是：" + lock);

                            try {
                                Thread.sleep(new Random().nextInt(200));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (index == 10) {
                                break;
                            }
                        } finally {
                            lock.unlock();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
