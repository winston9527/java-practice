package com.winston.practice.jdk.thread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * semaphore 实现生产者 消费者模式
 */
@Slf4j
public class SemaphoreConsumer {

    private static Semaphore semaphore = new Semaphore(0);

    private static Queue<String> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {


        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                int j = 0;
                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.offer("GOOD:线程" + finalI + j++);
                    semaphore.release();
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String s = queue.poll();
                    log.info(Thread.currentThread().getName() + "||" + s);
                }
            }).start();
        }

    }


}
