package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm;

import org.junit.Test;

public class CPUCacheTest {
    @Test
    public void testCPu(){
        int[] arr = new int[64 * 1024 * 1024];
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 3;
        }
        System.out.println(System.nanoTime() - start);
        long start2 = System.nanoTime();
        for (int i = 0; i < arr.length; i += 16) {
            arr[i] *= 3;
        }
        System.out.println(System.nanoTime() - start2);



    }
}
