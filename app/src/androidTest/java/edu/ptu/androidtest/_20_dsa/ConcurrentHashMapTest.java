package edu.ptu.androidtest._20_dsa;

import org.junit.Test;

import edu.ptu.javatest._60_dsa._36_ConcurrentHashMapTest;

public class ConcurrentHashMapTest {
    @Test
    public void testGrow(){
        _36_ConcurrentHashMapTest concurrentHashMapTest = new _36_ConcurrentHashMapTest();
        concurrentHashMapTest.testThread();

        System.out.println();
    }
}
