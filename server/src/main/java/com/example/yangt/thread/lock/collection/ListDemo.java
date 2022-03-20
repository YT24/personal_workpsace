package com.example.yangt.thread.lock.collection;

import org.apache.commons.collections4.set.ListOrderedSet;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ListDemo {

    public static void main(String[] args) {
        /**
         * Vector：数组实现，重量级 (线程安全、使用少)
         * 1，是按照顺序将元素存储（从下表为0开始），删除元素时，删除操作完成后，需要使部分元素移位，默认的初始容量都是10.
         */
        List<String> vector = new Vector();
        vector.add("1");
        /**
         * ArrayList;数组实现，查询快，增删慢，轻量级；(线程不安全)
         * 1，是按照顺序将元素存储（从下表为0开始），删除元素时，删除操作完成后，需要使部分元素移位，默认的初始容量都是10.
         */
        List<String> arrayList = new ArrayList<>();
        arrayList.add("1");

        /**
         * LinkedList：双向链表实现，增删快，查询慢 (线程不安全)
         */
        List<String> linkedList = new LinkedList<>();


        Set<String> set = new HashSet<>();

        /**
         * HashTable
         * 底层数组+链表实现，无论key还是value都不能为null，
         * 线程安全，
         * 实现线程安全的方式是在修改数据时锁住整个HashTable，效率低，ConcurrentHashMap做了相关优化
         * 初始size为11，扩容：newsize = olesize*2+1
         */
        Map<String,String> hashtable = new Hashtable<>();
        hashtable.put("1","v");

        /**
         * ConcurrentHashMap
         * 1.7 sgment 分段锁
         */
        Map<String, String> concurr = new ConcurrentHashMap<>();
        concurr.put("1","1");
        concurr.put("2","1");
        concurr.put("3","1");
        concurr.put("4","1");
        concurr.put("5","1");

        concurr.get("5");

        /**
         * HashMap
         * 底层数组+链表实现，可以存储null键和null值，
         * 线程不安全
         * 初始size为16，扩容：newsize = oldsize*2，size一定为2的n次幂
         * 扩容针对整个Map，每次扩容时，原来数组中的元素依次重新计算存放位置，并重新插入
         */
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("1","v");









        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new TreeSet<>();
        Set<String> set3 = new LinkedHashSet<>();
        Set<String> set4 = new ListOrderedSet<>();


        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();


        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new Hashtable<>();
        Map<String,Object> map3 = new ConcurrentHashMap<>();

        map2.put("key","value");









    }
}
