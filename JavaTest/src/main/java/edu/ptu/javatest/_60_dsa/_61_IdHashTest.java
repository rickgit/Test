package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

public class _61_IdHashTest {
    @Test
    public void testInteger() {
        Assert.assertTrue(Boolean.valueOf(true) == Boolean.valueOf(true));//缓存false true
        Assert.assertTrue(Byte.valueOf((byte) 1) == Byte.valueOf((byte) 1));//256 cache个缓存
        Assert.assertFalse(Character.valueOf('a') == Character.valueOf('a'));//128个缓存

        Assert.assertTrue(Short.valueOf((short) 1) == Short.valueOf((short) 1));//256 有符号1byte
        Assert.assertTrue(Integer.valueOf(123) == Integer.valueOf(123));//128个缓存
        Assert.assertFalse(Integer.valueOf(1230) == Integer.valueOf(1230));
        Assert.assertFalse(Long.valueOf(1230) == Long.valueOf(1230));

        Assert.assertFalse(Float.valueOf(1230) == Float.valueOf(1230));//无缓存
        Assert.assertFalse(Double.valueOf(1230) == Double.valueOf(1230));//无缓存
    }

    @Test
    public void testIdHash() {
        System.identityHashCode(new Object());
    }
}
