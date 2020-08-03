package com.winston.practice.jdk.serializable;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 21L;

    private String name;

    private int age;

    public Person() {
        System.err.println("构造方法 ");
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }


}
