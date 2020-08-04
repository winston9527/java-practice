package com.winston.practice.jdk.thread.countDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 相当于倒计时计数器 ， 初始化一个数字 使用await() 等待倒计时计数器归0
 * 然后每个线程执行完成后 使用countDown() 减1 ，等 CountDownLatch 计数器为0 的时候
 * await可以返回，否则一直阻塞
 * <p>
 * 使用countDown()  计数器 减1
 * countDownLatch.await();  阻塞 等待到计数器归0
 * await(long timeout, TimeUnit unit)  阻塞等待 到计数器归0   返回true 如果到达超时时间   计数器 还未归0 则返回false
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                log.info(">>>开始执行");
                try {
                    Thread.sleep(new Random().nextInt(10) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(">>>执行完了");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        log.info("等待完成");
    }
}
