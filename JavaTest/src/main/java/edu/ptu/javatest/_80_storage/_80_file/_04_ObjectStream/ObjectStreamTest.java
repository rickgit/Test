package edu.ptu.javatest._80_storage._80_file._04_ObjectStream;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.io.ObjectStreamConstants.SC_SERIALIZABLE;
import static java.io.ObjectStreamConstants.STREAM_MAGIC;
import static java.io.ObjectStreamConstants.STREAM_VERSION;
import static java.io.ObjectStreamConstants.TC_BASE;
import static java.io.ObjectStreamConstants.TC_CLASSDESC;
import static java.io.ObjectStreamConstants.TC_ENDBLOCKDATA;
import static java.io.ObjectStreamConstants.TC_OBJECT;

//https://docs.oracle.com/javase/8/docs/platform/serialization/spec/protocol.html
public class ObjectStreamTest {
    @Test
    public void testWrite() {
        try {

            PipedInputStream pipedInputStream = new PipedInputStream();
            PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
            ObjectOutputStream os = new ObjectOutputStream(pipedOutputStream);
            os.writeObject(new Integer(1));
            os.close();
            byte[] b = new byte[4];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(pipedInputStream);
            List<Integer> bList=new ArrayList<Integer>();
            while (true) {
                if (!(bufferedInputStream.read(b) > 0)) break;

                int value = ((b[0]) << 8 * 3) | ((0x00ff & b[1]) << 8 * 2) | ((0x0000ff & b[2]) << 8) | ((0x000000ff & b[3]));
                System.out.print(Integer.toHexString(value));
//                System.out.print(Integer.toBinaryString((b[0]<<8*3)|((0x00ff&b[1])<<8*2 )|(b[2]<<8)|(b[3]) ));
                System.out.print(" ");
                bList.add( value );
            }
            System.out.println();
            //writeStreamHeader()
            //0 魔术，版本号
            Assert.assertEquals(bList.get(0),(Integer)(STREAM_MAGIC<<16|STREAM_VERSION));
            int classlength = "java.lang.Integer".length();
            //1. 类名java.lang.Integer: tlv
            Assert.assertEquals(bList.get(1) ,(Integer) (TC_OBJECT<<24|TC_CLASSDESC<<16| classlength));

            Assert.assertEquals(bList.get(2),(Integer)('j'<<24|'a'<<16|'v'<<8|'a'));//类字符串
            Assert.assertEquals(bList.get(3),(Integer)('.'<<24|'l'<<16|'a'<<8|'n'));//类字符串
            Assert.assertEquals(bList.get(4),(Integer)('g'<<24|'.'<<16|'I'<<8|'n'));//类字符串
            Assert.assertEquals(bList.get(5),(Integer)('t'<<24|'e'<<16|'g'<<8|'e'));//类字符串
            Assert.assertEquals((Integer) (bList.get(6)&0xff000000),(Integer)(('r'&0x000000ff)<<24));//类字符串
            //2. Serializable的value
//            Assert.assertEquals();//八位是用来验证该类是否被修改过的验证码；Serializable接口后, 添加serialVersionUID 字段；long有八位；

            Assert.assertEquals((Integer) (bList.get(8)&0x00ffffff),(Integer)(SC_SERIALIZABLE<<16|1));//SC_SERIALIZABLE + short长度的字段数量
            //3. 字段数量
            //4. 字段名：tlv
            //字段解析：tag length value
            Assert.assertEquals((Integer) (bList.get(9)&0xff000000),(Integer)('I'<<24));//Integer 类型
            Assert.assertEquals((Integer) (bList.get(9)&0x00ffff00),(Integer)("value".length()<<8));//类型名称的长度
            Assert.assertEquals((Integer) (bList.get(9)&0x000000ff),(Integer) ('v'&0x000000ff));//类型名称的字符串
            Assert.assertEquals(bList.get(10),(Integer)('a'<<24|'l'<<16|'u'<<8|'e'));
            Assert.assertEquals((Integer) (bList.get(11) &0xff000000),(Integer)(TC_ENDBLOCKDATA<<24 &0xff000000));//结束
            //5. 父类信息 tlv，重复1
            Assert.assertEquals((Integer)(bList.get(11) &0x00ff0000),(Integer)(TC_CLASSDESC<<16 &0x00ff0000));//父类元元素,没有是TC_NULL 0x70
            //tlv: 16 java.lang.Number
            Assert.assertEquals((Integer)(bList.get(11) &0x0000ffff),(Integer)("java.lang.Number".length()));
            //4个字节的和serial的long值
            System.out.println(Long.toHexString(-8742448824652078965L));
            Assert.assertEquals((Integer) (bList.get(18)&0xffffffff),(Integer)(SC_SERIALIZABLE<<24|0<<8|TC_ENDBLOCKDATA ));//SC_SERIALIZABLE + short长度的字段数量+元元素结束
            Assert.assertEquals((Integer) (bList.get(19)&0xff000000),(Integer) (TC_BASE<<24));
            //最后
            Assert.assertEquals((Integer) (bList.get(19)<<8|bList.get(20)>>24), (Integer) 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
