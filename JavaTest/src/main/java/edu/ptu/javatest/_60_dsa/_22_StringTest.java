package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

public class _22_StringTest {
    @Test
    public void testString() {
        StringBuilder sbd = new StringBuilder();
        System.out.println(getArrayAlloc(sbd));;
        System.out.println(getArrayCount(sbd));
        Assert.assertEquals(Integer.toBinaryString(Integer.MAX_VALUE-8),"1111111111111111111111111110111");
        Assert.assertEquals( Integer.toBinaryString(Integer.MAX_VALUE-8).length(),31);

        StringBuilder sbd2 = new StringBuilder(0);
        System.out.println(getArrayAlloc(sbd2));;
        System.out.println(getArrayCount(sbd2));
        sbd2.append("f");
        System.out.println(getArrayAlloc(sbd2));;
        System.out.println(getArrayCount(sbd2));


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
