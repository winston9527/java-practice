package com.winston.practice.jdk.proxy.test;

import com.winston.practice.jdk.proxy.cglibproxy.CglibProxy;
import com.winston.practice.jdk.proxy.dynamicproxy.DynamicProxyHandler;
import com.winston.practice.jdk.proxy.entity.User;
import com.winston.practice.jdk.proxy.service.UserService;
import com.winston.practice.jdk.proxy.service.impl.UserServiceImpl;
import com.winston.practice.jdk.proxy.staticproxy.UserServiceImplStaticProxy;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        //会生成 com.sun.proxy.$Proxy0 保存在本工程的根目录下
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //testAddUser();
        //testAddUserAndPrintLog();
        //testAddUserAndPrintLogDynamicProxy();
        testAddUserAndPrintLogCgblibProxy();
    }

    public static void testAddUserAndPrintLogCgblibProxy() {

        UserServiceImpl userService2 = new UserServiceImpl();
        UserService userServiceProxy2 = (UserService) new CglibProxy().getInstance(userService2);
        System.out.println("代理名称：" + userServiceProxy2.getClass().getName());
        User user = new User();
        long userId = System.currentTimeMillis();
        user.setUserId(userId);
        user.setUserName("xiaohong");
        user.setRealName("小红");
        user.setPhone("1539696548");

        userServiceProxy2.addUser(user);

        userServiceProxy2.selectUser(userId);

        userServiceProxy2.addUser(user);

        //Cgblib 是
        //
    }

    public static void testAddUserAndPrintLogDynamicProxy() {

        UserService userService = new UserServiceImpl();
        // Proxy.newProxyInstance 会在运行时 动态生成一个对象。
        // 该对象实现了我们传入的Class<?>[] interfaces这一组接口。所以此处可以直接强转
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(),
          new DynamicProxyHandler(userService));
        System.out.println("代理名称：" + userServiceProxy.getClass().getName());
        User user = new User();
        long userId = System.currentTimeMillis();
        user.setUserId(userId);
        user.setUserName("xiaohong");
        user.setRealName("小红");
        user.setPhone("1539696548");

        userServiceProxy.addUser(user);

        userServiceProxy.selectUser(userId);

        userServiceProxy.addUser(user);

        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler();
        // 方式2
        UserService userServiceProxyBind = (UserService) dynamicProxyHandler.bind(userService);
        // 动态代理 简化了工作量。但是必须基于接口实现。
        userServiceProxyBind.selectUser(userId);
    }

    /**
     * 现在需要针对新增和查询用户的时候 记录操作日志。 1、直接在UserServiceImpl业务代码里面增加打印日志的功能。 2、使用代理模式
     * 在代理类里面记录日志。 使用方式1 的话
     * 在实际项目中代码逻辑会很复杂，加入日志代码会导致原来的业务代码更复杂。不符合软件设计的开闭原则，并且我们应该降低程序耦合度。
     * 所以我们应该尽量避免这种与业务不相关的需求 去修改业务代码。
     *
     * @Title: testAddUserAndPrintLog
     * @Description:
     */
    public static void testAddUserAndPrintLog() {

        UserService userService = new UserServiceImplStaticProxy();
        User user = new User();
        long userId = System.currentTimeMillis();
        user.setUserId(userId);
        user.setUserName("xiaohong");
        user.setRealName("小红");
        user.setPhone("1539696548");

        userService.addUser(user);

        userService.selectUser(userId);

        userService.addUser(user);
        // 此种针对每个类创建一个代理类的方式叫做静态代理。实现起来简单，但是会有很大的工作量。
        // 如果有商品、订单、账户、交易 等很多服务的话 需要为每个类都创建一个代理类。
    }

    public static void testAddUser() {

        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUserId(System.currentTimeMillis());
        user.setUserName("xiaohong");
        user.setRealName("小红");
        user.setPhone("1539696548");
        // 成功新增用户
        userService.addUser(user);

    }

}
