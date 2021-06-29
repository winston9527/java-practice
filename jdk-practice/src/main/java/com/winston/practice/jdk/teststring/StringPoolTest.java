package com.winston.practice.jdk.teststring;

public class StringPoolTest {


    public static void main(String[] args) {

        int a = 1;
        //创建2个对象   一个是aa对应的堆内存中的对象   另外一个是字符串常量池里面的 hello
        //当然  如果之前字符串常量池里面已经存在了 hello 这个对象的话  此处只创建一个对象
        String aa = new String ("hello");
        int c = 0;
        String bb = new String("11")+aa;
        String ccc = new String(bb);


    }
}
