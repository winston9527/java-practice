package com.winston.practice.jdk.thread.producerconsumer.model1;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产者消费者模式：使用Object.wait() / notify()方法实现
 */
@Slf4j
public class ProducerConsumer {

    private static final int maxSize = 3;

    public static void main(String args[]) {

        Queue<String> queue = new LinkedList<String>();

        Thread producer1 = new Producer("P-1", queue, maxSize);
        Thread producer2 = new Producer("P-2", queue, maxSize);
        Thread producer3 = new Producer("P-3", queue, maxSize);
        Thread consumer1 = new Consumer("C-1", queue);
        Thread consumer2 = new Consumer("C-2", queue);

        producer1.start();
        producer2.start();
        producer3.start();
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        consumer1.start();
        consumer2.start();
    }

    /**
     * 生产者
     */
    public static class Producer extends Thread {
        private Queue<String> queue;
        String name;
        int maxSize;
        int i = 1;

        public Producer(String name, Queue<String> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            log.info(name + ":启动");
            while (true) {
                // System.out .println(name+":进来了");
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            log.info(name + ":排队等待");
                            queue.wait();
                            log.info(name + ":醒来");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    log.info(name + ":生产了：" + i);
                    queue.offer(name + ":" + i);
                    i++;
                    queue.notifyAll();

                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /**
     * 消费者
     */
    public static class Consumer extends Thread {
        private Queue<String> queue;
        String name;

        public Consumer(String name, Queue<String> queue) {
            super(name);
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            log.info(name + ":启动");
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            log.info(name + ":消费者排队等待");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    String x = queue.poll();
                    log.info(name + ":消费了：" + x);
                    queue.notifyAll();

                    try {
                        Thread.sleep(3 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}