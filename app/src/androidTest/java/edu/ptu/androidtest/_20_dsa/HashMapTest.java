package edu.ptu.androidtest._20_dsa;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HashMapTest {
    @Test
    public void testResizeNoTree(){
        HashMap map = new HashMap<>(1, 1);
        for (int i = 0; i < 9; i++) {//达到冲突树化阀值（不包括哈希表的节点），Map没有达到总量阀值，扩容
            map.put(new  HashObj(1),i+"");//填入hash 相同的值
        }
        Assert.assertEquals(map.size(),9);
        Assert.assertEquals(getArrayAlloc(map),16);
        for (int i = 9; i < 17; i++) {//达到冲突树化阀值，Map没有达到总量阀值，扩容
            map.put(new  HashObj(2),i+"");//填入hash 相同的值
        }
        Assert.assertEquals(map.size(),17);
        Assert.assertEquals(getArrayAlloc(map),32);
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
