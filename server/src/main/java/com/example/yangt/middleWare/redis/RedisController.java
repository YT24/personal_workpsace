package com.example.yangt.middleWare.redis;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 可用于接口限流
     * @return
     */
    @GetMapping("/park")
    public String park(){
            RSemaphore rSemaphore = redissonClient.getSemaphore("yt-lock");
        try {
            rSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "-park";


    }
    @GetMapping("/go")
    public String go(){
        RSemaphore rSemaphore = redissonClient.getSemaphore("yt-lock");
        try {
            rSemaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "go";


    }

    /**
     * 下班 50 人走完 关门
     * @return
     */
    @GetMapping("/off/{id}")
    public String goOffWork(){
        RCountDownLatch count = this.redissonClient.getCountDownLatch("work");
        count.countDown();
        return "下班";
    }

    @GetMapping("/close/{id}")
    public String closeDoor(@PathVariable String id){
        RCountDownLatch count = this.redissonClient.getCountDownLatch("work");
        count.trySetCount(10);
        try {
            count.await();//等到闭锁完成 即count.getCount() =0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "关门了";
    }


    @GetMapping("/lock1/{id}")
    public String secondKill1(@PathVariable String id){
        RLock rLock = this.redissonClient.getLock("phone-xr");
        try {
            if(!rLock.isLocked()){
                System.out.println("开始上锁 id:"+id);
                rLock.lock(30, TimeUnit.SECONDS);
                System.out.println("上锁成功了 id:"+id);
                TimeUnit.SECONDS.sleep(30);
            }else{
                System.out.println("锁不到 锁不到。。。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           /* if(rLock.isHeldByCurrentThread()){
                rLock.unlock();
            }*/
        }


        return "success";
    }

    @GetMapping("/lock2/{id}")
    public String secondKill2(@PathVariable String id){
        RLock rLock = this.redissonClient.getLock("phone-xr");
        try {
                System.out.println("开始上锁 id:"+id);
                rLock.lock(100, TimeUnit.SECONDS);
                System.out.println("上锁成功了 id:"+id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rLock.isHeldByCurrentThread()){//如果不是加锁的那个线程，无法解锁
                rLock.unlock();
            }
        }


        return "success";
    }



    @GetMapping("redis/lock")
    public boolean testRedisLock(){
        RLock lock = redissonClient.getLock("test");
        boolean flag = false;
        try {
            System.out.println("开始上锁 id:"+Thread.currentThread().getId());
            flag = lock.tryLock(30, TimeUnit.SECONDS);
            System.out.println("开始上锁 id:"+Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

        return flag;
    }

    @Transactional
    @GetMapping("lock")
    public boolean testUnRedisLock(){
        RLock lock = redissonClient.getLock("test");
        lock.lock();
        lock.unlock();
        lock.unlock();

        return true;
    }

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("123");
        list.add("1234");
        list.add("12345");
        list.add("123456");
        System.out.println(list);
    }

}
