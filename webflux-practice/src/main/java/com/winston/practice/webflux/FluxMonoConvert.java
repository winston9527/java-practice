package com.winston.practice.webflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FluxMonoConvert {

    public static void main(String[] args) {

        log.info("=====");
        //Flux convert to Mono
        Mono<String> zhuzhuxia = Flux.just("猪猪侠", "GG-BOND").next();
        zhuzhuxia.subscribe(log::info);
        log.info("=====");
        //Mono convert to Flux
        Flux<String> superMan = Flux.from(Mono.just("SUPERMAN-QIANG"));
        superMan.subscribe(log::info);
        log.info("=====");
        //convert Mono<List<String>>  to Flux<String>
        List<String> list = new ArrayList<>();
        list.add("GG-BONG");
        list.add("SUPERMAN-QIANG");
        Mono<List<String>> just = Mono.just(list);
        Flux<String> stringFlux = just.flatMapMany(Flux::fromIterable);
        stringFlux.subscribe(log::info);


    }

}
