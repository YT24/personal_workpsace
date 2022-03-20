package com.example.yangt.abstractAndInterface;

public abstract class AbstractDemo {
    private String name;
    public static String age;
    private final String addr = "1234";
    protected String attr1;

     public abstract void test();

    public void test1(){
        System.out.println(123);
    };

}

class TestDemo extends AbstractDemo{

    @Override
    public void test() {
        System.out.println("test extends AbstractDemo: test method");
    }

    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        testDemo.test();
        testDemo.test1();
    }
}


