package com.winston.practice.jdk.thread.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 计数器 可以重置   多次使用
 * <p>
 * 比如下面 对账系统  有三个工作线程
 * 第一步  下载对账文件，            --3个都完成后 CyclicBarrier 会重置为0
 * 等3个线程都下载完成后 开始对账     --3个都完成后 CyclicBarrier 会重置为0
 * 等3个线程都对账完成后 开始上传对账结果
 * 和 CountDownLatch 的主要区别 是
 * CountDownLatch 是只能使用一次
 * CyclicBarrier  可以循环使用  每次达到触发条件后 所有计数器又回到初始化
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier cyclicbarrier = new CyclicBarrier(3);
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    log.info("开始下载对账文件");
                    Thread.sleep(new Random().nextInt(15) * 1000);
                    log.info("对账文件下载完成");
                    cyclicbarrier.await();
                    log.info("开始执行对账流程");
                    Thread.sleep(new Random().nextInt(15) * 1000);
                    log.info("对账执行完成");
                    cyclicbarrier.await();
                    log.info("上传对账结果");

                    latch.countDown();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.await();
    }


}
