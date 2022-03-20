package com.example.aserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class YtController {

    @GetMapping("test")
    public void test() throws InterruptedException {
        List<String> stringList = new ArrayList<>();
        while (true) {
            stringList.add("1234567890-");
            TimeUnit.SECONDS.sleep(10);
        }

    }

    @GetMapping("out/of/memory")
    public void out() throws InterruptedException {
        List<Dd> list = new ArrayList<>();

        Runtime run = Runtime.getRuntime();

        int i = 1;

        while (true) {
            Dd dd = new Dd();
            int[] arr = new int[1024 * 8];

            dd.setList(arr);
            list.add(dd);
            if (i++ % 1000 == 0) {
                System.out.print("i=" + i);
                System.out.print("最大内存=" + run.maxMemory() / 1024 / 1024 + "M,");
                System.out.print("已分配内存=" + run.totalMemory() / 1024 / 1024 + "M,");
                System.out.print("剩余空间内存=" + run.freeMemory() / 1024 / 1024 + "M");
                System.out.println("最大可用内存=" + (run.maxMemory() - run.totalMemory() + run.freeMemory()) / 1024 / 1024 + "M");

                Thread.sleep(3 * 1000L);

            }

        }
    }

}

class Dd{
    private int[] list;

    public int[] getList() {
        return list;
    }

    public void setList(int[] list) {
        this.list = list;
    }
}
