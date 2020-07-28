package com.winston.practice.jdk.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue ：一个由链表结构组成的可选有界阻塞队列。如果未指定容量，那么容量将等于 Integer.MAX_VALUE。 2 31－1
 * PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
 * DelayQueue：一个使用优先级队列实现的无界阻塞队列,，只有在延迟期满时才能从中提取元素。
 * SynchronousQueue：一个不存储元素、没有内部容量的阻塞队列。
 * LinkedTransferQueue：一个由链表结构组成的无界阻塞TransferQueue队列。
 * LinkedBlockingDeque：一个由链表结构组成的可选范围双向阻塞队列。如果未指定容量，那么容量将等于 Integer.MAX_VALUE。 2 31－1
 */

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        //add 内部也是调用了 offer 插入失败的话 会抛出异常  比如:固定容量的队列 队列已满（建议不适用add  或者是 remove）
        //queue.add(4);
        boolean offer = queue.offer(1);
        System.out.println("添加元素结果：" + offer);
        // 取出第一个元素  并且移除他
        Integer poll = queue.poll();
        System.out.println("第一个元素：" + poll);
        //取出一个元素  但是不会移除
        Integer peek = queue.peek();
        System.out.println("取出的元素是：" + peek);
        System.out.println("取出的元素是：" + queue.peek());

        queue.put(4);
        //当队列满的时候  put 方法会一直阻塞
        //queue.put(5);


        /***
         * 方法\处理方式	抛出异常	返回特殊值	一直阻塞	超时退出
         * 插入方法	add(e)	offer(e)	put(e)	offer(e,time,unit)
         * 移除方法	remove()	poll()	take()	poll(time,unit)
         * 检查方法	element()	peek()	不可用	不可用
         */

    }

}
