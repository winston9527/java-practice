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
        System.out.println("ss"+instant);

        ZonedDateTime instant2= Instant.now().atZone(ZoneId.systemDefault());
        System.out.println("ss"+instant2);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        ZonedDateTime parse = ZonedDateTime.parse("2025-01-01T17:16:10Z");
        System.out.println(parse);
        ZoneId zone = parse.getZone();
        System.out.println(zone);

         ZoneId.getAvailableZoneIds().stream().filter(s->s.equals("Z"))
           .forEach(System.out::println);

        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(utc);
        System.out.println(utc.withZoneSameInstant(ZoneId.of("+8")));

        System.out.println("=============");
        ZonedDateTime of = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        ZonedDateTime of1 = ZonedDateTime.of(LocalDateTime.now().plusDays(20), ZoneId.systemDefault());
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            getDates(of,of1);
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l);

        Date date = new Date();
        Date date2 = new Date();
        date2.setDate(14);

        long l3 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            findDates(date,date2);
        }
        long l4 = System.currentTimeMillis();
        System.out.println(l4-l3);


    }

    private static List<String> getDates(ZonedDateTime from, ZonedDateTime to) {
        return LongStream.range(0, ChronoUnit.DAYS.between(from.toLocalDate(), to.toLocalDate()) + 1)
          .mapToObj(value -> from.plusDays(value).toLocalDate().toString())
          .collect(toList());
    }

    public static List<Date> findDates(Date dStart, Date dEnd) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);

        List dateList = new ArrayList();
        //别忘了，把起始日期加上
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(cStart.getTime());
        }
        return dateList;
    }

}
