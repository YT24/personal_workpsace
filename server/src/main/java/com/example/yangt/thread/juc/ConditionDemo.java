package com.example.yangt.thread.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int number = 1; //A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();


    public void print5(){
        lock.lock();
        try{
            //判断
            while(number!=1){
                conditionA.await();
            }
            //干活
            for (int i = 1; i <=2 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 2;
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //判断
            while(number!=2){
                conditionB.await();
            }
            //干活
            for (int i = 1; i <=2 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 3;
            conditionC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //判断
            while(number!=3){
                conditionC.await();
            }
            //干活
            for (int i = 1; i <=2 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 1;
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author yt
 * @create 2021-01-18
 * 多个线程顺序调用 实现A->B->C
 * 三个线程启动如下：
 *   AA 打印5次 BB 打印10次 CC 打印15次
 *   接着
 *   AA 打印5次 BB 打印10次 CC 打印15次
 *   。。。。来10轮
 *
 *   1，高内聚低耦合前提下 线程操作资源类
 *   2，判断/通知/干活
 *   3，多线程交互中必须要防止现成的额虚假唤醒，也即（判断只能用while）
 *   4，标志位
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() ->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print10();
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}
