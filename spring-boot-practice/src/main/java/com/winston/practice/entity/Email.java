package com.winston.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Email {
    private String emailAddress;

    public Email(String emailAddress) {
        System.out.println("------");
        this.emailAddress = emailAddress;
    }
}
