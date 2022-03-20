package com.example.yangt.pojo;

public class Father {

    private String attr1 = attr1();
    private static String attr2 = attr2();

    static {
        System.out.println("father 静态代码块");
    }

    {
        System.out.println("father 普通代码块");
    }

    public Father() {
        System.out.println("father 构造方法");
    }

    public String attr1() {
        System.out.println("father 普通方法-attr1");
        return "4";
    }

    public static String attr2(){
        System.out.println("father 静态方法-attr2");
        return  "5";
    }
}

