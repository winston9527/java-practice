package com.winston.practice.jdk.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * java 序列化 和 反序列化的意义，主要是需要将Java实例信息 写入到 文件，或者 进行RPC传输的过程的时候用到
 * 也有很多第三方的序列化方案 目前比较常用的是 json序列化
 * <p>
 * serialVersionUID 在序列化的时候 会一起保存
 * 在反序列化的时候会判断类中的属性  和  流里面该字段的的值 是否一致，如果不一致的话 会抛出异常 InvalidClassException（相当于判断序列化和反序列化是不是同一个类）
 * 如果没有指定这个属性的值的话  ，jdk会自动根据类信息进行计算，但是如果你在反序列化之前修改了类的话   那么反序列化就会失败
 */

public class TestSerializable {

    public static void main(String[] args) {

        Person p1 = new Person();
        p1.setName("xiaozhang");
        p1.setAge(23);
        File file = new File("person.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        writeObject(file, p1);
        Person p2 = (Person) readObject(file);
        System.out.println(p2);
        System.out.println(p1 == p2);
    }

    private static Object readObject(File file) {
        FileInputStream fin = null;
        ObjectInput oi = null;
        try {
            fin = new FileInputStream(file);
            oi = new ObjectInputStream(fin);
            return oi.readObject();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (oi != null) {
                    oi.close();
                }
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }

        }
        return null;
    }

    private static void writeObject(File file, Person person) {

        FileOutputStream outputStream = null;
        ObjectOutputStream oos = null;
        try {
            outputStream = new FileOutputStream(file);
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(person);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
