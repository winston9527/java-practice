package com.winston.practice.jdk.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class InstantTest {

    public static void main(String[] args) {




        Instant instant = Instant.now();
        System.out.println("ss" + instant);

        ZonedDateTime instant2 = Instant.now().atZone(ZoneId.systemDefault());
        System.out.println("ss" + instant2);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        ZonedDateTime parse = ZonedDateTime.parse("2025-01-01T17:16:10Z");
        System.out.println(parse);
        ZoneId zone = parse.getZone();
        System.out.println(zone);

        ZoneId.getAvailableZoneIds().stream().filter(s -> s.equals("Z"))
          .forEach(System.out::println);

        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(utc);
        System.out.println(utc.withZoneSameInstant(ZoneId.of("+8")));

        System.out.println("=============");

        System.err.println(ZonedDateTime.of(LocalDateTime.now(),ZoneId.of("+07:00")));

    }


}
