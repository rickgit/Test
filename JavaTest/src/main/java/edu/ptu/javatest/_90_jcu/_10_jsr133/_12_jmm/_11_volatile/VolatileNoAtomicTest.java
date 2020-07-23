package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class VolatileNoAtomicTest {
    private volatile int count=0;
    @Test
    public void testAtomic(){
        ArrayList<Thread> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        count++;
                    }
                }
            });
            thread.start();
            objects.add(thread);
        }
        for (int i = 0; i < objects.size(); i++) {
            try {
                objects.get(i).join();//等待这些线程执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(count);//count <=1000
        Assert.assertTrue(count<=10*1000);
    }
}
