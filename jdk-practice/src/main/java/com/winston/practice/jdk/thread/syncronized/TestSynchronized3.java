package com.winston.practice.jdk.thread.syncronized;

public class TestSynchronized3 {

    public static void main(String[] args) {

        final TestDo3 t3 = new TestDo3();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = "冬瓜";
                    t3.doSome(name);
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = "冬瓜";
                    t3.doSome2(name);
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = "冬瓜";
                    t3.doSome3(name);
                }
            }).start();
        }
    }
}

class TestDo3 {

    public synchronized void doSome(String input) {

        System.out.println(input + " start doSome");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end doSome");

    }

    public synchronized void doSome2(String input) {

        System.out.println(input + " start doSome2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end doSome2");

    }

    public void doSome3(String input) {

        System.out.println(input + " start doSome3");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(input + " end doSome3");

    }

}
