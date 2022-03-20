package com.example.yangt.jdk.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class User{
    private int id;
    private String username;
    private int age;

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class UserDemo {
    public static void main(String[] args) {
        User user1 = new User(10, "a", 11);
        User user2 = new User(20, "b", 12);
        User user3 = new User(30, "c", 13);
        User user4 = new User(40, "d", 14);
        User user5 = new User(5, "e", 15);
        Object object = Object.class.cast(user1);
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);

        Stream<String> stringStream = list.stream().filter(user -> user.getId() % 2 == 0).map(user -> user.getUsername().toUpperCase());
        list.stream().filter(user -> user.getId()>5).forEach(user -> {
            if(user.getId() == 10){
                System.out.println(user);
                return;
            }
        });
    }
}
