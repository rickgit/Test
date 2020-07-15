package edu.ptu.javatest._90_jcu._10_base._14_sync;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步问题：同步块；同步方法；同步锁Lock
 */
public class _00_LockTest {
    @Test
    public void testThreadMaterialProduct() {
        ThreadMaterialProduct threadMaterialProduct = new ThreadMaterialProduct();
        for (int i = 0; i < 3; i++) {
            new Thread(threadMaterialProduct).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ThreadMaterialProduct implements Runnable {
        int meterial = 100;

        @Override
        public void run() {
            while (meterial > 0) {
                System.out.println(Thread.currentThread() + "take" + "meterial left " + (meterial--));
            }
        }
    }

    @Test
    public void testThreadMaterialProductWithLock() {
        ThreadMaterialProductWithLock threadMaterialProduct = new ThreadMaterialProductWithLock();
        for (int i = 0; i < 3; i++) {
            new Thread(threadMaterialProduct).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ThreadMaterialProductWithLock implements Runnable {
        int meterial = 100;
        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            lock.lock();
            try {
                while (meterial > 0) {
                    System.out.println(Thread.currentThread() + "take" + "meterial left " + (meterial--));
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
