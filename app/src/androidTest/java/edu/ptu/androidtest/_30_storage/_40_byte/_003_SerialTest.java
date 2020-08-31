package edu.ptu.androidtest._30_storage._40_byte;

import com.twitter.serial.stream.bytebuffer.ByteBufferSerial;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class _003_SerialTest {
    @Test
    public void testSerial() {
        ByteBufferSerial bbs = new ByteBufferSerial();
        byte[] bytes = new byte[0];
        try {
            bytes = bbs.toByteArray(new SerialObj(2, new SerialObj.SubObject()), SerialObj.SERIALIZER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SerialObj serialTest = null;
        try {
            serialTest = bbs.fromByteArray(bytes, SerialObj.SERIALIZER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(serialTest.num,2);
    }
}
