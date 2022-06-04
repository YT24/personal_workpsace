package com.example.bserver.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Base base = new Extended();
//        System.out.println(Base.FIELD_A);
//        System.out.println(Base.FIELD_B);
//        System.out.println(base.FIELD_C);
//        base.methodB();

        SendMsg sendMsg = new SendMsg();
        Thread t = new Thread(sendMsg);
        t.start();

        SendEmail sendEmail = new SendEmail();
        sendEmail.start();
    }
}
