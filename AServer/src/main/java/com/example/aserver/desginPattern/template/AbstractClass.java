package com.example.aserver.desginPattern.template;

public abstract class AbstractClass {
    // 共同的且繁琐的操作
    private void baseOperation() {
        // do something
        System.out.println("AbstractClass baseOperation 共同的且繁琐的操作");
    }

    // 由子类定制的操作
    protected abstract void customOperation();

    // 模板方法定义的框架
    public final void templateMethod() {
        /**
         * 调用基本方法，完成固定逻辑
         */
        baseOperation();
        customOperation();
    }

}