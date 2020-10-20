package com.winston.practice.webflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MonoTest2 {


    public static void main(String[] args) {
        Mono<String> stringMono = Mono.fromSupplier(() -> null)
          .switchIfEmpty(Mono.error(new RuntimeException()))
          .map(s -> {

              System.out.println("main :" + s);
              return s;
          }).zipWith(Mono.defer(MonoTest2::buildD), (a, b) -> {
                System.out.println("main zipWith");
                return a + b + "c";
            }
          );
        //订阅
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stringMono.subscribe(s -> System.out.println("最后订阅的结果为：" + s), s -> System.out.println(s));

        //订阅
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Mono<String> buildMonoB() {
        System.out.println("buildMonoB has been invoked");
        return Mono.just("b").map(s -> {
            System.out.println("buildMonoB" + s);
            return s;
        }).zipWith(Mono.defer(MonoTest2::buildD), (a, b) -> {
            System.out.println("buildMonoB zipwith");
            return a + "---" + b;
        });
    }

    private static Mono<String> buildMonoC() {
        System.out.println("buildMonoC has been invoked");
        return Mono.defer(MonoTest2::buildD).map(s -> {
            System.out.println("buildMonoC map" + s);
            return s;
        });
    }

    private static Mono<String> buildD() {
        System.out.println("C has been invoked");
        return Mono.just("d");
    }


}
