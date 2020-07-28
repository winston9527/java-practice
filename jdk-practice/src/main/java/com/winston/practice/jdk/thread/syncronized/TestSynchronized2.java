package com.winston.practice.jdk.thread.syncronized;

public class TestSynchronized2 {

    public static void main(String[] args) {

        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = "冬瓜";
                    new TestDo2().doSome(name);
                }
            }).start();
        }
    }
}

class TestDo2 {

    public void doSome(String input) {
        synchronized (input) {

            System.out.println(input + " start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(input + " end");

        }
    }


}

