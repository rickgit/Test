package edu.ptu.javatest;

import org.junit.Test;

import java.util.ArrayList;

public class LimiteTest {
    @Test
    public void testThreadNum(){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread thread = new Thread();
            objects.add(thread);
            thread.start();
            System.out.println("o"+i);
        }

    }
}
