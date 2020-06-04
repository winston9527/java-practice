package com.winston.practice.jdk.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ServerSocketChannelTest {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress local = new InetSocketAddress(7000);
        serverSocketChannel.bind(local);
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        int read;
        while ((read = socketChannel.read(byteBuffer)) != -1) {
            //System.out.println(new String(byteBuffer.array().));
            //byteBuffer.clear();
            byteBuffer.flip();

            String receivedRequestData = StandardCharsets.UTF_8.newDecoder().decode(byteBuffer).toString();
            System.out.println("接收到客户端的请求数据：" + receivedRequestData);
            byteBuffer.clear();
        }
        System.out.println(read);

    }

}
