package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm;

import org.junit.Test;

public class FinalFieldInitTest {

    final int finalProperty;

    public FinalFieldInitTest() throws Exception{
        finalProperty = 1;
        int a=1/0;
    }

    @Test
    public void testInitWithCrash() {
        FinalFieldInitTest finalFieldInitTest=null;
        try {
              finalFieldInitTest = new FinalFieldInitTest();
        }catch (Exception e){
//            e.printStackTrace();
        }
        if (finalFieldInitTest!=null){
            System.out.println(finalFieldInitTest.finalProperty);
        }
        System.out.println();
    }

}
