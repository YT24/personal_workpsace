package com.example.yangt.thread.juc;

public class Text {

    /**
     * 对于同步块的实现使用了monitorenter和monitorexit指令，
     *
     */
    public void aa1(){
        synchronized (this){
            System.out.println(2344);
        }
    }

    /**
     * 而同步方法则 是依靠方法修饰符上的ACC_SYNCHRONIZED来完成的。无论采用哪种方式，
     * 其本质是对一 个对象的监视器（monitor）进行获取，而这个获取过程是排他的，也就是同一时刻只能有一个 线程获取到由synchronized所保护对象的监视器。
     */
    public synchronized void aa2(){
            System.out.println(2344);
    }
}
