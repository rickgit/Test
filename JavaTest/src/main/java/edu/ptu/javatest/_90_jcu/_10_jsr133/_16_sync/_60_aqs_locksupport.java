package edu.ptu.javatest._90_jcu._10_jsr133._16_sync;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class _60_aqs_locksupport {
    @Test
    public void testLockSupport(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park ........");
                LockSupport.park();
                System.out.println("park end ........");
            }
        });
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("unpark ...........");
        LockSupport.unpark(thread);
        System.out.println("unpark success ...........");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
