package edu.ptu.androidtest._40_throughput;

import org.junit.Test;

import edu.ptu.javatest._90_jcu._10_jsr133._16_sync._62_aqs_lock;

public class AQSTest {
    @Test
    public void testAqs(){
        new _62_aqs_lock().testMultiThreadFairLock();
    }
}
