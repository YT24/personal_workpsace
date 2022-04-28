package com.example.bserver.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestController {


    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        map.put("v1","k1");

        ConcurrentHashMap cmap = new ConcurrentHashMap<>();
        cmap.put("v1","k1");
        cmap.get("v1");
    }
}
