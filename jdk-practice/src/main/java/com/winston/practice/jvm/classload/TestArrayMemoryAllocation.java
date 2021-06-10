package com.winston.practice.jvm.classload;

public class TestArrayMemoryAllocation {

    public static void main(String[] args) {
        //3932K
        testBoolean();
    }

    static void testByte(){
        //4956-3932=1024k
        byte[] array = new byte[1024*1024];
    }
    static void testBoolean(){
        //4956-3932=1024k
        boolean[] array = new boolean[1024*1024];
    }
    static void testObject(){
        //8028K-3932=4096k
        Object[] array = new Object[1024*1024];
    }

    static void testLong(){
        //12124K-3932=8192
        long[] array = new long[1024*1024];
    }
}
