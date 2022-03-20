package com.example.yangt.thread.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t -----写入数据 key:"+key+" value:"+value.toString());
            TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t -----写入完成 key:"+key+" value:"+value.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t -----读取数据 key:"+key);
            TimeUnit.SECONDS.sleep(1);
            Object obj = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t -----读取完成 key:"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
public class ReadWriteDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=5 ; i++) {
            final int tmpInt = i;
            new Thread(() ->{
                myCache.put(tmpInt+"",tmpInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=5 ; i++) {
            final int tmpInt = i;
            new Thread(() ->{
                myCache.get(tmpInt+"");
            },String.valueOf(i)).start();
        }
    }
}
