package com.winston.practice.jvm.classload;


public class ClassLoaderTest2 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class<?> aClass1 = Class.forName("com.winston.practice.jvm.classload.Person");
        System.out.println(aClass1);
        //Class.forName 内部调用 forName0(className, true, ClassLoader.getClassLoader(caller), caller)
        //其中第二个参数表示  需要初始化
        //所以此处会输出System.out.println("初始化age是" + age);
    }


}

class Person2 {

    private static int age = 10;

    static {
        System.out.println("初始化age是" + age);
    }

}