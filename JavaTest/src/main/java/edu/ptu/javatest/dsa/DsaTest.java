package edu.ptu.javatest.dsa;

import org.junit.Test;

public class DsaTest {
    @Test
    public void testDsa(){
        System.out.println(minRunLength(924123423));
    }
    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= 32) {
            int r1 = r;
            System.out.println(r1 +" "+n);
            r |= (n & 1);
            boolean b = r1 != r;
            if (b)
            System.out.println(" =============  "+r1);
            n >>= 1;
        }
        return n + r;
    }
}
