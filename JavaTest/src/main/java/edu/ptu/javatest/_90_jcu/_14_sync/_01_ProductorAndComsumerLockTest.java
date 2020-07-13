package edu.ptu.javatest._90_jcu._14_sync;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * wait-notify
 */
public class _01_ProductorAndComsumerLockTest {

    @Test
    public void testProductorAndComsumer() {
        WareHouse wareHouse = new WareHouse();
        CheckIn checkIn = new CheckIn(wareHouse);
        Checkout checkout = new Checkout(wareHouse);
        new Thread(checkIn).start();
        new Thread(checkout).start();
        new Thread(checkIn).start();
        new Thread(checkout).start();
        new Thread(checkIn).start();
        new Thread(checkout).start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class WareHouse {
        static int MAX_ITEM = 10;
        int items;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void checkInItem() {
            lock.lock();
            try {
                while (items >= MAX_ITEM) {//⭐ while 防止中断或虚假唤醒
                    System.out.println("仓库满了，等待");
                    try {
                        condition.await();//⭐ 释放锁,其他线程可以进入synchronized
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //⭐ 不能else，每次都要唤醒。防止最后一次没有notify checkoutOut
                items++;
                System.out.println("仓库 checkInItem " + items);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public   void checkoutItem() {
            lock.lock();
            try {
                while (items <= 0) {//⭐while 防止中断或虚假唤醒
                    System.out.println("没有数据，等待");
                    try {
                       condition.await();//⭐ 释放锁,其他线程可以进入synchronized
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                //⭐ 不能else，每次都要唤醒。防止最后一次没 notify checkin
                items--;
                System.out.println("仓库 checkoutItem " + items);
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }
    }

    public static class CheckIn implements Runnable {
        private final WareHouse wareHouse;

        CheckIn(WareHouse wareHouse) {
            this.wareHouse = wareHouse;
        }

        @Override
        public void run() {
            int times = new Random().nextInt(10) + 5;
            System.out.println("checkin " + times);
            for (int i = 0; i < times; i++) {
                wareHouse.checkInItem();
            }

        }
    }

    public static class Checkout implements Runnable {
        private final WareHouse wareHouse;

        Checkout(WareHouse wareHouse) {
            this.wareHouse = wareHouse;
        }

        @Override
        public void run() {
            int times = new Random().nextInt(10) + 5;
            System.out.println("Checkout " + times);
            for (int i = 0; i < times; i++) {
                wareHouse.checkoutItem();
            }

        }
    }
}
