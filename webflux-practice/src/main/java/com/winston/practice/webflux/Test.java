package com.winston.practice.webflux;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println((char) 65);
        System.out.println(isWrapClass(Long.class));
        System.out.println(isWrapClass(Integer.class));
        System.out.println(isWrapClass(String.class));
        System.out.println(isWrapClass(Test.class));
    }

    public static boolean isWrapClass(Class clz) {
        try {
            System.out.println(clz);
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
} 