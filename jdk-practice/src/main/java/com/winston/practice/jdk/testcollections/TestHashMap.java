package com.winston.practice.jdk.testcollections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHashMap {
    public static void main(String[] args) {

        testHashMap();

        System.out.println(hash("1"));
        System.out.println(hash("2"));
        System.out.println(hash("3"));
        System.out.println(hash("4"));
        System.out.println(hash("5"));
        System.out.println(hash("6"));
        System.out.println(hash("7"));
        System.out.println(hash("8"));
        System.out.println(hash("11"));
        System.out.println(hash("13"));
        System.out.println(hash("43"));
        System.out.println(hash("43"));
        System.out.println(hash("12"));
    }


    static void testHashMap() {

        IntStream.range(1, 1000)
          .mapToObj(i -> i + "")
          .collect(Collectors.groupingBy(i -> hash(i.hashCode())&15))
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue().size() > 1)
          .collect(Collectors.toList())
          .forEach(
            s-> {
//                System.out.println("key"+s.getKey());
//                System.out.println("value"+s.getValue());
            }
          );

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 132; i+=11) {
            map.put(i+"", "ok"+i);
            map.get("222");
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
