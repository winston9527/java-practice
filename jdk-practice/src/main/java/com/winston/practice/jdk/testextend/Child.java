package com.winston.practice.jdk.testextend;

public class Child extends Parent {

    public void sayHello() {
        System.out.println("sayHello Child");
    }

    public static void main(String[] args) {

        new Child().test();
    }

}
