package com.winston.practice.jdk.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketTest {

    public static void main(String[] args) throws IOException {

        ExecutorService s = Executors.newCachedThreadPool();

        try (ServerSocket socket = new ServerSocket(9999, 1)) {
            System.out.println("启动成功了，等待连接");
            while (true) {
                //accept 从队列里面获取一个请求  建立一个对应的socket
                Socket accept = socket.accept();
                System.out.println(Thread.currentThread().getName() + "取得连接");
                //启动一个线程  来处理当前请求的socket,但是由于启动的线程里面是同步阻塞操作的，
                //就会导致该线程被占用的时间很长，可能导致线程池的数量可能耗尽，
                s.submit(() -> {
                    try (InputStream inputStream = accept.getInputStream();) {
                        System.out.println(Thread.currentThread().getName() + "获取了输入流");
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buffer)) != -1) {
                            String content = new String(buffer, 0, len);
                            System.out.println(Thread.currentThread().getName() + "读取到数据：" + content);
                        }
                        System.out.println(Thread.currentThread().getName() + "结束了");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
