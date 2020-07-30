package com.winston.practice.jdk.proxy.service.impl;

import com.winston.practice.jdk.proxy.entity.User;
import com.winston.practice.jdk.proxy.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static Map<Long, User> usersMap;

    static {
        System.out.println("初始化用户集合");
        usersMap = new HashMap<Long, User>();
    }

    @Override
    public int addUser(User user) {
        if (usersMap.containsKey(user.getUserId())) {

            return 0;
        } else {
            usersMap.put(user.getUserId(), user);
        }
        return 1;
    }

    @Override
    public User selectUser(long userId) {
        if (usersMap.containsKey(userId)) {
            return usersMap.get(userId);
        } else {
            return null;
        }
    }

}
