package com.winston.practice.webflux;

import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoOnErrorS {


    public static void main(String[] args) {

        Mono<String> stringMono = Mono.just("张").doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorResume(e -> Mono.just(e.getMessage()))
          .map(s -> s + "----");

        stringMono.subscribe(System.out::println, System.err::println);

        System.out.println("=====onErrorReturn=========");
        Mono<String> stringMono2 = Mono.just("张").doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorReturn("出错了")
          .map(s -> s + "----");

        stringMono2.subscribe(System.out::println, System.err::println);

        System.out.println("=====onErrorContinue=========");

        String[] arr = {"陈三", "陈世民", "王二", "麻子"};
        Flux<String> stringFlux1 = Flux.fromArray(arr).log().doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorContinue((e, s) -> System.out.println(e.getMessage() + "||" + s));

        stringFlux1.subscribe(System.out::println, System.err::println);

        System.out.println("=====onErrorResume=========");

        Flux<String> stringFlux2 = Flux.fromArray(arr).log().doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈--" + i);
        }).onErrorResume(e -> Mono.just(e.getMessage()));

        stringFlux2.subscribe(System.out::println, System.err::println);

        System.out.println("=====onErrorReturn=========");

        Flux<String> stringFlux3 = Flux.fromArray(arr).log().doOnNext(i -> {
            Assert.isTrue(i.startsWith("陈"), "他不姓陈");
        }).onErrorReturn("异常");
        stringFlux3.subscribe(System.out::println);


    }


}
