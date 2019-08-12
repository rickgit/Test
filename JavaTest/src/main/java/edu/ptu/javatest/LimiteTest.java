package edu.ptu.javatest;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
    @Test
    public void testFileDescNum(){
        HashMap<Integer, File> file = new HashMap<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            file.put(i,new File(""));
        }


    }
}
