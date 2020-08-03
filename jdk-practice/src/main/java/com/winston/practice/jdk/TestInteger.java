package com.winston.practice.jdk;


public class TestInteger {
    public static void main(String[] args) {

        Integer aa = Integer.MAX_VALUE;
        Integer bb = aa + 2;
        System.out.println(Integer.toBinaryString(aa));
        System.out.println(bb);

        //01111111      11111111     11111111    11111111
        int j = 2;
        for (int i = 2; i <= 31; i++) {
            j = j * 2;
        }
        j = j - 1;
        System.out.println("j=" + j);

        byte ba = Byte.MAX_VALUE;
        System.out.println(ba);
        //01111111       64 32 16 8 4 2 1


        int bc = -2147483647;
        System.out.println(Integer.toBinaryString(bc));


        System.out.println(113 / 5);

        System.out.println(-5 % 2);
        System.out.println(-5 % -2);
        System.out.println(2.1 % 2);

        System.out.println(Integer.toBinaryString(-2));

        System.out.println(1 == 1.0f);

        System.out.println(-1 >> 2);
        float a = 1.1f;


    }
}
