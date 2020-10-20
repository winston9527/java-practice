package com.winston.practice.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoOnError {


    public static void main(String[] args) {

        Mono<String> stringMono = Mono.just("张").doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorResume(e-> Mono.just(e.getMessage()));

        stringMono.subscribe(System.out::println);


        System.out.println("=====onErrorContinue=========");

        String [] arr = {"陈三","陈世民","王二","麻子"};
        Flux<String> stringFlux1 = Flux.fromArray(arr).log().doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorContinue((e,s)-> System.out.println(e.getMessage()+"||"+s));

        stringFlux1.subscribe(System.out::println);

        System.out.println("=====onErrorResume=========");

        Flux<String> stringFlux2 = Flux.fromArray(arr).doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorResume(e-> Mono.just(e.getMessage()));

        stringFlux2.subscribe(System.out::println);

        System.out.println("=====onErrorReturn=========");

        Flux<String> stringFlux3 = Flux.fromArray(arr).log().doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorReturn("异常");

        stringFlux3.subscribe(System.out::println);

        System.out.println("=====Mono onErrorResume=========");
        Mono.just(0)
          .map(i->10/i)
          .onErrorResume(s-> {
              log.info("error",s);
              return Mono.error(new RuntimeException("test'"));
          })
          .onErrorResume(s-> {
              log.info("错误",s);
              return Mono.just(1000);
          })
          .subscribe(System.out::println);


    }


}
