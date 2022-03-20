package com.example.yangt.thread.lock.consumerAndproducer;


class AirCondition{
    private int number = 0;
    public synchronized void increment() throws Exception{
        // 判断
        while (number !=0){
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        //通知
        this.notifyAll();
    }
    public synchronized void decrement() throws Exception{
        while (number ==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        //通知
        this.notifyAll();
    }
}

/**
 * synchronized 同步块中 判断必须用while
 */

public class ConsumerDemo {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(() ->{
            try {
                for (int i = 0; i <10 ; i++) {

                    airCondition.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(() ->{
            try {
                for (int i = 0; i <10 ; i++) {
                    airCondition.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(() ->{
            try {
                for (int i = 0; i <10 ; i++) {

                    airCondition.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(() ->{
            try {
                for (int i = 0; i <10 ; i++) {
                    airCondition.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}
