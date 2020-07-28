package com.winston.practice.jdk.sort;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

@Slf4j
public class SortTest {


    public static void main(String[] args) {
        List<DateRange> dateRanges = Lists.newArrayList(
          DateRange.builder().startDate(LocalDate.of(2020, 01, 01))
            .endDate(LocalDate.of(2020, 12, 01)).build(),
          DateRange.builder().startDate(LocalDate.of(2020, 01, 01))
            .endDate(LocalDate.of(2020, 03, 01)).build(),
          DateRange.builder().startDate(LocalDate.of(2020, 02, 01))
            .endDate(LocalDate.of(2020, 02, 12)).build(),
          DateRange.builder().startDate(LocalDate.of(2020, 01, 01))
            .endDate(LocalDate.of(2020, 01, 12)).build());

        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            dateRanges.stream().sorted(Comparator.comparingLong((ToLongFunction<DateRange>) range ->
                ChronoUnit.DAYS.between(range.getStartDate(), range.getEndDate()))
                .reversed()
              //.thenComparing(DateRange::getStartDate)
            ).collect(Collectors.toList());

        }
        long s2 = System.currentTimeMillis();

        System.out.println("===========" + (s2 - s1));
        long s3 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            dateRanges.stream().sorted(Comparator.comparingLong((ToLongFunction<DateRange>) range ->
                ChronoUnit.DAYS.between(range.getEndDate(), range.getStartDate()))
              //.thenComparing(DateRange::getStartDate)
            ).collect(Collectors.toList());
        }


        long s4 = System.currentTimeMillis();
        System.out.println("===========" + (s4 - s3));


    }


    @Data
    @Builder
    static class DateRange {
        private LocalDate startDate;
        private LocalDate endDate;
    }

}
