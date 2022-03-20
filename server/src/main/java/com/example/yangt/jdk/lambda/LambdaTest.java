package com.example.yangt.jdk.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest {

    public static void main(String[] args) {
        List<Lambda> list = new ArrayList<>();
        Map<String,Lambda> map = new HashMap<>();
        Lambda lambda1 = new Lambda("kobe","24");
        lambda1.setAge(43);
        map.put("kobe",lambda1);
        Lambda lambda2 = new Lambda("james","23");
        lambda2.setAge(36);
        map.put("james",lambda2);
        Lambda lambda3 = new Lambda("kwy","2");
        lambda2.setAge(29);
        map.put("kwy",lambda3);
        list.add(lambda1);
        list.add(lambda2);

        /**
         * 遍历list集合
         */
        list.stream().forEach(lambda -> {
            if(lambda.getUsername().equals("kobe")){
                System.out.println("i am kobe bryant ....");
            }
            if(lambda.getUsername().equals("james")){
                System.out.println("i am little king ....");
            }
        });
        /**
         * 过滤
         */
        List<Lambda> newList = list.stream().filter(lambda -> lambda.getAge()>29 && lambda.getPassword().equals("24")).collect(Collectors.toList());
        System.out.println(newList);

        /**
         * 遍历map集合
         */
        map.forEach((key, value) -> {
            System.out.println(key + "---------------" + value.getPassword());
        });

        /**
         * 遍历list+map集合
         */
        List<Map<String,Lambda>> listMap = new ArrayList<>();
        listMap.add(map);
        listMap.stream().forEach(stringLambdaMap -> {
            stringLambdaMap.forEach((key,value)-> {
                System.out.println(key + "---------------" + value.getPassword());
            });
        });

        System.out.println("-------------------------------------------------------------------------------------------");

        Lambda[] lambdaArr = new Lambda[5];
        lambdaArr[0] = lambda1;
        lambdaArr[1] = lambda2;
        lambdaArr[2] = lambda3;
        System.out.println(lambdaArr.length);
        System.out.println(lambdaArr[lambdaArr.length-3]);

        Arrays.asList(lambdaArr).forEach(lambda -> {
            if(lambda!=null){
                System.out.println(lambda.getUsername()+"------"+lambda.getPassword());
            }

        });

        Arrays.asList(lambdaArr).forEach(System.out::println);


    }
}
