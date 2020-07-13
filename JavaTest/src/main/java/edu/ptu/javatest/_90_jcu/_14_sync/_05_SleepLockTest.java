package edu.ptu.javatest._90_jcu._14_sync;

import org.junit.Test;

public class _05_SleepLockTest {
    @Test
    public void testSleep(){
        Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock)   {
                    System.out.println("thread a lock...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread a unlock...");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock)   {
                    System.out.println("thread b lock...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread b unlock...");
                }
            }
        }).start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
