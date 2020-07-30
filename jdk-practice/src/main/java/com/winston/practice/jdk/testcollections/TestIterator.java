package com.winston.practice.jdk.testcollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestIterator {

    public static void main(String[] args) {

        testRemove1();
        System.out.println();
        //testRemove2();
        System.out.println();
        testRemove3();
    }

    private static void testRemove1() {
        List<String> lists = new ArrayList<String>();
        lists.add("data1");
        lists.add("data2");
        lists.add("data3");
        lists.add("data4");

        System.out.println(lists);

        Iterator<String> it = lists.iterator();
        while (it.hasNext()) {
            String tmp = it.next();
            if ("data3".equals(tmp)) {
                lists.remove(tmp);
            }
        }
        System.out.println(lists);
    }

    private static void testRemove2() {
        List<String> lists = new ArrayList<String>();
        lists.add("data1");
        lists.add("data2");
        lists.add("data3");
        lists.add("data4");

        System.out.println(lists);

        Iterator<String> it = lists.iterator();
        while (it.hasNext()) {
            String tmp = it.next();
            if ("data2".equals(tmp)) {
                lists.remove(tmp);
            }
        }
        System.out.println(lists);
    }

    private static void testRemove3() {
        List<String> lists = new ArrayList<String>();
        lists.add("data1");
        lists.add("data2");
        lists.add("data3");
        lists.add("data4");

        System.out.println(lists);

        lists.removeIf(str -> "data2".equals(str));
        System.out.println(lists);
    }

}
