package com.winston.practice.webflux;

import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PublisherCreateTest {


    public static void main(String[] args) throws InterruptedException {

        //元素通过FluxSink API以同步或异步方式进行。
        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            //fluxSink.complete();
            fluxSink.error(new RuntimeException());
        }).subscribe(System.out::println,System.err::println);

        AtomicInteger i = new AtomicInteger();
        //创建一个循环的Flux 每次next发出元素。synchronousSink的next方法  在程序里面只能调用一次
        Flux.generate(synchronousSink -> {
            synchronousSink.next("this is generate"+i);
            if(i.getAndIncrement()==10){
                synchronousSink.complete();
            }
            //synchronousSink.complete();
        }).subscribe(System.out::println,System.err::println);


        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 10);
        Flux.fromStream(integerStream).subscribe(s->{
            System.out.println("一："+s);
        });
        //流只能被使用一次 下面再次使用integerStream 创建flux的时候  会报错
        Flux.fromStream(integerStream).subscribe(s->{
            System.out.println("二："+s);
        },System.err::println);





        TimeUnit.MILLISECONDS.sleep(100);




    }

}
