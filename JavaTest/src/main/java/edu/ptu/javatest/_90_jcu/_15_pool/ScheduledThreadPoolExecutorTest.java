package edu.ptu.javatest._90_jcu._15_pool;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledThreadPoolExecutorTest {
    @Test
    public void testSchedulePool(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    }
}
