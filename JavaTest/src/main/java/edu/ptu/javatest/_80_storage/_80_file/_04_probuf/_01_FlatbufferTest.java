package edu.ptu.javatest._80_storage._80_file._04_probuf;

import com.google.flatbuffers.FlatBufferBuilder;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

//flatc --proto addressbook.proto 支持转化protobuffer
//flatc -j addressbook.fbs 转化为java
//使用“零拷贝”策略
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

            System.out.print( String.format("%2s",Integer.toHexString(bytes[i])).replace(' ','0')) ;
//            System.out.print(Character.toChars(bytes[i])) ;
                System.out.print(" ");
        }
        System.out.println();

        //字符串：uint32_t+int8_t+ 0 termination + padding.
    }

    @Test
    public void testFlatBuffer() {
        //flatbuffer代码小，直接拷贝到项目com.google.flatbuffers

        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int nameOffset = builder.createString("hello");
        int titmeOffSet = builder.createVectorOfTables(new int[]{1, 1, 1});
        int testOffset = builder.createByteVector(new byte[]{1, 2, 5, 10});
        int fbDemo = FbDemo.createFbDemo(builder, nameOffset, 30, 1, titmeOffSet, testOffset);
        builder.finish(fbDemo);
        byte[] demoArr = builder.sizedByteArray();
        System.out.println(demoArr.length);
        FbDemo rootAsFbDemo = FbDemo.getRootAsFbDemo(ByteBuffer.wrap(demoArr));
        Assert.assertEquals(rootAsFbDemo.name(), "hello");

    }
}
