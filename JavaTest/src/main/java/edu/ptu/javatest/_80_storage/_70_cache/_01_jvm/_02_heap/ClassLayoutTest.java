package edu.ptu.javatest._80_storage._70_cache._01_jvm._02_heap;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutTest {
    @Test
    public void test(){
        String s = ClassLayout.parseInstance(new Integer[]{1,32,23,123}).toPrintable();
          s = ClassLayout.parseInstance(new int[]{1,32,23,123}).toPrintable();
        System.out.println(s);
    }
}
