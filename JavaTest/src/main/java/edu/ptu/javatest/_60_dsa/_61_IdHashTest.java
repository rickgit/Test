package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

public class _61_IdHashTest {
    @Test
    public void testInteger(){
       Assert.assertTrue(Integer.valueOf(123)==Integer.valueOf(123));
        Assert.assertFalse(Integer.valueOf(1230) == Integer.valueOf(1230));
        Assert.assertFalse(Character.valueOf('a') == Character.valueOf('a'));//128个缓存
    }
    @Test
    public void testIdHash(){
        System.identityHashCode(new Object());
    }
}
