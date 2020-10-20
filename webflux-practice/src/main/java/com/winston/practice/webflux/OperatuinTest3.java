package com.winston.practice.webflux;

import java.util.Optional;

public class OperatuinTest3 {


    public static void main(String[] args) {

        Integer id = 12;
        Object o = Optional.ofNullable(id)
          .map(i -> method1())
          .orElse(method2());
        System.out.println(o);
        System.out.println("==============");
        Object o2 = Optional.ofNullable(id)
          .map(i -> method1())
          .orElseGet(OperatuinTest3::method2);
        System.out.println(o2);
    }

    private static Object method2() {
        System.out.println("method2");
        return 200;
    }

    private static Object method1() {
        System.out.println("method1");
        return 100;
    }


}
