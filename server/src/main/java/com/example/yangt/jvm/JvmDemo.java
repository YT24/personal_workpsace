package com.example.yangt.jvm;

import com.example.yangt.pojo.Son;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JvmDemo {

    public static void main(String[] args) {
        Son son = new Son();
        System.out.print(ClassLayout.parseInstance(son).toPrintable());

    }
}
