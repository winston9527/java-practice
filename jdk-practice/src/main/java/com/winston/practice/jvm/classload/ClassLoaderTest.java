package com.winston.practice.jvm.classload;

/**
 * 类加载机制：
 * 加载：通过类的全限定名获取二进制字节流，将二进制字节流转换成方法区中的运行时数据结构，在内存中生成Java.lang.class对象；
 * 链接 ->包含3步 ：
 * ------>验证：检查导入类或接口的二进制数据的正确性；（文件格式验证，元数据验证，字节码验证，符号引用验证）
 * ------>准备:给类的静态变量分配并初始化存储空间
 * ------>解析:将常量池中的符号引用转成直接引用
 * 初始化 ：激活类的静态变量的初始化Java代码和静态Java代码块，并初始化程序员设置的变量值。
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("com.winston.practice.jvm.classload.Person");
        System.out.println(aClass1);
        //System.out.println("初始化age是" + age);  不会输出 ，
        // 因为ClassLoader.getSystemClassLoader().loadClass 默认不会执行初始化过程
    }


}

class Person {

    private static int age = 10;

    static {
        System.out.println("初始化age是" + age);
    }

}