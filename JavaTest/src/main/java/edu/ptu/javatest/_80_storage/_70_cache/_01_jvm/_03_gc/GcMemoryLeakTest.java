package edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc;

import org.junit.Test;

import java.util.ArrayList;

public class GcMemoryLeakTest {
    //-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
    @Test
    public void testDump(){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(GcTest.getKb(1024));
        }
    }
}
