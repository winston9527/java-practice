package com.winston.practice.jdk.thread.kongzhongwang;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第二题：现成程序中的Test类中的代码在不断地产生数据，
 * 然后交给TestDo.doSome()方法去处理，就好像生产者在不断地产生数据，消费者在不断消费数据。
 * 请将程序改造成有10个线程来消费生成者产生的数据，这些消费者都调用TestDo.doSome()方法去进行处理，
 * 故每个消费者都需要一秒才能处理完，程序应保证这些消费者线程依次有序地消费数据，
 * 只有上一个消费者消费完后，下一个消费者才能消费数据，下一个消费者是谁都可以，
 * 但要保证这些消费者线程拿到的数据是有顺序的。原始代码如下：
 */
public class Test2 {

    public static void main(String[] args) {

        BlockingQueue<String> list = new LinkedBlockingQueue<String>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    //使用锁 表示  在同一个时间  只有一个线程可以 消费
                    lock.lock();
                    String output = null;
                    try {
                        //使用阻塞队列 主要是避免线程运行时   生产者没有生产数据。
                        //一个取巧的办法 如果将 创建线程的代码  放到 生产者后面去，等生产者生产完数据  再消费的话  此处不用block queue 也可以
                        // 但是那样的话   如果生产者 耗时很久   问题就会很明显
                        output = TestDo.doSome(list.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + output);
                } finally {
                    lock.unlock();
                }
            });
        }


        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 10; i++) {  //这行不能改动
            String input = i + "";  //这行不能改动
            list.add(input);
        }
        executor.shutdown();
    }
}

//不能改动此TestDo类
class TestDo {
    public static String doSome(String input) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }
}
