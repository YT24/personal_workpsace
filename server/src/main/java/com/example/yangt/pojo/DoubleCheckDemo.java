package com.example.yangt.pojo;

import org.apache.poi.ss.formula.functions.T;

public class DoubleCheckDemo {
    private static T t;

    public static T getT(){
        if(t == null){
            synchronized (DoubleCheckDemo.class){
                if(t == null){
                    return new T();
                }
            }
        }
        return t;
    }
}
