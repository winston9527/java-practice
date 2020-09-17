package com.winston.practice.jdk.testcollections.map;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashTableVsConcurrentHashMap {


    static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws InterruptedException {
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        mapTest(new ConcurrentHashMap<>(919000));
        mapTest(new Hashtable<>(919000));
        executor.shutdown();
        /**
         *          class java.util.Hashtable时间:486，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:577，大小:919000
         *          class java.util.Hashtable时间:176，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:26，大小:919000
         *          class java.util.Hashtable时间:224，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:11，大小:919000
         *          class java.util.Hashtable时间:173，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:69，大小:919000
         *          class java.util.Hashtable时间:184，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:13，大小:919000
         *          class java.util.Hashtable时间:282，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:11，大小:919000
         *          class java.util.Hashtable时间:179，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:10，大小:919000
         *          class java.util.Hashtable时间:229，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:11，大小:919000
         *          class java.util.Hashtable时间:284，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:11，大小:919000
         *          class java.util.Hashtable时间:170，大小:919000
         *          class java.util.concurrent.ConcurrentHashMap时间:11，大小:919000
         *          class java.util.Hashtable时间:210，大小:919000
         *
         * 把其他影响的因素排除后（比如 线程池，初始化 集合容量，避免处理过程中扩容影响），根据上面的结果  看起来  效果是要好很多
         *
         */

    }

    private static void mapTest(Map<String, String> map) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            int finalJ = j;
            executor.submit(() -> {
                for (int i = 0; i < 10000; i++) {
                    String temp = finalJ + "" + i;
                    map.put(temp, temp);
                    map.get(temp);
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println(map.getClass() + "时间:" + (System.currentTimeMillis() - start) + "，大小:" + map.size());

    }


}
