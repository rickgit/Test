package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListTest {
    @Test
    public void testArrayAddNull() {
        ArrayList<String> list = new ArrayList<>(2);
        list.add(null);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testArrayAddSame() {
        ArrayList<String> list = new ArrayList<>(2);
        list.add(null);
        list.add(null);
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void testInitSize() {
        ArrayList<String> list = new ArrayList<>(2);
        list.add("2");
        list.add("1");
        Assert.assertEquals(getArrayAlloc(list), 2);
        list.add("3");
        Assert.assertEquals(getArrayAlloc(list), 3);
    }

    @Test
    public void testGrow() {
        ArrayList<String> list = new ArrayList<>(2);
        for (int i = 0; i < 4; i++) {
            list.add("" + i);
        }
        Assert.assertEquals(getArrayAlloc(list), 4);
        list.add("" + 5);
        Assert.assertEquals(getArrayAlloc(list), 6);//(4+4>1=6)-5>0
    }

    @Test
    public void testGrowHugeCapacity() {//-Xmx18g -Xmx18g  -XX:+PrintCommandLineFlags  防止java.lang.OutOfMemoryError: Java heap space； array.lenth=4byte=32bit=4G
        ArrayList<String> list = new ArrayList<>(Integer.MAX_VALUE - 8);//Java heap space
        for (int i = 0; i < Integer.MAX_VALUE - 8; i++) {
            list.add(null);
        }
        Assert.assertEquals(getArrayAlloc(list), Integer.MAX_VALUE - 8);
        try {
            list.add("" + 1);//java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        } catch (Throwable e) {
            Assert.assertEquals(e.getMessage(), "Requested array size exceeds VM limit");
        }

    }

    @Test
    public void testGrowHugeCapacity2() {//-Xmx18g -Xmx18g  -XX:+PrintCommandLineFlags  防止java.lang.OutOfMemoryError: Java heap space； array.lenth=4byte=32bit=4G
        ArrayList<String> list = new ArrayList<>();//Java heap space

        int last = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(null);
            int arrayAlloc = getArrayAlloc(list);
            boolean b = arrayAlloc == last;

            if (!b) {
                System.gc();
                last = arrayAlloc;
                System.out.println(arrayAlloc);
            }

        }

    }

    @Test
    public void testMax() {//-Xmx136g -Xmx136g  -XX:+PrintCommandLineFlags
        try {
            ArrayList<String> list = new ArrayList<>(Integer.MAX_VALUE);//Requested array size exceeds VM limit
            Assert.fail();
        } catch (Throwable e) {
            Assert.assertEquals(e.getMessage(), "Requested array size exceeds VM limit");
        }
    }


    private static int getArrayAlloc(ArrayList<String> list) {
        try {
            Field elementData = null;
            elementData = list.getClass().getDeclaredField("elementData");
            elementData.setAccessible(true);
            Object[] objects = (Object[]) elementData.get(list);
            return objects.length;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }
}
