package com.example.yangt.thread.lock.collection;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程不安全，多线程并发争抢同步资源类，未加锁
 * 线程安全，JUC 加锁
 *
 *
 *
 *
 *
 * list 底层：
 *      object类型数组，一块连续的存储空间，初始值 10 （java8）
 *      扩容方式： 第一次扩容 10 + 10/2 = 15  第二次扩容 15 + 15/2 = 22
 * hashmap 底层：
 *    数组：有遍历快，增删慢的特点 一块连续的存储空间 初始值 16
 *    链表：有增删快，遍历慢的特点
 *    扩容方式：（2 n次方）* 16
 */
public class NotSaleList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();//线程不安全
        List<String> list2 = new CopyOnWriteArrayList<>(); //线程安全
        Collections.synchronizedList(list);

        Set<String> setList = new HashSet<>();//线程不安全
        setList = new CopyOnWriteArraySet<>();//线程安全

        Map<String,Object> map = new HashMap<>();//线程不安全
        map = new ConcurrentHashMap<>();//线程安全

        map.put("1",1);
        for (int i = 0; i < 30 ; i++) {
            new Thread(() -> {
              list2.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            }).start();
        }

        Lock lock = new ReentrantLock();
        lock.lock();
    }
}
