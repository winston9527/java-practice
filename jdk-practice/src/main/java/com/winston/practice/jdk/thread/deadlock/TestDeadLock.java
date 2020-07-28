package com.winston.practice.jdk.thread.deadlock;

/**
 * @Description: 线程死锁
 * @version: v1.0.0
 * @author: Winston
 * @date: 2018年8月28日 下午3:47:32
 */
public class TestDeadLock {

    public static void main(String[] args) {
        String lock1 = "aa";
        String lock2 = "bb";
        Thread thread1 = new Thread(new MyThread1(lock1, lock2));
        Thread thread2 = new Thread(new MyThread1(lock2, lock1));
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }

}

class MyThread1 implements Runnable {

    private String lock1;

    private String lock2;

    public MyThread1(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {

        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "获得：" + lock1);
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "等待：" + lock2);
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获得：" + lock2);
            }
        }

    }

}