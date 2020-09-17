package com.winston.practice.jvm.classload;

/**
 * 通过子类引用父类的静态字段，不会导致子类初始化
 */
class SuperClass {
    static {
        System.out.println("SuperClass init....");
    }

    public static int value = 123;
}


class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init....");
    }
}


public class TestClInit1 {

    public static void main(String[] args) {
        /**
         * 对于静态字段，只有直接定义这个字段的类才会被初始化，
         * 因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
         * （是否触发子类的加载和验证，取决于虚拟机具体的实现，对于HotSpot来说，可以通过-XX:+TraceClassLoading参数观察到此操作会导致子类的加载）
         */
        //SuperClass init....
        //123
        System.out.println(SubClass.value);
    }
}