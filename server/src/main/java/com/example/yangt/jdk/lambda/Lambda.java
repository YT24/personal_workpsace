package com.example.yangt.jdk.lambda;

import lombok.experimental.Accessors;

@Accessors
public class Lambda {

    private String username;

    private String password;

    private Integer age;

    public Lambda(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Lambda() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Lambda{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
