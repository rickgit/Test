package edu.ptu.javatest._60_dsa;

import com.sun.org.apache.xpath.internal.operations.String;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class StringTest {
    @Test
    public void testString() {
        StringBuilder sbd = new StringBuilder();
        System.out.println(getArrayAlloc(sbd));;
        System.out.println(getArrayCount(sbd));
        Assert.assertEquals(Integer.toBinaryString(Integer.MAX_VALUE-8),"1111111111111111111111111110111");
        Assert.assertEquals( Integer.toBinaryString(Integer.MAX_VALUE-8).length(),31);
        StringBuffer sbf = new StringBuffer();
    }
    private static int getArrayAlloc(Object list) {
        try {
            Field elementData = null;
            elementData = list.getClass().getSuperclass().getDeclaredField("value");
            elementData.setAccessible(true);
            char[] objects = (char[]) elementData.get(list);
            return objects.length;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }
    private static int getArrayCount(Object list) {
        try {
            Field elementData = null;
            elementData = list.getClass().getSuperclass().getDeclaredField("count");
            elementData.setAccessible(true);
            int objects = (int) elementData.get(list);
            return objects;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }
}