package com.example.yangt.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class UserTest {

    //冒泡排序
    public static void BubbleSort(int[] arr) {
        int temp;//定义一个临时变量
        for (int i = 0; i < arr.length - 1; i++) {//冒泡趟数
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 二分法
     *
     * @param array
     * @param start
     * @param end
     * @param findValue
     * @return
     */
    public static int searchRecursive(int[] array, int start, int end, int findValue) {
        if (array == null) {
            return -1;
        }
        if (start <= end) {
            //中间位置
            int middle = (start + end) / 2;
            //中值
            int middleValue = array[middle];
            if (findValue == middleValue) {
                //与中值相等就直接返回
                //return middle;
                return middleValue;
            } else if (findValue < middleValue) {
                //目标值小于中值，在中值前面找（这里调用了二分法的方法）
                return searchRecursive(array, start, middle - 1, findValue);
            } else {
                //目标值大于中值，在中值后面找（这里调用了二分法的方法）
                return searchRecursive(array, middle + 1, end, findValue);
            }
        } else {
            //返回-1，查找失败
            return -1;
        }
    }


    public static void main(String[] args) {
        /*int arr[] = new int[]{1, 6, 2, 2, 5};
        BubbleSort(arr);
        System.out.println(searchRecursive(arr,0,arr.length-1,arr.length));
        System.out.println(Arrays.toString(arr));*/

       /* User[] users = new User[4];
        User u = new User();
        u.setUsername("12345671234567");
        users[0] = u;
        String str = "1234567";
        List<String> list = new ArrayList<>();
        list.add(str);
        System.out.println(users.length);
        System.out.println(str.length());
        System.out.println(list.size());
        System.out.println(users[0]);*/


        try {
            Class userClass = Class.forName("com.example.yangt.jdk.reflect.User");
            /**
             * 反射获取构造方法
             */
            Constructor constructor = userClass.getConstructor();
            Constructor constructor1 = userClass.getConstructor(String.class);
            /**
             * 创建实例
             */
            Object object = constructor.newInstance();
            Object object1 = constructor1.newInstance("kobe");
            /**
             * 獲取setUsername方法  方法类型 public
             */
            Method setUsernameMethod = userClass.getMethod("setUsername",String.class);
            /**
             * 執行方法   方法类型 public
             */
            setUsernameMethod.invoke(object,"kobe");

            /**
             * 获取setusername2方法  方法类型 private
             */
            Method setUsername2PrivateMethod = userClass.getDeclaredMethod("setUsername2", String.class);
            setUsername2PrivateMethod.setAccessible(true);
            setUsername2PrivateMethod.invoke(object,"yangte1234");



            Method getUsernameMethod = userClass.getMethod("getUsername");
            String username = (String) getUsernameMethod.invoke(object);
            System.out.println(username);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
