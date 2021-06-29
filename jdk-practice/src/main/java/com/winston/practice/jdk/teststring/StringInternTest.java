package com.winston.practice.jdk.teststring;

public class StringInternTest {

    public static void main(String[] args) throws InterruptedException {
        int size  = 1000*1000;
        String[] array = new String[size];
        long start = System.currentTimeMillis();
        for(int i=0;i<size;i++){
            //array[i]=String.valueOf(i%10);    //instances 1029437
            //intern 是检查当前的值 是否已经保存在字符串常量池里面  如果有的话 就返回原来对象的地址
            //如果不存在的话  则将当前字符串放到字符串常量池  并且返回地址
            array[i]=String.valueOf(i%10).intern(); //12349
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        Thread.sleep(1000000);
    }

}
