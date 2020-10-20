package com.winston.practice.webflux;

import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class MonoErrorTest {

    public static void main(String[] args) {

        Mono.just(1)
          .doOnNext(s -> {
              System.out.println("doOnNext" + s);
          })
          .filter(i->i>2)
          .zipWith(Mono.fromSupplier(() -> createMono()), (a, b) -> {
              System.out.println("zipWith has invoked ");
              return a + b;
          })
          .switchIfEmpty(Mono.just("Nothing"))
          .subscribe(s -> {
              System.out.println("元素" + s);
          }, err -> {
              System.err.println("error:" + err);
          });
        System.out.println("main has done");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String createMono() {
        System.out.println("createMono has invoked");
        return "value";
    }

}
