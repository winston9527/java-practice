package com.winston.practice.jdk.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTest {
    public static void main(String[] args) {
        String temp = "ddenfj#@fe_dw.comw";
        int count = 1000000;
        long a = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Pattern emailPattern = Pattern.compile("^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
			emailPattern.matcher(temp).matches();
        }
        long b = System.currentTimeMillis();
        System.out.println(b - a);
        a = System.currentTimeMillis();
        Pattern emailPattern = Pattern.compile("^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
        for (int i = 0; i < count; i++) {
            emailPattern.matcher(temp).matches();
        }
        b = System.currentTimeMillis();
        System.out.println(b - a);


        String reg = "/agencies/[\\S]+/reservation-groups/((!references).)*/[\\S]+";
        String text = "/agencies/xxxx/reservation-groups/references/recommit";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.matches());
    }
}