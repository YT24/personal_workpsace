package com.example.yangt.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        RunThread t1 = new RunThread();
        RunThread t2 = new RunThread();
        Thread thread1 = new Thread(t1,"A");
        Thread thread2 = new Thread(t2,"B");
        thread1.start();
        thread1.join();
        thread2.start();

        CallableThread callableThread = new CallableThread();
        FutureTask<String> ft=new FutureTask<String>(callableThread);
        new Thread(ft).start();
        System.out.println(ft.get());

    }
}
