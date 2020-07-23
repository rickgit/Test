package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

import org.junit.Test;

//保证内存可见性
public class _01_VolatileJMMTest {
    public volatile int age;
    public  int age2;
    @Test
    public void testFieldNoVolatile() {
       age=+1;
       age=+age;

    }
    @Test
    public void testFieldNoVolatile2() {
        age2=+1;

    }

    public static void main(String[] args) {
        _01_VolatileJMMTest volatileJMMTest = new _01_VolatileJMMTest();
        volatileJMMTest.testFieldNoVolatile();
    }

}
