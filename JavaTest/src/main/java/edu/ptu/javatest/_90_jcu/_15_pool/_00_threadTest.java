package edu.ptu.javatest._90_jcu._15_pool;

import org.junit.Test;

public class  _00_threadTest {
    @Test
    public void test() throws   Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run t1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t1.join();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run t2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t2.join();
    }
}
