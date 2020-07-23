package edu.ptu.javatest._90_jcu._10_jsr133._16_sync;

import org.junit.Test;

/**
 * 独占锁产生的问题
 */
public class _05_DeathLockTest {
    @Test
    public void testDeathLock() {
        ResourceA a = new ResourceA();
        ResourceB b = new ResourceB();
        a.setB(b);
        b.setA(a);

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.doResourceAWork();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.doResourceBWork();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ResourceA {
        ResourceB b;

        ResourceA() {

        }

        public void setB(ResourceB b) {
            this.b = b;
        }

        public void doResourceAWork() {
            System.out.println("======= doResourceAWork start");
            boolean flag=true;
            synchronized (b) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    System.out.println("======= doResourceAWork doning");

                }
            }
            System.out.println("======= doResourceAWork done");
        }
    }

    public static class ResourceB {
        ResourceA a;

        ResourceB() {

        }

        public void setA(ResourceA a) {
            this.a = a;
        }

        public void doResourceBWork() {
            System.out.println("======= doResourceBWork start");
            synchronized (a) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    System.out.println("======= doResourceBWork doing");

                }
            }
            System.out.println("======= doResourceBWork done");
        }
    }
}
