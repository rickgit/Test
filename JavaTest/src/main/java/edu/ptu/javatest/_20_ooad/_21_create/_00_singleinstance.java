package edu.ptu.javatest._20_ooad._21_create;

import org.junit.Assert;
import org.junit.Test;

//-XX:+TraceClassLoading
public class _00_singleinstance {

    @Test
    public void testSingleInstance() {
        Assert.assertEquals(SingleInstance.getSingleinstance(), SingleInstance.getSingleinstance());
        System.out.println("testSingleInstance");
    }

    @Test
    public void testSingleInstanceDoubleCheck() {
        Assert.assertEquals(SingleInstanceDoubleCheck.getSingleinstance(), SingleInstanceDoubleCheck.getSingleinstance());
        System.out.println("testSingleInstanceDoubleCheck");
    }

    @Test
    public void testSingleInstanceStaticNestClass() {
        Assert.assertEquals(SingleInstanceStaticNestClass.getSingleinstance().getSingleinstance(), SingleInstanceStaticNestClass.getSingleinstance().getSingleinstance());

        System.out.println("testSingleInstanceStaticNestClass");
    }
    @Test
    public void testSingleInstanceEnum() {
        Assert.assertEquals(SingleInstanceEnum.INSTANCE,SingleInstanceEnum.INSTANCE);

        System.out.println("testSingleInstanceEnum");
    }
}
