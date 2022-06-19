package com.example.shardingjdbc.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @Author humorchen
 * @Date 2021/10/25
 */
@Getter
@ToString
public class NoticeEvent extends ApplicationEvent {

    private String title;

    private String content;

    public NoticeEvent(String title,String content) {
        super(title);
        this.title = title;
        this.content = content;
    }


}

