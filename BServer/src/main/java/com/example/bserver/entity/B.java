package com.example.bserver.entity;

public class B {

//    public static B b1 = new B();
//
//    public static B b2 = new B();

    static {
        System.out.println("静态块");
    }

    {
        System.out.println("构造块");
    }


    public static void main(String[] args) {
        B b3 = new B();
    }
}
