package com.winston.practice.jdk.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;


@Slf4j
public class NioServerSocketChannelTest {


    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {

            InetSocketAddress local = new InetSocketAddress(7000);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(local);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int num = selector.select(2000);
                if (num > 0) {
                    log.info(">>>监听到事件:{}", num);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        if (next.isAcceptable()) {
                            //连接事件
                            log.info("连接事件");
                            ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                            SocketChannel accept = channel.accept();
                            accept.write(ByteBuffer.wrap(("Hi " + accept.getRemoteAddress() + "welcome to login").getBytes()));
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ);
                        } else if (next.isReadable()) {
                            SocketChannel channel = (SocketChannel) next.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            String receivedRequestData = StandardCharsets.UTF_8.newDecoder().decode(byteBuffer).toString();
                            //String receivedRequestData = new String(byteBuffer.array()).trim();
                            log.info(">>>收到:{}内容:{}", channel.getRemoteAddress(), receivedRequestData);

                        }
                        iterator.remove();
                    }
                }
            }
        }
    }

}
