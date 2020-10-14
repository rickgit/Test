package edu.ptu.androidtest._40_throughput;

import org.junit.Test;

import edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc.FinalizeMethodTest;

public class FinalizeAndroidTest {
    @Test
    public void testFinalAndroid() {
        new FinalizeMethodTest().testFinalizeAlivable();
    }
}
