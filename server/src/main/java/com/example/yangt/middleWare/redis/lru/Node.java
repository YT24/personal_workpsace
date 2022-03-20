package com.example.yangt.middleWare.redis.lru;

public class Node {
    public int key,val;
    public Node next,pre;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}