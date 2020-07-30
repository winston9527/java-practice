package com.winston.practice.jdk.number;

public class NUmberTest2 {

    public static void main(String[] args) {


        //A
        Integer i1 = 127;
        Integer i2 = 127;
        //定义的 i1  和 i2  通过基础类型赋值 会自动进行装箱操作
        //使用javap -c NUmberTest2
        //通过反编译结果  可以看到 Integer 自动装箱是通过调用 Integer.valueOf() 来完成的
        // （我不知道为啥子 Idea的反编译 不限显示Integer.valueOf()  这个装箱操作 猜测是idea反编译 会优化显示结果）
        //通过源码  可以看到 Integer.valueOf 里面 有IntegerCache 对象 该对象 缓存了 -128 到 127 的实例，超过范围的会通过构造方法新创建
        //所以此处 结果 为 true
        System.out.println(i1 == i2);
        //B
        Integer i3 = 128;
        Integer i4 = 128;
        //所以此处 为 false
        System.out.println(i3 == i4);

        //C
        Integer i5 = 235;
        Integer i6 = i5;
        System.out.println(i5 == i6);


    }
}
