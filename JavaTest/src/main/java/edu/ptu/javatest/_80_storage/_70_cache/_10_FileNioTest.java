package edu.ptu.javatest._80_storage._70_cache;
//https://docs.oracle.com/javase/8/docs/technotes/guides/io/example/index.html

//查看非堆，堆外内存
//-XX:NativeMemoryTracking=[off | summary | detail]
//-XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics
// -Xmx10m -Xmx10m

import org.junit.Assert;
import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import sun.nio.ch.DirectBuffer;

public class _10_FileNioTest {
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
    public void testNioHeapBufferMapped() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-Xmx10m -Xmx10m -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics -XX:NativeMemoryTracking=detail -XX:MaxDirectMemorySize=2m
    @Test
    public void testNioDirectBufferMapped() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testAio() {
        try {
            Path file = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file, StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            channel.read(buffer, 2, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer integer, ByteBuffer byteBuffer) {
                    byteBuffer.flip();
                    System.out.println(Charset.forName("UTF-8").decode(byteBuffer).toString());
                }

                @Override
                public void failed(Throwable throwable, ByteBuffer byteBuffer) {

                }
            });

//            while (!result.isDone()) {
//                System.out.println("子文件读取中");
//            }
//            //反转buffer
//            buffer.flip();
//            System.out.println("主线程执行完毕");
//            Integer bytesRead = result.get();
//            System.out.println(buffer.position());
//            System.out.println(Charset.forName("UTF-8").decode(buffer).toString());
//            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testAioWrite() {
        try {
            Path file = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile");
            if (!file.toFile().exists())
                file.toFile().createNewFile();

            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file,  StandardOpenOption.WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("aio write".getBytes());
            buffer.flip();
            channel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer integer, ByteBuffer byteBuffer) {
                    System.out.println("completed "+integer);
                }

                @Override
                public void failed(Throwable throwable, ByteBuffer byteBuffer) {
                    Assert.fail(throwable.getMessage());
                }
            });


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testSendfileTo() {//基于 sendfile 实现的 FileChannel
        long l = System.currentTimeMillis();
        try {
            FileChannel channel = FileChannel.open(Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java"));

            Path path = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile");
            if (path.toFile().exists())
                path.toFile().delete();
            FileChannel channel2 = (FileChannel.open(path,
                    EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)));//java.nio.channels.NonWritableChannelException

            channel.transferTo(0, channel.size(), channel2);


            channel.close();
            channel2.close();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        System.out.println(System.currentTimeMillis() - l);

    }

    @Test
    @SuppressWarnings("all")
    public void testSendfileFrom() {//基于 sendfile 实现的 FileChannel
        long l = System.currentTimeMillis();
        try {
            FileChannel channel = FileChannel.open(Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java"));

            Path path = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile");
            if (path.toFile().exists())
                path.toFile().delete();
            FileChannel channel2 = (FileChannel.open(path,
                    EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)));//java.nio.channels.NonWritableChannelException
            channel2.transferFrom(channel, 0, channel.size());
            channel.close();
            channel2.close();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        System.out.println(System.currentTimeMillis() - l);

    }

    @Test
    @SuppressWarnings("all")
    public void testPath2Path() {//基于 sendfile 实现的 FileChannel
        long l = System.currentTimeMillis();
        try {
            Path path = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTest.java");

            Path path2 = Paths.get("D:\\workspace\\Test\\JavaTest\\src\\main\\java\\edu\\ptu\\javatest\\_80_storage\\_70_cache\\_10_NioTestSendFile");//java.nio.channels.NonWritableChannelException
            if (path2.toFile().exists())
                path2.toFile().delete();
            Files.copy(path, path2);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        System.out.println(System.currentTimeMillis() - l);

    }
}