package com.winston.practice.jdk.generic;

/**
 * class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
 * <p>
 * }
 *
 * @Description: 泛型类
 * @Author Winston
 * @Version 1.0 2018年9月10日 下午3:57:10
 */
public class GenericType<T> {

    private T data;

    public GenericType(T data) {
        super();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GenericType [data=" + data + "]";
    }

    /**
     * @param tClass
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @Title: genericMethod
     * @Description: 泛型的声明，必须在方法的修饰符（public,static,final,abstract等）之后，返回值声明之前。此处的T表示泛型方法
     * 和public class GenericType <T> 里面的T 不是一个意思
     */
    public static <T> T genericMethod(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

}
