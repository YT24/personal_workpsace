package com.example.aserver.entity;

import com.example.aserver.function.ExceptionFunction;

public class ExceptionUtils {

    public static ExceptionFunction isTure(boolean b){

        return (errorMessage) -> {
            if (b){
                throw new RuntimeException(errorMessage);
            }
        };
    }

    public static void main(String[] args) {
        isTure(true).throwExpection("123456");
    }
}
