package com.winston.practice.webflux;

import reactor.core.publisher.Mono;

public class MonoDoOnSuccess {

    public static void main(String[] args) {


        Mono.fromSupplier(() -> 22)
          .switchIfEmpty(Mono.error(new NullPointerException()))
          .doOnSuccess(s -> {
              System.err.println("sss" + s);
          }).map(s -> s + 3)
          .subscribe(s -> {
              System.err.println("11" + s);
          }, s -> {
              System.err.println("22" + s);
          }, () -> System.out.println("OK"));

        Mono.just(0)
          .map(s -> 10 / s)
          .flatMap(s -> Mono.just(0).map(a -> 10 / a).onErrorResume(e -> e != null, e -> {
              System.out.println(111);
              return Mono.just(12);
          })).subscribe(s -> System.out.println(s));

    }


}
