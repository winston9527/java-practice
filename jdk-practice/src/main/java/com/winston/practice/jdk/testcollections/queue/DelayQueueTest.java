package com.winston.practice.jdk.testcollections.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue 中的元素  需要实现 Delayed接口
 * 重写 getDelay 和 compareTo 方法
 * <p>
 * getDelay 返回当前对象 距离超时时间的 间隔
 * 返回 0 或者 负数  表示该对象已经超时。会被立即消费
 */
@Slf4j
public class DelayQueueTest {


    public static void main(String[] args) {

        BlockingQueue<FlowFile> queue = new DelayQueue<>();
        int[] time = {10, 1, 3, 0, 6};
        new Thread(() -> {
            FlowFile file1 = new FlowFile("flowFile" + 2, 2 * 1000 + System.currentTimeMillis());
            try {
                queue.put(file1);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                FlowFile file = new FlowFile("flowFile" + time[i], time[i] * 1000 + System.currentTimeMillis());
                try {
                    log.info("加入新元素" + file);
                    queue.put(file);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 6; i++) {
                try {
                    FlowFile take = queue.take();
                    log.info("拿到新元素：" + take + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


@AllArgsConstructor
@Data
class FlowFile implements Delayed {

    private String name;

    private Long penaltyTime;

    @Override
    public long getDelay(TimeUnit unit) {
        return penaltyTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        FlowFile other = (FlowFile) o;
        return this.penaltyTime.compareTo(other.penaltyTime);
    }
}