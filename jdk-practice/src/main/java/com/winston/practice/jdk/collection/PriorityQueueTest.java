package com.winston.practice.jdk.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        queue.put(new Person("小猪", 12));
        queue.put(new Person("小狗", 11));
        queue.put(new Person("小猫", 14));
        queue.put(new Person("小妞", 13));
        Person temp;
        while ((temp = queue.poll()) != null) {
            System.out.println(temp);
        }

    }
}

@Data
@Builder
@AllArgsConstructor
class Person implements Comparable<Person> {

    private String name;

    private int age;

    @Override
    public int compareTo(Person o) {
        //12 11 1
        return this.age - o.age;
    }
}