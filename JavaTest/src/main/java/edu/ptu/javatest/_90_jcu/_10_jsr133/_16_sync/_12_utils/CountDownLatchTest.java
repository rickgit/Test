package edu.ptu.javatest._90_jcu._10_jsr133._16_sync._12_utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

import edu.ptu.javatest._90_jcu._10_jsr133._16_sync._62_aqs_lock;
import edu.ptu.javatest._90_jcu._10_jsr133._16_sync._63_rwlock;

public class CountDownLatchTest {
    @Test
    public void testCountDownLatch() {
        _63_rwlock.printShareObj();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                printCountDownSyn(countDownLatch);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ruing");
        }).start();
        printCountDownSyn(countDownLatch);
        countDownLatch.countDown();
        printCountDownSyn(countDownLatch);
        countDownLatch.countDown();
        printCountDownSyn(countDownLatch);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }printCountDownSyn(countDownLatch);
        System.out.println();
    }

    public static void printCountDownSyn(CountDownLatch countDownLatch) {
        try {
            Field sync = countDownLatch.getClass().getDeclaredField("sync");
            sync.setAccessible(true);
            Object o = sync.get(countDownLatch);
            _62_aqs_lock.printAqs(o);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
