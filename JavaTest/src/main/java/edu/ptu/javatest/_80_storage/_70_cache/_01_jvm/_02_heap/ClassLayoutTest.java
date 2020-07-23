package edu.ptu.javatest._80_storage._70_cache._01_jvm._02_heap;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutTest {
    @Test
    public void test() {
//        String s = ClassLayout.parseInstance(new Integer[]{1,32,23,123}).toPrintable();
        String s = ClassLayout.parseInstance(new Object[]{new Object(), new Object()}).toPrintable();
        System.out.println(s);

        s = ClassLayout.parseInstance(new Object()).toPrintable();
        System.out.println(s);
    }
}
