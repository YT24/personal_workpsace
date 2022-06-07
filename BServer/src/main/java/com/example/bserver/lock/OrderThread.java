package com.example.bserver.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderThread {
	
	public static void main(String[]args) {
		Order order=new Order();
		Thread thread1=new Thread(()->{
			for(int i=0;i<10;i++) {
				order.print1();
			}
		},"线程1");
		Thread thread2=new Thread(()->{
			for(int i=0;i<10;i++) {
				order.print2();
			}
		},"线程2");
		Thread thread3=new Thread(()->{
			for(int i=0;i<10;i++) {
				order.print3();
			}
		},"线程3");

		thread1.start();
		thread2.start();
		thread3.start();

	}
}




