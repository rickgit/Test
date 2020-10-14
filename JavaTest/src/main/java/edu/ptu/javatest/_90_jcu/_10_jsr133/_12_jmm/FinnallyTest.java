package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm;

import org.junit.Test;

public class FinnallyTest {
    @Test
    public void testFinnnaly(){
        String returnval = returnval();
        System.out.println(returnval);

    }

    private String returnval() {
        try{
            return  "1";
        }finally {
            System.out.println("结束");
            return "2";
        }
    }
}
