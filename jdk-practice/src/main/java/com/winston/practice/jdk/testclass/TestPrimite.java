package com.winston.practice.jdk.testclass;

public class TestPrimite {

    public static void main(String[] args) {

        System.out.println(byte.class.isPrimitive());
        System.out.println(short.class.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(float.class.isPrimitive());
        System.out.println(double.class.isPrimitive());
        System.out.println(long.class.isPrimitive());

        System.out.println(char.class.isPrimitive());
        System.out.println(boolean.class.isPrimitive());

        System.out.println(String.class.isPrimitive());
    }
}
