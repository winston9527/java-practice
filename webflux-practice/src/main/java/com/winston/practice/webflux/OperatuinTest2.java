package com.winston.practice.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.Stream;

public class OperatuinTest2 {


    public static void main(String[] args) {


        Flux<Integer> integerFlux = Flux.just(getIntegers()).doOnNext(System.out::println);

        System.out.println("1========");

        integerFlux
          .subscribe();

        System.out.println("-------------------------");

        Flux<Integer> integerFlux2 = Flux.defer(()->Flux.just(getIntegers())).doOnNext(System.out::println);
        System.out.println("2========");

        integerFlux2
          .subscribe();



        Mono.just(1).then(Mono.just(2)).subscribe(System.err::println);
        System.out.println("----------------");

        Flux.just(2,4,3,8,9,0,1).sort(Comparator.comparing(i-> i.intValue())).subscribe(System.out::println);

        Flux.just(2,4,3,8,9,0,1).sort((a,b)->a.compareTo(b)).subscribe(System.out::println);

        System.out.println("----------------");
        Stream.of(4,2,5,7,6,3,11).sorted(Comparator.naturalOrder()).forEach(System.err::println);




    }

    private static Integer[] getIntegers() {
        System.out.println("log");
        return new Integer[] {1, 2, 3, 4};
    }

}
