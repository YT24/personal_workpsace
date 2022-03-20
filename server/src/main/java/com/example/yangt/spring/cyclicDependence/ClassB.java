package com.example.yangt.spring.cyclicDependence;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
public class ClassB {


    private ClassA classA;

    public ClassB() {
        log.info("---creat B ---- success !!!");
    }


}
