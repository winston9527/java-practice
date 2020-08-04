package com.winston.practice.jdk.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * public Semaphore(int permits, boolean fair) {
 * permits 许可证的个数 （可以为负数 或者 0 但是这样的话就必须）
 * fair    是否公平  为true的话 表示公平锁  会遵循 first-in  first-out的规则 ，为false的话  随机从等待队列拿一个线程 分配许可证
 * semaphore.acquire(); 获取许可 如果没有可用许可证的话  一直阻塞  可能抛出线程被打断的异常  InterruptedException
 * semaphore.acquireUninterruptibly(); 获取许可 如果没有可用许可证的话  一直阻塞  不会抛出线程被打断的异常
 * tryAcquire() 获取许可证  不阻塞  获取到的话 返回true  否则 false
 */
public class TestSemaphore {

    /**
     * Semaphore 音标 seməfɔ:(r) 信号量
     */
    private static Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) {
        System.out.println(semaphore.availablePermits());
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        semaphore.acquire();
                        //
                        //semaphore.acquireUninterruptibly();
                        TestDo.doSome(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}

class TestDo {

    public static void doSome(String input) {

        System.out.println(input + " start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end");

    }

}