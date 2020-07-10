package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HashMapTest {
    @Test
    public void testArrayAddNull() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    public void testArrayAddSame() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        map.put(null, null);
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    public void testTableSize() {
        //Returns a power of two size for the given target capacity.
        try {
            Method tableSizeFor = HashMap.class.getDeclaredMethod("tableSizeFor", int.class);
            tableSizeFor.setAccessible(true);
            Object invoke = tableSizeFor.invoke(HashMap.class, 9);
            System.out.println(invoke);
        } catch (Exception e) {//NoSuchMethodException | IllegalAccessException | InvocationTargetException e
            e.printStackTrace();
        }

    }

    @Test
    public void testInitSize() {
        HashMap<Object, String> map = new HashMap<>(1, 1);//initialCapacity,loadFactor
        Assert.assertEquals(-1, getArrayAlloc(map));
        map.put(null, null);
        Assert.assertEquals(1, getArrayAlloc(map));

        testTableSize();
        map = new HashMap<>(2, 1);
        Assert.assertEquals(-1, getArrayAlloc(map));
        map.put(null, null);
        Assert.assertEquals(2, getArrayAlloc(map));


        map = new HashMap<>(3, 1);//容量自动调整是2的整数倍
        Assert.assertEquals(-1, getArrayAlloc(map));
        map.put(null, null);
        Assert.assertEquals(4, getArrayAlloc(map));


    }
    @Test
    public void testResizeNoTree(){
        HashMap map = new HashMap<>(1, 1);
        for (int i = 0; i < 9; i++) {//达到冲突树化阀值，Map没有达到总量阀值，扩容
            map.put(new HashObj(1),i+"");//填入hash 相同的值
        }
        Assert.assertEquals(map.size(),9);
        Assert.assertEquals(getArrayAlloc(map),16);
        for (int i = 9; i < 17; i++) {//达到冲突树化阀值，Map没有达到总量阀值，扩容
            map.put(new HashObj(2),i+"");//填入hash 相同的值
        }
        Assert.assertEquals(map.size(),17);
        Assert.assertEquals(getArrayAlloc(map),32);
    }

    /**
     * 树化需要两个条件，总容量及hash冲突值
     */
    @Test
    public void testTree(){
        try {
            Field treeCap = HashMap.class.getDeclaredField("MIN_TREEIFY_CAPACITY");
            treeCap.setAccessible(true);
            Field threshold = HashMap.class.getDeclaredField("TREEIFY_THRESHOLD");
            threshold.setAccessible(true);
            Integer cap = (Integer) treeCap.get(HashMap.class);
            HashMap<Object, Object> map = new HashMap<Object, Object> (cap,1f);
            Integer integer = (Integer) threshold.get(HashMap.class)+1;
            for (int i = 0; i < integer; i++) {
                map.put(new HashObj(1),""+i);
            }
            Assert.assertEquals(map.size(),integer.intValue());
            Field elementData = null;
            elementData = map.getClass().getDeclaredField("table");
            elementData.setAccessible(true);
           Assert.assertEquals (  (((Object[])(elementData.get(map)))[1]).getClass().toString(),"class java.util.HashMap$TreeNode");
        } catch ( Exception e) {
            e.printStackTrace();
            Assert.fail();
        }


    }
    public class HashObj{
        int hash;
        HashObj(int hash){
            this.hash=hash;
        }
        @Override
        public int hashCode() {
            return hash;
        }
    }




    private static int getArrayAlloc(Object map) {
        try {
            Field elementData = null;
            elementData = map.getClass().getDeclaredField("table");
            elementData.setAccessible(true);
            Object[] objects = (Object[]) elementData.get(map);
            return objects.length;
        } catch (Exception e) {//throws NoSuchFieldException, IllegalAccessException
            return -1;
        }
    }
}
