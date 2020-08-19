package edu.ptu.javatest._90_jcu._10_jsr133._16_sync._12_utils;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class _02_CyclicBarrierTest {

    @Test
    public void testCyclic() {
        CyclicBarrier barrier = new CyclicBarrier(3, () ->
                System.out.println(Thread.currentThread().getName() + " 完成最后任务"));
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                System.out.println();
                try {
                    System.out.println(Thread.currentThread().getName());
                    int await = barrier.await();//等待所有线程到达
                    System.out.println(Thread.currentThread().getName()+" end await "+await);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            });
            thread.setName("线程 "+i);
            thread.start();
        }
        try {
            Thread.sleep(113000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
