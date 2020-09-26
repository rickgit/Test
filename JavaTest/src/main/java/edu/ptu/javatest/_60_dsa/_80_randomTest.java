package edu.ptu.javatest._60_dsa;

import org.junit.Test;

import java.util.Random;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

public class _80_randomTest {
    @Test
    public void testRandom(){
        Random random = new Random();
        Object seed = _00_ReflectionTest.getRefFieldObj(random, Random.class, "seed");
        System.out.println(seed+" "+random.nextInt());
        Random random2 = new Random();
        Object seed2 = _00_ReflectionTest.getRefFieldObj(random, Random.class, "seed");
        System.out.println(seed2+" "+random2.nextInt());

        System.out.println((int)(Math.pow(2,31)-1));
        System.out.println("2147483647");
    }
}
