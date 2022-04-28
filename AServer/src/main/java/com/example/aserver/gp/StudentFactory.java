package com.example.aserver.gp;

import org.springframework.beans.factory.FactoryBean;

public class StudentFactory implements FactoryBean<StudentFactory> {
    @Override
    public StudentFactory getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
