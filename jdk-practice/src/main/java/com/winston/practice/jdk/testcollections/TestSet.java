package com.winston.practice.jdk.testcollections;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Winston
 * @Version 1.0 2018年9月17日 下午4:10:02
 */
public class TestSet {
    public static void main(String[] args) {


        Set<Person> set = new HashSet<Person>();
        Person p1 = new Person("Aa", 11);
        Person p2 = new Person("Aa", 11);
        set.add(p1);
        set.add(p2);
        set.remove(p1);
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        set.forEach(a -> System.out.println(a.toString()));
    }
}


class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
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
