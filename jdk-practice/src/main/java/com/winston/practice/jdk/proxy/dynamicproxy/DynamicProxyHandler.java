package com.winston.practice.jdk.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: JDK动态代理 基于接口实现
 * @Author Winston
 * @Version 1.0 2018年9月12日 上午10:10:24
 */
public class DynamicProxyHandler implements InvocationHandler {

    /**
     * 代理元对象
     */
    private Object obj;


    public DynamicProxyHandler() {
    }

    public DynamicProxyHandler(Object obj) {
        super();
        this.obj = obj;
    }

    public Object bind(Object obj) {
        this.obj = obj; // 接收业务实现类对象参数
        //obj.getClass().getInterfaces()  创建的代理类会实现这一组接口
        //方式2 直接返回代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Object proxy： 指代所代理的真实对象，也就是真实对象的代理
        // Method method: 指代所调用的真实对象的某个方法的Method对象
        // Object[] args: 指代所代理的真实对象某个方法的参数
        System.out.println(proxy.getClass().getName());
        System.out.println("调用方法：" + method);
        System.out.print("调用参数：");
        for (Object arg : args) {
            System.out.println(arg);
        }
        Object result = method.invoke(obj, args);
        System.out.println("返回结果：" + result);
        return result;
    }

}
