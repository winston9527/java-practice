package com.winston.practice.jdk.constantsoptimize;


/**
 * 常量优化：
 * 在给一个变量赋值的时候，如果“=”的右边全部是常量（包括final关键字定义的常量在内）那么在编译阶段会把右边的结果赋值给左边的变量，
 * 如果范围不超过左边的变量类型的范围（或者说属于左边的范围）那么就会赋值成功如果超过就会赋值失败。
 * 右边如果存在变量，则不会触发常量优化机制。
 * <p>
 * 其中JAVA常量优化的有char,short,byte,String;
 * <p>
 */
public class Test1 {


    public static void main(String[] args) {

        int a = 10;
        //byte 的范围比int小，将int直接赋值给byte可能出现精度丢失问题，所以会报错
        //byte b = a;

        final int c = 10;
        //此时c是常量 ，所以编译过程中就知道了他的值 是否在byte取值范围内 因此编译通过
        byte d = c;

        final int e = 129;
        //编译报错 ，虽然是常量  但是取值超过  byte的最大值
        //byte f = e;

    }

}
