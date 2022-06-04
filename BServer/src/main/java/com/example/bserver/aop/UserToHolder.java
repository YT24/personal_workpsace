package com.example.bserver.aop;

import com.example.bserver.entity.UserTo;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class UserToHolder {

    private static final ThreadLocal<UserTo> threadLocal = new ThreadLocal<>();

    public static UserTo getUserTo(){

        return threadLocal.get();
    }

    public static void setUserTo(UserTo userTo){
        threadLocal.set(userTo);
    }

    public static void remove(){
        threadLocal.remove();
    }

    public static void main(String[] args) {
        double f = 0.1;

        System.out.println(String.format("%.2f", f));

        Collections.synchronizedList(new ArrayList<>());
    }

}
