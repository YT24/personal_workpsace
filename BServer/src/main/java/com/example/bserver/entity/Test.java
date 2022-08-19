package com.example.bserver.entity;

public class Test {
    public static void main(String[] args) {
        Base base = new Extended();
        System.out.println(Base.FIELD_A);
        System.out.println(Base.FIELD_B);
        System.out.println(base.FIELD_C);
        base.methodB();

    }
}
