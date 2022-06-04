package com.example.bserver.entity;

class Extended extends Base {
    public static final String FIELD_A = "extended_field_a";
    public static String FIELD_B = "extended_field_b";
    public String FIELD_C = "extended_field_c";

    static {
        System.out.println("extended_cinit-static");
    }

    public Extended() {
        System.out.println("extended_init-cons");
    }

    public static void methodA() {
        System.out.println("extended_method_a");
    }

    @Override
    public void methodB() {
        super.methodB();
        System.out.println("extended_method_b");
    }
}