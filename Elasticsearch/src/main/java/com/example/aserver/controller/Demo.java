package com.example.aserver.controller;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        String str2 = new String("str")+new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str2==str1);


        List<User> userList = new ArrayList<>();
    }
}

class User{
    private Integer id;

    private String userName;

    private String passWord;
}