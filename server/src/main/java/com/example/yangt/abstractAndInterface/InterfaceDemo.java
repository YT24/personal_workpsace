package com.example.yangt.abstractAndInterface;

public interface InterfaceDemo {

    static String name = "kobe";
    final String password = "24";


    public abstract String login();


    public static void register(){
        System.out.println(21323454);
    }
    public default void register(Integer integer){
        System.out.println(integer);
    }

    public  void logout();
}

class In implements InterfaceDemo{

    @Override
    public String login() {
        return null;
    }

    @Override
    public void logout() {

    }

    public static void main(String[] args) {
        InterfaceDemo interfaceDemo = new In();
        InterfaceDemo.register();
        interfaceDemo.register(213243546);
    }
}

