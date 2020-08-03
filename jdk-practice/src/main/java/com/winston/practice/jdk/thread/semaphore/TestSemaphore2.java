package com.winston.practice.jdk.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @version: v1.0.0
 * @author: Winston
 * @date: 2018年8月22日 下午2:24:49
 */
public class TestSemaphore2 {

    /**
     * Semaphore 音标 seməfɔ:(r) 信号量
     */
    static Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) {
        System.out.println(semaphore.availablePermits());
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //tryAcquire()方法在没有许可的情况下会立即返回 false，要获取许可的线程不会阻塞，这与Lock类的lock()与tryLock()类似
                    if (semaphore.tryAcquire()) {
                        // semaphore.acquireUninterruptibly();
                        TestDo2.doSome(Thread.currentThread().getName());
                        semaphore.release();
                    } else {
                        System.out.println(Thread.currentThread().getName() + "no semaphore");
                    }
                }
            }).start();
        }
    }
}

class TestDo2 {

    public static void doSome(String input) {
        System.out.println(input + " start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end");
    }

}