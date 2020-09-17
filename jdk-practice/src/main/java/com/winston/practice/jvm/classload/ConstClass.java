package com.winston.practice.jvm.classload;

public class ConstClass {
    static {
        System.out.println("ConstClass init....");
    }

    public static final String MM = "hello Franco";
}