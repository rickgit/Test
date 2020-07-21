package edu.ptu.androidtest._20_dsa;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapTest {
    @Test
    public void testGrow(){
        edu.ptu.javatest._60_dsa.ConcurrentHashMapTest concurrentHashMapTest = new edu.ptu.javatest._60_dsa.ConcurrentHashMapTest();
        concurrentHashMapTest.testThread();

        System.out.println();
    }
}
