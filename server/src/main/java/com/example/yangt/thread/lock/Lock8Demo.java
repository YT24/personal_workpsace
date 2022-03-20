package com.example.yangt.thread.lock;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws Exception{
        //System.out.println("sendEmail:"+this);
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------------发邮件给kobe-----------");
    }
    public static synchronized void sendMSG() throws Exception{
        //System.out.println("sendMSG:"+this);
        System.out.println("------------发短信给james-----------");
    }

    public void call() throws Exception{
        System.out.println("call:"+this);
        System.out.println("------------打电话给倪妮-----------");
    }
}

/**
 * 1，同一资源类如果有多个synchronized方法 某一时刻 只要有一个线程去访问其中的一个synchronized方法了，其他它线程再想访问其他的synchronized方法 只能等待
 * 即同一时间内只允许一个线程访问访问这些synchronized方法 锁的的是当前对象this （对象锁）
 *
 * 2，普通方法和同步锁无关，不枪锁
 *
 * 3，不同资源类 访问不同synchronized方法，锁不同
 *
 * 4，静态synchronized方法 锁的是整个Class（全局锁）
 *
 * synchronized 实现同步的基础：java 中每一个对象都可以作为锁
 * 具体表象为以下三种形式
 *   1，队医普通同步方法，锁的是当前实例对象
 *   2，对于同步方法块 锁定的是括号里的配置对象
 *   3，对于静态同步方法，锁定的是当前class对象
 */
public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
            new Thread(() ->{
                try {
                    phone.sendEmail();
                }catch (Exception e){
                    e.printStackTrace();
                }},"A").start();


            new Thread(() ->{try {
                //TimeUnit.SECONDS.sleep(1);
                //phone.sendMSG();
                //phone.sendMSG();
                phone.call();
            }catch (Exception e){
                e.printStackTrace();
            }},"B").start();

    }
}
