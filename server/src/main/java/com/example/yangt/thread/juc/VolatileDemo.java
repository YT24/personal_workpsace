package com.example.yangt.thread.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS概念
 * <mark>CAS的英文为Compare and Swap 翻译为比较并交换。</mark>
 *
 * CAS机制中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。
 *
 * 更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。
 *
 * CAS的全称为Compare-And-Swap ,它是一条CPU并发原语.
 *
 * 它的功能是判断内存某个位置的值是否为预期值,如果是则更新为新的值,这个过程是原子的.
 *
 *
 * UnSafe是CAS的核心类 由于Java 方法无法直接访问底层 ,需要通过本地(native)方法来访问,UnSafe相当于一个后面,基于该类可以直接操作特额定的内存数据.UnSafe类在于sun.misc包中,
 * 其内部方法操作可以向C的指针一样直接操作内存,因为Java中CAS操作的助兴依赖于UNSafe类的方法.
 *
 *
 * CAS缺点
 * 循环时间长开销很大
 * CAS 通常是配合无限循环一起使用的，我们可以看到 getAndAddInt 方法执行时，如果 CAS 失败，会一直进行尝试。如果 CAS 长时间一直不成功，可能会给 CPU 带来很大的开销。
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待前面20个线程全部执行完，在用main线程取得最终的结果值是多少
        while (Thread.activeCount() > 2) {
            //main 线程 GC线程
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type volatile number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type final number value:" + myData.atomicInteger);

        /**
         * main	 int type final number value:18310
         * main	 AtomicInteger type final number value:20000
         */
    }
}


class MyData {
    volatile int number = 0;

    public void addT060() {
        this.number = 60;
    }

    //number加了volatile关键字
    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}