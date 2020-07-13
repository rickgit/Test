package edu.ptu.javatest._80_storage._70_cache._01_jvm._02_heap;

import org.junit.Test;

import edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc.GcTest;


public class HeapTest {
    //查看堆栈情况(单位：kb，秒) 默认75：12：12；65024.0kb：10752kb
    //jps |grep JUnit| awk '{system("jstat -gc "$1" 5000")}'
    @Test
    public void testSurvivorSwap(){// minor gc :0.009s;  full gc :  0.274s
        for (int i = 0; i < 100; i++) {
            byte[] b= GcTest.getKb(1014*100);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
