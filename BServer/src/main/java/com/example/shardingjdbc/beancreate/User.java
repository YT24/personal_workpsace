package com.example.shardingjdbc.beancreate;

import com.example.shardingjdbc.entity.UserTo;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@ToString
@Slf4j
@Component
public class User {

    private Long id;

    private String name;

    @Autowired
    private UserTo userTo;

    public User() {
        this.id = 10L;
        this.name = "kk";
       log.info("构造方法： id: {}, name: {}",id,name);
    }

    @PostConstruct
    public void init(){
        log.info("user - init 方法执行");
    }
}