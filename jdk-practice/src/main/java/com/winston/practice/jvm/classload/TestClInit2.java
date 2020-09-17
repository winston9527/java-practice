package com.winston.practice.jvm.classload;

public class TestClInit2 {
    public static void main(String[] args) {
        Element[] array = new Element[2];
        System.out.println(array);
        //不会输出element初始化，因为定义数据不会导致类初始化
    }

}


class Element {
    static {
        System.out.println("element初始化");
    }
}