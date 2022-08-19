package com.example.bserver.entity;

public class Base {
    public static final String FIELD_A = "base_field_a";
    public static String FIELD_B = "base_field_b";
    public String FIELD_C = "base_field_c";

    static {
        System.out.println("base_cinit-static");
    }

    public Base() {
        System.out.println("base_init-cons");
    }

    public static void methodA() {
        System.out.println("base_method_a");
    }

    public void methodB() {
        System.out.println("base_method_b");
    }
}