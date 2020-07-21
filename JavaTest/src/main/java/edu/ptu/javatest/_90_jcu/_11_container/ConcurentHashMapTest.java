package edu.ptu.javatest._90_jcu._11_container;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurentHashMapTest {
    @Test
    @SuppressWarnings("all")
    public void testPutIfAbsent() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("1", "1");System.out.println(map.get("1"));
        map.put("1", "2");System.out.println(map.get("1"));
        map.putIfAbsent("1", "3");System.out.println(map.get("1"));
        map.remove("1" );System.out.println(map.get("1")+"");
        map.putIfAbsent("1", "3");System.out.println(map.get("1"));
        Assert.assertEquals(map.size(), 1);
    }
}
