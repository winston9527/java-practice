package com.winston.practice.jdk.thread.kongzhongwang;


import java.util.ArrayList;
import java.util.List;

/**
 * 第三题：现有程序同时启动了4个线程去调用TestDo.doSome(key, value)方法，
 * 由于TestDo.doSome(key, value)方法内的代码是先暂停1秒，
 * 然后再输出以秒为单位的当前时间值，所以，会打印出4个相同的时间值，如下所示：
 * 4:4:1258199615
 * 1:1:1258199615
 * 3:3:1258199615
 * 1:2:1258199615
 * 请修改代码，如果有几个线程调用TestDo.doSome(key, value)方法时，
 * 传递进去的key相等（equals比较为true），则这几个线程应互斥排队输出结果，
 * 即当有两个线程的key都是"1"时，它们中的一个要比另外其他线程晚1秒输出结果，如下所示：
 * 4:4:1258199615
 * 1:1:1258199615
 * 3:3:1258199615
 * 1:2:1258199616
 * 总之，当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间值（要用互斥），
 * 如果key不同，则并行执行（相互之间不互斥）。原始代码如下：
 */
//不能改动此Test类
public class Test3 extends Thread {

    private Test3Do Test3Do;
    private String key;
    private String value;

    public Test3(String key, String key2, String value) {
        this.Test3Do = Test3Do.getInstance();
			/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
			以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key + key2;
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException {
        Test3 a = new Test3("1", "", "1");
        Test3 b = new Test3("1", "", "2");
        Test3 c = new Test3("3", "", "3");
        Test3 d = new Test3("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    public void run() {
        Test3Do.doSome(key, value);
    }
}

class Test3Do {

    private Test3Do() {
    }

    private static Test3Do _instance = new Test3Do();

    public static Test3Do getInstance() {
        return _instance;
    }

    public static List<Object> keys = new ArrayList<Object>();

    public void doSome(Object key, String value) {

        synchronized (Test3Do.class) {
            if (!keys.contains(key)) {
                keys.add(key);
            } else {
                Object finalKey = key;
                //因为传入的key 每次是拼接出来的，每次都是一个新的字符串  synchronized 里面是根据 == 来判断是否是同一个对象的
                //collection 的 contains 方法 是通过 equals 方法 来判断的，所以 当key存在的时候 我们就直接取上一次的对象  即可以得到同一把锁
                key = keys.stream().filter(i -> i.equals(finalKey)).findFirst().orElse(null);
            }
        }

        synchronized (key) {
            // 以大括号内的是需要局部同步的代码，不能改动!
            {
                try {
                    Thread.sleep(1000);
                    System.out.println(key + ":" + value + ":"
                      + (System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
