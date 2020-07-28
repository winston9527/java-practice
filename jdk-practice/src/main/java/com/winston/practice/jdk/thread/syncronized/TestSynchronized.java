package com.winston.practice.jdk.thread.syncronized;

public class TestSynchronized {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String input = Thread.currentThread().getName();
                    //1、synchronized 加在非静态方法上面，相当于同步代码块 synchronized(this){  }。锁住当前对象实例
                    TestDo.doSomeStatic(input);
                    //TestDo.doSomeStatic(input);
                }
            }
            ).start();
        }
    }
}


class TestDo {

    public synchronized void doSome(String input) {
        System.out.println(input + " start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end");
    }

    public void doSomeClassLock(String input) {
        synchronized (TestDo.class) {
            System.out.println(input + " start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(input + " end");
        }
    }


    public static synchronized void doSomeStatic(String input) {
        System.out.println(input + " start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end");
    }

}