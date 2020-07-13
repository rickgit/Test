package edu.ptu.javatest._90_jcu;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class _03_SynchronizyTest {
    //-XX:+PrintFlagsFinal
    @Test
    public void testUseBiasLock() {
        //jvm的信息
        try {//XX:BiasedLockingStartupDelay=4000
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//  jvm启动后4s才会启动偏向锁，jvm启动前4s大量资源竞争，开启没意义
        Object lock = new Object();
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }


    }

    //-XX:+PrintFlagsFinal bias锁需要4秒后才启动，前4s是轻量级锁
    @Test
    public void testLock() {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }

    @Test
    public void testUseBiasLock2() {
        //jvm的信息
        try {//XX:BiasedLockingStartupDelay=4000
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//  jvm启动后4s才会启动偏向锁，jvm启动前4s大量资源竞争，开启没意义
        Object lock = new Object();
        synchronized (lock) {
            synchronized (lock) {
                synchronized (lock) {
                    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                    System.out.println(lock);
                }
            }
        }


    }

    Object hardLock = new Object();

    @Test
    public void testHardLock() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (hardLock) {
                    long l = System.nanoTime();
                    System.out.println(ClassLayout.parseInstance(hardLock).toPrintable());
                    System.out.println(System.nanoTime()-l);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (hardLock) {
                    long l = System.nanoTime();
                    System.out.println(ClassLayout.parseInstance(hardLock).toPrintable());
                    System.out.println(System.nanoTime()-l);
                }
                System.out.println("第二个线程");
//                System.gc();//gc也会释放锁
                System.out.println(ClassLayout.parseInstance(hardLock).toPrintable());
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //转化位偏向锁锁
        System.out.println(ClassLayout.parseInstance(hardLock).toPrintable());
    }

    @Test
    public void testSynchron() {
        Object lock = new Object();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
