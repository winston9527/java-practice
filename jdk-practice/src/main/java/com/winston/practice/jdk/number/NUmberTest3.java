package com.winston.practice.jdk.number;

public class NUmberTest3 {

    public static void main(String[] args) {


        //A
        int i1 = 127;
        int i2 = 127;
        System.out.println(i1 == i2);

        //B
        int i3 = 128;
        int i4 = 128;
        System.out.println(i3 == i4);

        //C
        int i5 = 235;
        int i6 = i5;
        System.out.printf("i5~%d\ti6~%d\n", System.identityHashCode(i5), System.identityHashCode(i6));


    }
}
