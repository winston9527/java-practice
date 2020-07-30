package com.winston.practice.jdk.number;

public class NUmberTest3 {

    public static void main(String[] args) {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2));
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));
        System.out.println("i1=i4   " + (i1 == i4));
        System.out.println("i4=i5   " + (i4 == i5));
        // i5+i6 会将 i5 和 i6 继续拆箱 通过 int类型继续 + 运算 得到结果 为 int 类型
        // 然后 i4 是Integer 类型 不能和 int 直接进行 == 运算 ，jvm 会将 i4 也进行 拆箱 再进行运算 ，所以下面结果为 true
        //具体可以参看 下面反编译的结果
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));
        System.out.println("40=i5+i6   " + (40 == i5 + i6));


        /**
         *      反编译结果：
         *      Integer i1 = Integer.valueOf(40);
         *       Integer i2 = Integer.valueOf(40);
         *       Integer i3 = Integer.valueOf(0);
         *       Integer i4 = new Integer(40);
         *       Integer i5 = new Integer(40);
         *       Integer i6 = new Integer(0);
         *       System.out.println("i1=i2   " + (i1 == i2));
         *       System.out.println("i1=i2+i3   " + (i1.intValue() == i2.intValue() + i3.intValue()));
         *       System.out.println("i1=i4   " + (i1 == i4));
         *       System.out.println("i4=i5   " + (i4 == i5));
         *       System.out.println("i4=i5+i6   " + (i4.intValue() == i5.intValue() + i6.intValue()));
         *       System.out.println("40=i5+i6   " + (40 == i5.intValue() + i6.intValue()));
         */

    }
}
