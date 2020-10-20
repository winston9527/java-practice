package com.winston.practice.webflux;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class MonoTest1 {


    public static void main(String[] args) {
        System.out.println("---------------------");
        Mono.empty().subscribe(s-> System.out.println(s.getClass()));
        Mono.just("Hello Winston").subscribe(s->System.out.println(s.length()));

        Mono.create(s->{s.success("sss");}).subscribe(System.out::println);
        Mono<Integer> just = Mono.just(123);
        Mono<Integer> integerMono = just.doOnNext(s -> {
            System.out.println("结果为：" + s);
        });
        Mono<Tuple2<Integer, Integer>> integerMono1 = integerMono.zipWith(Mono.just(456));
//        integerMono = integerMono.zipWith(Mono.just(789),(a,b)->{
//            a++;
//            b++;
//            return a+b;
//        });
        integerMono.subscribe(System.out::println);
        System.out.println("---------------------");
        integerMono1.subscribe(System.out::println);
        System.out.println("---------------------");


        /**
         * buildMonoB has been invoked
         * buildMonoC has been invoked
         * C has been invoked
         * buildMonoC mapd
         * buildMonoB zipwith
         *
         */

    }

}
