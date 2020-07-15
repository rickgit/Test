package edu.ptu.javatest._80_storage._70_cache;
//https://docs.oracle.com/javase/8/docs/technotes/guides/io/example/index.html

//查看非堆，堆外内存
//-XX:NativeMemoryTracking=[off | summary | detail]
//-XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics
// -Xmx10m -Xmx10m

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.EnumSet;
import java.util.concurrent.Future;

import sun.nio.ch.DirectBuffer;

public class _10_NioTest {
    @Test
    public void testNioHeapBuffer() {
        try {
            ByteBuffer allocate = ByteBuffer.allocate(1024 * 1024 * 10);
            Assert.assertFalse(allocate.isDirect());

        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), OutOfMemoryError.class);
        }
        ByteBuffer allocate = ByteBuffer.allocate(1024 * 1024 * 1);
        Assert.assertFalse(allocate.isDirect());

    }

    @Test
    public void testNioDirectBuffer() {
        ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 10);
        Assert.assertTrue(bb.isDirect());
        //清除直接缓存

        ((DirectBuffer) bb).cleaner().clean();
    }
    @Test
    public void testNioHeapBufferMapped()   {
        try {
            RandomAccessFile f = new RandomAccessFile("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java", "rw");
//            FileInputStream f = new FileInputStream("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java");
            FileChannel fc = f.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(20);
            fc.read(allocate);
            allocate.flip();
            Assert.assertFalse(allocate.isDirect());
            System.out.println(Charset.forName("UTF-8").decode(allocate).toString());  //只是一个提醒而不是guarantee
            fc.close();
            f.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //-Xmx10m -Xmx10m -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics -XX:NativeMemoryTracking=detail -XX:MaxDirectMemorySize=2m
    @Test
    public void testNioDirectBufferMapped()   {
        try {
            RandomAccessFile f = new RandomAccessFile("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java", "rw");
//            FileInputStream f = new FileInputStream("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java");
            FileChannel fc = f.getChannel();
            MappedByteBuffer buf = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
            Assert.assertTrue(buf.isDirect());
            System.out.println("is Loaded in physical memory: {}" + buf.isLoaded());  //只是一个提醒而不是guarantee
            System.out.println("capacity {}" + buf.capacity());
            System.out.println("isLoaded {}" + buf.isLoaded());//如果缓冲区的内容在物理内存中，则返回 true，否则返回 false。
            fc.close();
            f.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    @SuppressWarnings("all")
    public void testAio(){
        try {
            Path file = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            Future<Integer> result = channel.read(buffer, 0);

            while (!result.isDone()) {
                System.out.println("子文件读取中");
            }
            //反转buffer
            buffer.flip();
            System.out.println("主线程执行完毕");
            Integer bytesRead = result.get();
            System.out.println(buffer.position());
//            System.out.println(Charset.forName("UTF-8").decode(buffer).toString());
            System.out.println("Bytes read [" + bytesRead + "]");
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testSendfileTo(){//基于 sendfile 实现的 FileChannel
        long l = System.currentTimeMillis();
        try {
            FileChannel channel = FileChannel.open(Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_FileNioTest.java"));

            FileChannel channel2 = (FileChannel.open(Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile.java"),
                    EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)));//java.nio.channels.NonWritableChannelException

            channel.transferTo(0, channel.size(), channel2);


            channel.close();
            channel2.close();

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
        System.out.println(System.currentTimeMillis()-l);

    }
    @Test
    @SuppressWarnings("all")
    public void testSendfileFrom(){//基于 sendfile 实现的 FileChannel
        long l = System.currentTimeMillis();
        try {
            FileChannel channel = FileChannel.open(Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java"));

            FileChannel channel2 = (FileChannel.open(Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile.java"),
                    EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)));//java.nio.channels.NonWritableChannelException

            channel.transferFrom(channel2,0, channel.size() );


            channel.close();
            channel2.close();

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
        System.out.println(System.currentTimeMillis()-l);

    }
}