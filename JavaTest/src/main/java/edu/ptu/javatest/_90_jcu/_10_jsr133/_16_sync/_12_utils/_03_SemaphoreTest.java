package edu.ptu.javatest._90_jcu._10_jsr133._16_sync._12_utils;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class _03_SemaphoreTest {

    @Test
    public void testCyclic() {
        Semaphore semaphore = new Semaphore(3);//控制流量
        for (int i = 0; i < 13; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +" 正在处理");
                    Thread.sleep(3000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
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
