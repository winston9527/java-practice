package com.winston.practice.webflux;

import reactor.core.publisher.Mono;

public class DoFinally {


    public static void main(String[] args) {

        Mono.just(0)
          .map(s -> 100 / s)
          .doFinally(s -> System.out.println("finall:" + s))
          .subscribe(s -> System.err.println(s), s -> {
              System.out.println("ss" + s);
          });
    }
}
