package edu.ptu.javatest._90_jcu._15_pool;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ForkJoinTest {
    @Test
    public void testForkJoinTest() {
        new ThreadPoolExecutor(2, 4,
                4,TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory(), (runnable, threadPoolExecutor) -> {

        });
    }
}
