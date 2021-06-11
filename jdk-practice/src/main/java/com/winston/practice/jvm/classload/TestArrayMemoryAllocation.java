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
        //4956-3932=1024k  此处并不代表boolean占用一个字节，
        //在Java虚拟机中没有任何供boolean值专用的字节码指令 所以 boolean平时会被转为int类型 所以占用4个字节，此处boolean的数组会被转为byte数组，所以占用一个字节
        //在Java虚拟机中没有任何供boolean值专用的字节码指令，Java语言表达式所操作的boolean值，在编译之后都使用Java虚拟机中的int数据类型来代替
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
