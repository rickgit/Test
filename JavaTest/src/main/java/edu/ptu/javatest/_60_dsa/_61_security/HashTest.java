package edu.ptu.javatest._60_dsa._61_security;

import org.junit.Test;

public class HashTest {

    @Test
    public void testHashString(){
        //Double
       long EXP_BIT_MASK = 9218868437227405312L;
        System.out.println(Long.toBinaryString(EXP_BIT_MASK));//指数位
        System.out.println("11111111111".length());
        System.out.println("111111111110000000000000000000000000000000000000000000000000000".length());
       long SIGNIF_BIT_MASK = 4503599627370495L;
        System.out.println(Long.toBinaryString(SIGNIF_BIT_MASK));//小数位
        System.out.println("1111111111111111111111111111111111111111111111111111".length());

        System.out.println(Long.toBinaryString(0x7ff8000000000000L));
        System.out.println("111111111111".length());
    }
    @Test
    public void testHashObj(){

    }
}
