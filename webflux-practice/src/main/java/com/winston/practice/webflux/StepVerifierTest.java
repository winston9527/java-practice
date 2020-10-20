package com.winston.practice.webflux;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierTest {


    public static void main(String[] args) {

        Flux<Integer> map = Flux.just(1, 2, 3, 4, 5).map(i -> {
            System.out.println("处理"+i);
            return i * 2;
        });
        StepVerifier.create(map)
                //希望数据流
                .expectNext(2,4,6,8,10)
                //检查是否有完成信号
                .expectComplete()
                //验证
                .verify();

        //订阅
        map.subscribe(System.out::println,  //打印数据元素
                //错误信号调用
                System.err::println,
                //完成信号调用
                System.out::println);


        StepVerifier.create(map)
          //希望数据流
          .expectNextCount(5)
          //检查是否有完成信号
          .expectComplete()
          //验证
          .verify();

    }

}
