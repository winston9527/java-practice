package com.winston.practice.webflux;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class FluxTest {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux.range(1, 3).doOnNext(s -> {
            System.out.println("doOnNext" + s);
        })
          .flatMap(i -> {
              System.out.println("flatMap" + i);
              return Flux.just("张" + i, "李" + i, "王" + i, "麻" + i, "陈" + i).delayElements(Duration.ofMillis(100));
          })
          .subscribe(s -> {
              System.out.println("subscribe:" + s);
          }, null, countDownLatch::countDown);

        countDownLatch.await();

        System.out.println("====================");

        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        Flux.range(1, 3).doOnNext(s -> {
            System.out.println("doOnNext" + s);
        })
          .concatMap(i -> {
              System.out.println("flatMap" + i);
              return Flux.just("张" + i, "李" + i, "王" + i, "麻" + i, "陈" + i).delayElements(Duration.ofMillis(100));
          })
          .subscribe(s -> {
              System.out.println("subscribe:" + s);
          }, null, countDownLatch2::countDown);

        countDownLatch2.await();

        System.out.println("===next=======");
        Flux.just(13, 2, 3, 4).next().subscribe(System.out::println);


        System.out.println("concatWith");

        Flux.just(1, 2, 3, 4).map(i -> {
            System.out.println("第一次：" + i);
            return ++i;
        }).concatWith(Flux.just(12, 32))
          .map(s -> {
              System.out.println("第二次：" + s);
              return s;
          })
          .subscribe(System.err::println);


    }


}
