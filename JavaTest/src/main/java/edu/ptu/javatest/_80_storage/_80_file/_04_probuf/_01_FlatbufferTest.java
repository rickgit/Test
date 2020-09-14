package edu.ptu.javatest._80_storage._80_file._04_probuf;

import com.google.flatbuffers.FlatBufferBuilder;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

//flatc --proto addressbook.proto 支持转化protobuffer
//flatc -j addressbook.fbs 转化为java
//使用“零拷贝”策略
//[flatbuffer编码的内存的结构](https://blog.csdn.net/weixin_42869573/article/details/83820166)
public class _01_FlatbufferTest {
    @Test
    public void testHello() {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int hello = builder.createString("hello");//c0000060 8040600 0400050 0068656c6c6f 000
        int fbHello = FbHello.createFbHello(builder, hello);
        builder.finish(fbHello);
        byte[] bytes = builder.sizedByteArray();
        System.out.println(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            //0c 00 00 00 | 00 00 | 06 00 | 08 00 04 00 | 06 00 00 00 | 04 00 00 00 _ 05 00 00 00 _ 68 65 6c 6c 6f 00 00 00
            System.out.print( String.format("%2s",Integer.toHexString(bytes[i])).replace(' ','0')) ;
//            System.out.print(Character.toChars(bytes[i])) ;
                System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < "hello".length(); i++) {
            System.out.print(Integer.toHexString("hello".charAt(i)) );

        }
        FbHello rootAsFbHello = FbHello.getRootAsFbHello(ByteBuffer.wrap(bytes));
        String name = rootAsFbHello.name();
        //字符串：uint32_t+int8_t+ 0 termination + padding.
    }

    @Test
    public void testFlatBuffer() {
        //flatbuffer代码小，直接拷贝到项目com.google.flatbuffers

        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int nameOffset = builder.createString("hello");
        int time1 = builder.createString("时间1");
        int time2 = builder.createString("时间2");
        int time3 = builder.createString("时间3");
        int titmeOffSet = builder.createVectorOfTables(new int[]{time1, time2, time3});
        int testOffset = builder.createByteVector(new byte[]{1, 2, 5, 10});
        int fbDemo = FbDemo.createFbDemo(builder, nameOffset, 30, 1, titmeOffSet, testOffset);
        builder.finish(fbDemo);
        byte[] demoArr = builder.sizedByteArray();
        System.out.println(demoArr.length);
        for (int i = 0; i < demoArr.length; i++) {
            //14 00 00 00 00 00 | 0e 00 20 00 04 00 08 00 14 00 0c 00 10 00 | 0e 00 00 00 34 00 00 00 1e 00 00 00 1c 00 00 00 10 00 00 00 00 00 00 00 00 00 fffffff0 3f 00 00 00 00 04 00 00 00 01 02 05 0a 03 00 00 00 17 00 00 00 13 00 00 00 0f 00 00 00 05 00 00 00 68 65 6c 6c 6f 00 00 00
             System.out.print( String.format("%2s",Integer.toHexString(demoArr[i])).replace(' ','0')) ;
//            System.out.print(Character.toChars(bytes[i])) ;
            System.out.print(" ");
        }
        FbDemo rootAsFbDemo = FbDemo.getRootAsFbDemo(ByteBuffer.wrap(demoArr));
        rootAsFbDemo.name();
        rootAsFbDemo.age();
        rootAsFbDemo.money();
        String times = rootAsFbDemo.times(0);
        Assert.assertEquals(rootAsFbDemo.test(0), 1);
        Assert.assertEquals(rootAsFbDemo.test(3), 10);
        Assert.assertEquals(rootAsFbDemo.name(), "hello");

    }
}
