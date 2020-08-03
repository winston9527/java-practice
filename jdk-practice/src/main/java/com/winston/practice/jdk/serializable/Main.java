package com.winston.practice.jdk.serializable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {


    static void send(Serializable dto) {

        Map<String, Object> thing = (HashMap<String, Object>) dto;

        System.out.println(thing);
    }


    public static void main(String[] args) {

        HashMap<String, Object> thing = new HashMap<String, Object>();
        thing.put("a", 1);
        thing.put("b", "two");
        thing.put("c", Arrays.asList(3, 30));

        send(thing);

    }

}
