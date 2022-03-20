package com.example.yangt.schedule;

import java.time.LocalDateTime;

import com.example.yangt.schedule.thread.Thread_001;
import com.example.yangt.schedule.thread.Thread_002;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 三、多线程定时任务
 *
 * @author 16602
 */
//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
//@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class ThreadsSchedule {

    @Async
    //@Scheduled(fixedDelay = 2000)  //间隔1秒
    public void first() throws InterruptedException {
        Thread thread = new Thread(new Thread_001(), "thread_001");
        thread.setName("thread_001");
        thread.start();
        System.err.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "  name:" + thread.getName());

        Thread.sleep(2000 * 10);
    }

    @Async
    //@Scheduled(fixedDelay = 4000)
    public void second() {
        Thread thread = new Thread(new Thread_002(), "thread_002");
        thread.setName("thread_002");
        thread.start();
        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "  name:" + thread.getName());
    }
}