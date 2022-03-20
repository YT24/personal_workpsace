package com.example.yangt.middleWare.redis.lru;


import java.util.HashMap;

public class LruCache{
    HashMap<Integer, Node> map;
    DoubleLinkedList cache;
    int cap;

    public LruCache(HashMap<Integer, Node> map, DoubleLinkedList cache, int cap) {
        this.map = map;
        this.cache = cache;
        this.cap = cap;
    }

    public void put(int key,int val){
        Node newNode = new Node(key,val);
        if(map.containsKey(key)){
            cache.delete(map.get(key));
            map.put(key,newNode);
            cache.addFist(newNode);
        }else{
            if(map.size()==cap) {
                int k = cache.deleteLast();
                map.remove(k);
            }
                cache.addFist(newNode);
                map.put(key,newNode);
        }
    }

    public int get(int key){

        if(!map.containsKey(key)) return -1;
        int val = map.get(key).val;
        put(key,val);

        return val;
    }
}


// head recently use
//tail lru
class DoubleLinkedList{

    private Node head;
    private Node tail;

    public DoubleLinkedList() {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.pre = head;
    }


    public void addFist(Node node){
        node.next = head.next;
        node.pre = head;

        head.next.pre = node;
        head.next = node;
    }

    public int delete(Node node){
        int key = node.key;
        node.next.pre = node.pre;
        node.pre.next = node.next;
        return key;
    }

    public int deleteLast(){

        if(head.next == tail) return -1;
        return delete(tail.pre);

    }

}

