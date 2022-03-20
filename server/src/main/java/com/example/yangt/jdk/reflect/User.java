package com.example.yangt.jdk.reflect;

public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public User() {
        System.out.println("User 構造器");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private void setUsername2(String username){
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
