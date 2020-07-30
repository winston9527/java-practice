package com.winston.practice.jdk.testcollections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * java.util.List 是有序集合也成为 列表 可以重复。 它是Collection的子接口 主要实现类 java.util.ArrayList
 * java.util.LinkedList java.util.Vector
 *
 * @Author Winston
 * @Version 1.0 2018年9月17日 下午4:11:54
 */
public class TestList {

    public static void main(String[] args) {

        testThreadSafe();
    }

    static void testThreadSafe() {

        CountDownLatch latch = new CountDownLatch(2);

        List<String> list1 = new ArrayList<String>();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    list1.add("A" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    list1.add("B" + i);
                }
            }
        });
        t1.start();
        latch.countDown();
        t2.start();
        latch.countDown();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list1);
        System.out.println(list1.size());
        /**
         * 结果一 [A0, A1, A2, A3, A4, A5, A6, A7, B0, A8, null, B1, B2, B3, B4, B5, B6, B7, B8, B9]
         */
        // 其中出现为空的原因 在于新增的时候 elementData[size++] = e; 分为两步
        // elementData[size] = e; ①
        // size++; ②
        // 当线程A执行 了步骤①还未来得及执行步骤②的时候 此时size为n elementData[n] = A(n) 则
        // cpu切换到线程B执行。由于此时size还没加1 导致线程B执行的时候 elementData[n] = B(n).
        // 然后线程B 执行size++ 此时 size变为 n+1并且 elementData[n+1]=null。最后CPU再切换回A线程
        // 执行第②步。导致 size变为 n+2 。后面在追加进来的元素 执行步骤①的时候
        // 就将新元素放到了n+2的位置上。所以n+1的位置就永远为null了

        /**
         * 结果二 Exception in thread "Thread-0"
         * java.lang.ArrayIndexOutOfBoundsException: 10 at
         * java.util.ArrayList.add(ArrayList.java:463) at
         * com.winston.javase.testcollections.TestList$1.run(TestList.java:83)
         * at java.lang.Thread.run(Thread.java:748) [A0, A1, B0, A2, A3, A4, A5,
         * B2, A6, B3, null, B4, B5, B6, B7, B8, B9] 17
         */

        // if (minCapacity - elementData.length > 0)

        // 如果当前 列表容量为10个 数组目前里面包含 9个元素 下标是 0至8 当A线程和B线程同时加入新元素
        // 当线程A 执行完以上代码 此时10 - 10 刚好等于0 当数组容量刚好达到临界值的时候 不会进行扩容。此时还未将新元素放到数组
        // CPU切换 到线程B，线程B 在以上代码 也刚好达到临界值 不会扩容 然后线程B继续执行 elementData[size++] = e;
        // 此时B线程元素刚好放到数组里面下标为9的位置 并且size变为10
        // 再切换回线程A。此时线程A直接执行elementData[size++] = e 因为此时size 为10 elementData[10]
        // 引发数组下标越界异常

    }

}
