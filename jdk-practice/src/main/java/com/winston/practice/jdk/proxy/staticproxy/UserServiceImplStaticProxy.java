package com.winston.practice.jdk.proxy.staticproxy;

import com.winston.practice.jdk.proxy.entity.User;
import com.winston.practice.jdk.proxy.service.UserService;
import com.winston.practice.jdk.proxy.service.impl.UserServiceImpl;

/**
 * @Description: 静态代理  基于每个类编写一个代理类
 * @Author Winston
 * @Version 1.0 2018年9月11日 下午2:21:01
 */
public class UserServiceImplStaticProxy implements UserService {

    private UserService userService = new UserServiceImpl();

    @Override
    public int addUser(User user) {
        System.out.println("新增用户：" + user.toString());
        int result = userService.addUser(user);
        if (result == 1) {
            System.out.println("新增用户成功");
        } else {
            System.out.println("新增用户失败");
        }
        return result;
    }

    @Override
    public User selectUser(long userId) {
        System.out.println("查询用户：" + userId);
        User result = userService.selectUser(userId);
        if (result != null) {
            System.out.println("查询用户成功");
        } else {
            System.out.println("查询用户失败、用户不存在");
        }
        return result;
    }


}
