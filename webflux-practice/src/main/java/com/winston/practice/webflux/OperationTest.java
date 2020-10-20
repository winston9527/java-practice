package com.winston.practice.webflux;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class OperationTest {


    public static void main(String[] args) throws InterruptedException {

        //map 将原来的元素映射为新元素  如下  将每个元素值乘以2
        Flux.just(1,2,3,4,5).map(i->i*2).subscribe(System.out::println);


        CountDownLatch countDownLatch = new CountDownLatch(1);

        //flaxMap 将原来的元素  转换为流  然后将这些流进行拼接为一个合并的流.   并且合并的过程是异步合并的  所以下面的输出不是"zhuzhuxiaggbond"
        Flux.just("zhuzhuxia","ggbond").flatMap(s-> Flux.fromArray(s.split("\\s*")).delayElements(Duration.ofMillis(100))).subscribe(System.out::print,null,countDownLatch::countDown);


        countDownLatch.await();

        System.out.println();

        String context = "Hi Winston,I,m Steven. Nice to meet you.";

        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        //zip 从两个流里面  每个流取出一个元素拼接在一起 形成一个Tuple2类型的元素
        Flux.zip(
        Flux.fromArray(context.split("\\s+")),Flux.interval(Duration.ofMillis(10))).subscribe(
                s->{
                    //class reactor.util.function.Tuple2
                    System.out.println(s.getClass()+"||"+s.getT1()+"||"+s.getT2());

                },null,
                countDownLatch2::countDown);

        countDownLatch2.await();

        System.out.println("=================");
        //zip 取元素的时候  取到其中一个流里面没有数据了 则终止操作
        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        //zip 从两个流里面  每个流取出一个元素拼接在一起 形成一个Tuple2类型的元素
        Flux.zip(
                Flux.fromArray(context.split("\\s+")),Flux.fromStream(IntStream.range(1,5).boxed())).subscribe(
                s->{
                    //class reactor.util.function.Tuple2
                    System.out.println(s.getClass()+"||"+s.getT1()+"||"+s.getT2());

                },null,
                countDownLatch3::countDown);

        countDownLatch3.await();

        System.out.println("====doFinally======");
        //流消费完成后执行  不管是取消、异常、还是正常的消费完成。其中传入的参数为 signalType 终止类型
        Flux.range(1,5).doFinally(signalType -> {
            System.out.println(signalType);
        }).take(1).subscribe();

        Flux.range(1,5).doFinally(signalType -> {
            System.out.println(signalType);
        }).subscribe();


        System.out.println("======retry===========");
        AtomicInteger i = new AtomicInteger(0);
        //根据输出结果可以看出 retry是针对原来的流数据 再次循环而已
        Flux.range(1,5).doOnNext(s->{
            System.out.println(i+"|"+s);
            if(i.intValue()==0 && s==3){
                i.incrementAndGet();
                throw new RuntimeException("s = 3");
            }
        }).retry(1).subscribe(System.out::println);




    }




}
