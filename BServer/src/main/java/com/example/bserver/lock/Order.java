package com.example.bserver.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Order{
	Lock lock=new ReentrantLock();
	Condition condition1=lock.newCondition();
	Condition condition2=lock.newCondition();
	Condition condition3=lock.newCondition();
	int number=1;
	public void print1() {
		lock.lock();
		try {
			while(number!=1) {
				condition1.await();
			}
			System.out.println(Thread.currentThread().getName()+"正在执行");
			number=2;
			condition2.signalAll();
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
		
	}
	public void print2() {
		lock.lock();
		try {
			while(number!=2) {
				condition2.await();
			}
			System.out.println(Thread.currentThread().getName()+"正在执行");
			number=3;
			condition3.signalAll();
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
		
	}
	public void print3() {
		lock.lock();
		try {
			while(number!=3) {
				condition3.await();
			}
			System.out.println(Thread.currentThread().getName()+"正在执行");
			number=1;
			condition1.signalAll();
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
		
	}
}