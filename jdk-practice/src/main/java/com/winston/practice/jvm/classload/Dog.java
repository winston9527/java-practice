package com.winston.practice.jvm.classload;

public class Dog extends Animal {

    static {
        System.out.println("dog static");
    }

    {
        System.out.println("dog");
    }

    public static void main(String[] args) {

        Dog cat = new Dog();

    }

}
