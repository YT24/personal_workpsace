package com.example.yangt.thread.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.*;

@RestController
@Slf4j
public class ThreadPoolController {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;


    @RequestMapping(path = "pool",method = RequestMethod.GET)
    public void threadPoolTest(){
        for (int i = 0; i <6 ; i++) {

            threadPoolExecutor.execute(() ->{
                log.info(Thread.currentThread().getName()+" 干活了");
            });
            threadPoolExecutor.submit(() ->{
                log.info(Thread.currentThread().getName()+" 干活了");
            });
        }
        threadPoolExecutor.shutdownNow();

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorServiceFixed = Executors.newFixedThreadPool(5);//指定线程数的池子
        ExecutorService executorServiceCached = Executors.newCachedThreadPool();//n个线程的池子
        ExecutorService executorServiceSingle = Executors.newSingleThreadExecutor();// 一个线程的池子
        for (int i = 0; i <10 ; i++) {

        }

        for (int i = 0; i <5 ; i++) {

            Future<String> stringFuture0 = executorServiceCached.submit(new CallableThread());
            Future<String> stringFuture1 = executorServiceSingle.submit(new CallableThread());
            Future<String> stringFuture2 = executorServiceFixed.submit(new CallableThread());
            System.out.println("executorServiceCached 返回值："+stringFuture0.get());
            System.out.println("executorServiceSingle 返回值："+stringFuture1.get());
            System.out.println("executorServiceCached 返回值："+stringFuture2.get());
        }
        executorServiceFixed.shutdown();
        executorServiceCached.shutdown();
        executorServiceSingle.shutdown();
       /* try {
            FutureTask<String> futureTask = new FutureTask(new CallableThread());
            Thread thread = new Thread(futureTask);
            thread.start();
            log.info("-----> callable result:"+futureTask.get());
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
