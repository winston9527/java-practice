package com.winston.practice.jdk.proxy.service;


import com.winston.practice.jdk.proxy.entity.User;

public interface UserService {

    int addUser(User user);

    User selectUser(long userId);

}
