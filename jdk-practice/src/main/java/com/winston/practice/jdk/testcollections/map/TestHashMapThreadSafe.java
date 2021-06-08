package com.winston.practice.jdk.testcollections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHashMapThreadSafe {

    static ExecutorService service = Executors.newFixedThreadPool(10);

    static Map<String,String> map = new HashMap<>();
    static CountDownLatch count = new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 10; j++) {
            service.submit(()->{
                for (int i=0;i<100;i++){
                    map.put(Thread.currentThread().getName()+"--"+i,""+i);
                }
                count.countDown();
            });
        }
        System.out.println("提交完成，开始等待");
        count.await();
        System.out.println("执行完成");
        System.out.println(map);
        System.out.println(map.size());
        service.shutdown();

        /**
         *
         * 按理来说map的size 应该会是 1000 但是运行下来会发现  只有900多  就是因为如下代码
         * 线程1 和 线程2 同时检查 发现要保存的位置 还没有值，导致线程1 保存到该位置后，线程2 继续保存到该位置，所以就会造成元素丢失
         * code 1：
         *
         if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
         else {

         code 2:
         if ((e = p.next) == null) {
         p.next = newNode(hash, key, value, null);
         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         treeifyBin(tab, hash);
         break;
         }
         *
         */
    }
}
