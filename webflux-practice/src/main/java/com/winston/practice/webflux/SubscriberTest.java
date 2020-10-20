package com.winston.practice.webflux;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SubscriberTest {


    public static void main(String[] args) {

        Flux.range(1,6).doOnRequest(value -> {
            System.out.println("请求："+value+"个值");
        }).subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("开始订阅");
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                try {
                    SECONDS.sleep(1);
                    System.out.println("取得："+value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request(1);
            }
    });



    }


}
