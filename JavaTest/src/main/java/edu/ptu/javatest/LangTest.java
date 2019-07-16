package edu.ptu.javatest;

import org.junit.Test;

public class LangTest {

    @Test
    public void testFinally(){
        try {
            return;
        }finally {
            System.out.println("finnaly");
        }
    }
}
