package edu.ptu.javatest._80_storage._90_net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class WebSocketTest {
    @Test
    public void test(){
//        try {
//            //多路复用
//            SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();
//            System.out.println();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        URL url = null;
        try {
            url = new URL("https://www.baidu.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        testSocket("14.215.177.38");//tls_v1.2_ECDH  ping www.baidu.com
        testSocket("104.31.70.56");//tcp http://www.plantuml.com/
    }
   
    private void testSocket(String url) {
        try {
            Socket socket = new Socket(url,80);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write("hello".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
