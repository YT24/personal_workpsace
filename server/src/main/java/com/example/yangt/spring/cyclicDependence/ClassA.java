package com.example.yangt.spring.cyclicDependence;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
public class ClassA {

    private ClassB classB;


    public ClassA() {
        log.info("---creat A ---- success !!!");
    }
}
