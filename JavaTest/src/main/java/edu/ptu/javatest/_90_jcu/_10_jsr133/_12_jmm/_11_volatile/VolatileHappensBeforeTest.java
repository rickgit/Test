package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

import org.junit.Test;

public class VolatileHappensBeforeTest {
    private int a=-1,b=-2,c=-3;
    public void write(){
        a=1;
        c=3;
        b=a;
    }
    public void read(){
        System.out.println("b:"+b+"; a:"+a+"; c:"+c);
    }

    @Test
    public void testHappensBefore() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            VolatileHappensBeforeTest test = new VolatileHappensBeforeTest();
            Thread t1 = new Thread(() -> {
                test.write();
            });

            Thread t2 = new Thread(() -> {
                test.read();
            });
            t1.start();
            t2.start();
            t1.join();t2.join();
        }
    }
}
