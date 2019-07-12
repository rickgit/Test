package edu.ptu.javatest;

import org.junit.Test;

public class PerformanceTest {
    public static final int time = 1_000_000_000;


    /**<pre>
     *      0: iconst_0
     *      1: istore_1
     *      2: iload_1
     *      3: ldc           #3                  // int 1000000000
     *      5: if_icmpge     14
     *      8: iinc          1, 1
     *      11: goto          2
     *      14: return
     * </pre>
      */
    @Test
    public void testInstructions() {
        for (int i = 0; i < time; i++) {
        }
        //环境：CPU：1.6GHz，4核心，64位
        //(5*1_000_000_000+3)个指令
        //运行：4ms
        //平均： 1250指令/ns
    }
}
