package com.example.yangt.middleWare.redis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruDemo<K,V> extends LinkedHashMap<K,V> {

    private int capacity;//缓存坑位

    public LruDemo(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size()>capacity;
    }


    public static void main(String[] args) {
        LruDemo lruDemo = new LruDemo(3);

        lruDemo.put(1,"a");
        lruDemo.put(2,"b");
        lruDemo.put(3,"c");
        System.out.println(lruDemo.keySet());

        lruDemo.put(4,"e");
        lruDemo.put(4,"e");
        lruDemo.put(4,"e");
        System.out.println(lruDemo.keySet());

        lruDemo.put(4,"f");
        lruDemo.put(5,"e");
        System.out.println(lruDemo.keySet());


    }

}
