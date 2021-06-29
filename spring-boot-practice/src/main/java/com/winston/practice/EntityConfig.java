package com.winston.practice;

import com.winston.practice.entity.Email;
import com.winston.practice.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//@Import({User.class})
@Configuration
public class EntityConfig {

    @Bean
    public User user01(){
        return new User("tom",11,email01());
    }

    @Bean
    public Email email01(){
        return new Email("tom@test.com");
    }

    public User hehe(){
        return new User("tom",11,email01());
    }
}
