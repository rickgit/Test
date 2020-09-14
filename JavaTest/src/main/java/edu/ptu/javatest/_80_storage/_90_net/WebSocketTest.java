package edu.ptu.javatest._80_storage._90_net;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class WebSocketTest {
    @Test
    public void test(){
        try {
            //多路复用
            SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
