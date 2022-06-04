package com.example.aserver.cyclicDependence;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
@Data
@ToString
@Aspect
public class ClassA  implements BeanPostProcessor {

    private ClassB classB;


    public ClassA() {
        log.info("---creat A ---- success !!!");
    }


}
