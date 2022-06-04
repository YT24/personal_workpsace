package com.example.bserver.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NoticeEventListener implements ApplicationListener<NoticeEvent> {

    @Async
    @Override
    public void onApplicationEvent(NoticeEvent noticeEvent) {
        System.out.println(Thread.currentThread().getName() + "线程处理事件");
        System.out.println(noticeEvent);
    }
}
