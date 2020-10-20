package com.winston.practice.webflux;

import reactor.core.publisher.Mono;

public class MonoThen {


    public static void main(String[] args) {

        Mono.just(10)
          .map(s -> {
              System.out.println(s);
              return 10 / s;
          })
          .then(Mono.just(2))
          .doOnNext(s -> System.out.println("sss:" + s))
          .subscribe(System.out::println, System.err::println, () -> System.out.println("OK"));


    }

}
