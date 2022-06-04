package com.example.aserver.desginPattern.template;

public class ConcreteClass2 extends AbstractClass{
    @Override
    protected void customOperation() {
        // 具体模板2 业务逻辑
        System.out.println("具体模板2：customOperation()");
    }
}