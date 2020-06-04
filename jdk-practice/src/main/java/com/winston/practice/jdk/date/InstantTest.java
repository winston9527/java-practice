package com.winston.practice.jdk.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantTest {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println("ss"+instant);

        ZonedDateTime instant2= Instant.now().atZone(ZoneId.systemDefault());
        System.out.println("ss"+instant2);


    }

}
