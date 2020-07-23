package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

import org.junit.Assert;
import org.junit.Test;

public class FinalTest {
    static FinalTest finalTest;
    final int finalProperty;
    int noFinalProperty;

    public FinalTest() {
        finalProperty = 1;
        noFinalProperty = 2;
    }

    static void staticwrite() {
        finalTest = new FinalTest();
    }

    static void staticread() {
        if (finalTest != null) {
            int finalProperty = finalTest.finalProperty;
            Assert.assertEquals(finalProperty, 1);
            int noFinalProperty = finalTest.noFinalProperty;
            Assert.assertEquals(noFinalProperty, 2);//不能保证
        }
    }

    @Test
    public void testFinalProperty() {
        for (int i = 0; i < 1000000; i++) {
            Thread.yield();
            new Thread(() ->
                    staticwrite()
            ).start();

        }

        for (int i = 0; i < 1000000; i++) {
            Thread.yield();
            new Thread(() ->
                    staticread()
            ).start();

        }
        try {
            Thread.sleep(113000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
