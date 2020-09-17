package com.winston.practice.jvm.classload;

public class TestClInit3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.MM);
        /**
         * 并没有ConstClass init….，这是因为虽然TestClInit3里引用了ConstClass类中的常量，
         * 但其实在编译阶段通过常量传播优化，已经将此常量存储到Test3类的常量池中。两个类在编译成class之后就不存在任何联系了。
         */

    }

}



