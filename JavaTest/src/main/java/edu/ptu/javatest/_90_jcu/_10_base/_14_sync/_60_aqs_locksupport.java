package edu.ptu.javatest._90_jcu._10_base._14_sync;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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
