package com.winston.practice.jdk.testcollections;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {

        testHashMap();
    }


    static void testHashMap() {

        Map<String, String> map = new HashMap<>();
        map.put("222", "ok");
        map.get("222");

    }
}
