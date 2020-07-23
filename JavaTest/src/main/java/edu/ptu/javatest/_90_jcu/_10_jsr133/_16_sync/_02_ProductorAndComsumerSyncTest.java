package edu.ptu.javatest._90_jcu._10_jsr133._16_sync;

import org.junit.Test;

import java.util.Random;

/**
 * wait-notify
 */
public class _02_ProductorAndComsumerSyncTest {

    @Test
    public void testProductorAndComsumer() {
        WareHouse wareHouse = new WareHouse();
        CheckIn checkIn = new CheckIn(wareHouse);
        Checkout checkout = new Checkout(wareHouse);
        new Thread(checkIn).start();
        new Thread(checkout).start();
//        new Thread(checkIn).start();
//        new Thread(checkout).start();
//        new Thread(checkIn).start();
//        new Thread(checkout).start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class WareHouse {
        static int MAX_ITEM = 10;
        int items;

        public synchronized void checkInItem() {
            while (items >= MAX_ITEM) {//⭐ while 防止中断或虚假唤醒
                System.out.println("仓库满了，等待");

                try {
                    wait();//⭐ 释放锁,其他线程可以进入synchronized
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //⭐ 不能else，每次都要唤醒。防止最后一次没有notify checkoutOut
            items++;
            System.out.println("仓库 checkInItem " + items);
            notifyAll();
        }

        public synchronized void checkoutItem() {
            while (items <= 0) {//⭐while 防止中断或虚假唤醒
                System.out.println("没有数据，等待");
                try {
                    wait();//⭐ 释放锁,其他线程可以进入synchronized
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //⭐ 不能else，每次都要唤醒。防止最后一次没 notify checkin
            items--;
            System.out.println("仓库 checkoutItem " + items);
            notifyAll();

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
