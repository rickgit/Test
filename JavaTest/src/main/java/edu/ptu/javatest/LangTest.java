package edu.ptu.javatest;

import org.junit.Test;

public class LangTest {

    //    @Test
    public void testFinally() {
        try {
            return;
        } finally {
            System.out.println("finnaly");
        }
    }

    @Test
    public void testStringBuffer() {//3s665ms
        StringBuffer sb = null;
        for (int times = 0; times < 1000; times++) {
            sb = new StringBuffer();
            for (long i = 0; i < 1000_000; i++) {
                sb.append(1);
            }
        }

    }

    @Test
    public void testStringBuilder() {//11s972ms
        StringBuilder sb =null;
        for (int times = 0; times < 1000; times++) {
            sb = new StringBuilder();
            for (long i = 0; i < 1000_000; i++) {
                sb.append(1);
            }
        }
    }
}
