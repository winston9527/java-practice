package com.winston.practice.jvm.classload;

public class Animal {

    static {
        System.out.println("animal static");
    }

    {
        System.out.println("animal");
    }

}
