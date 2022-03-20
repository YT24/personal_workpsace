package com.example.yangt.pojo;

public class Son extends Father {
    private String attr11 = attr11();
    private static String attr22 = attr22();

    static {
        System.out.println("son 静态代码块");
    }

    {
        System.out.println("son 普通代码块");
    }

    public Son() {
        System.out.println("son 构造方法");
    }

    public String attr11() {
        System.out.println("son 普通方法-attr11");
        return "9";
    }

    public static String attr22(){
        System.out.println("son 静态方法-attr22");
        return  "10";
    }


    public static void main(String[] args) {
        Son son = new Son();
    }
}
