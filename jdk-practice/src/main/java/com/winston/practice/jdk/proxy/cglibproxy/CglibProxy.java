package com.winston.practice.jdk.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: Cglib 会根据代理类 生成一个子类进行代理。所以代理类不能加上final修饰符
 * @Author Winston
 * @Version 1.0 2018年9月12日 上午10:07:26
 */
public class CglibProxy implements MethodInterceptor {

    private Object obj;

    public Object getInstance(Object obj) {
        // 元对象赋值
        this.obj = obj;
        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 指定代理类的父类为当前元对象
        enhancer.setSuperclass(this.obj.getClass());

        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println(obj.getClass().getName());
        System.out.println(method);
        System.out.println(args);
        Object result = method.invoke(this.obj, args);
        System.out.println(result);

        return result;
    }

}
