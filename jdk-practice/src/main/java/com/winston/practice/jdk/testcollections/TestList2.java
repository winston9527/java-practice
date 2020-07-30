package com.winston.practice.jdk.testcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * java.util.List 是有序集合也成为 列表 可以重复。
 * 它是Collection的子接口 主要实现类
 * java.util.ArrayList
 * java.util.LinkedList
 * java.util.Vector
 *
 * @Author Winston
 * @Version 1.0 2018年9月17日 下午4:11:54
 */
public class TestList2 {

    public static void main(String[] args) {

        //testArrayList();
        for (int i = 0; i < 20; i++) {
            //compareArrayListLinkedList();
        }
        //testThreadSafe();

        testThreadSafeLinkedList();

    }

    private static void compareArrayListLinkedList() {
        List<String> list = new ArrayList<String>();
        List<String> linkedList = new LinkedList<String>();
        linkedList.add("西");
        list.add("西");
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            // list.add("西");
            linkedList.add(1, "西");
        }
        long s2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(1, "西");
            // linkedList.add("西");
        }
        long s3 = System.currentTimeMillis();

        System.out.println("linkedList:" + (s2 - s1) + "||" + "ArrayList:" + (s3 - s2));

    }

    private static void testArrayList() {
        /**
         * ArrayList 动态数组 。可以加入null元素 线程不安全
         * 数组扩容：
         * 将数组的长度设置为原来长度的1.5倍。oldCapacity >> 1 相当于 oldCapacity/2
         *  int newCapacity = oldCapacity +(oldCapacity >> 1);
         * 算得的新长度  使用Arrays.copyOf创建一个新的数组  并且将原来的elementData复制进去
         * elementData = Arrays.copyOf(elementData,newCapacity);
         *
         */
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("AA");
        }
        list.add("BB");
        System.out.println(list);
        //1、List 新增的方法 public void add(int index, Object e) 在指定位置插入一个元素，该位置原来的元素依次后移.
        //参数index需要大于等于0 并且小于列表当前元素的个数  即是列表的size
        //先对原来的数组容量检查  是否需要扩容。
        //然后调用System.arraycopy(((Object) (elementData)), index, ((Object) (elementData)), index + 1, size - index); 将数组elementData 的index位元素 复制到index+1位置。并且后面依次覆盖
        //最后 elementData[index] = element; 将第index位置的元素  设置为当前插入的元素。由于该过程  需要移动index后面的所有元素。所以性能很差
        list.add(1, "CC");
        System.out.println(list);
    }


    static void testThreadSafe() {

        List<String> list1 = new ArrayList<String>();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list1.add("A" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list1.add("B" + i);
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list1);
        System.out.println(list1.size());
        /**
         * 结果一
         * [null, B0, B1, B2, B3, A1, B4, A2, A3, A4, A5, A6, null, B5, A8, null, A9, B7, B8, B9]
         */
        //其中出现为空的原因 在于新增的时候  elementData[size++] = e; 分为两步
        //elementData[size] = e;    ①
        //size++;							   ②
        //当线程A执行 了步骤①还未来得及执行步骤②的时候 此时size为n elementData[n] = A(n) 则  cpu切换到线程B执行。由于此时size还没加1 导致线程B执行的时候  elementData[n] = B(n).
        //然后线程B 执行size++  此时  size变为 n+1并且 elementData[n+1]=null。最后CPU再切换回A线程  执行第②步。导致 size变为 n+2 。后面在追加进来的元素 执行步骤①的时候  就将新元素放到了n+2的位置上。所以n+1的位置就永远为null了

        /**
         * 结果二
         * Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 10
         at java.util.ArrayList.add(ArrayList.java:463)
         at com.winston.javase.testcollections.TestList$1.run(TestList.java:83)
         at java.lang.Thread.run(Thread.java:748)
         [A0, A1, B0, A2, A3, A4, A5, B2, A6, B3, null, B4, B5, B6, B7, B8, B9]
         17
         */

        // if (minCapacity - elementData.length > 0)

        //如果当前 列表容量为10个  数组目前里面包含 9个元素  下标是 0至8 当A线程和B线程同时加入新元素
        //当线程A 执行完以上代码  此时10 - 10 刚好等于0 当数组容量刚好达到临界值的时候 不会进行扩容。此时还未将新元素放到数组
        //CPU切换 到线程B，线程B 在以上代码  也刚好达到临界值  不会扩容  然后线程B继续执行 elementData[size++] = e; 此时B线程元素刚好放到数组里面下标为9的位置 并且size变为10
        //再切换回线程A。此时线程A直接执行elementData[size++] = e 因为此时size 为10 elementData[10] 引发数组下标越界异常

    }

    static void testThreadSafeLinkedList() {

        List<String> list1 = new LinkedList<String>();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("开始");
                for (int i = 0; i < 10; i++) {
                    list1.add("A" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("开始");
                for (int i = 0; i < 10; i++) {
                    list1.add("B" + i);
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Iterator<String> it = list1.iterator();
        while (it.hasNext()) {
            String tmp;
            if ((tmp = it.next()) != null) {
                System.out.print(tmp + "  ");
            } else {
                break;
            }
        }
        System.out.println();
        System.out.println(list1.size());
    }


    static void testSynchronizedList() {

        List<String> list1 = Collections.synchronizedList(new ArrayList<String>());
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("开始");
                for (int i = 0; i < 10; i++) {
                    list1.add("A" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("开始");
                for (int i = 0; i < 10; i++) {
                    list1.add("B" + i);
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator<String> it = list1.iterator();
        while (it.hasNext()) {
            String tmp;
            if ((tmp = it.next()) != null) {
                System.out.print(tmp + "  ");
            } else {
                break;
            }
        }
        System.out.println();
        System.out.println(list1.size());
    }
}
