package edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc;

import org.junit.Test;

public class LocalVarTableGcTest {
    //-XX:+PrintGCDetails
    @Test
    public void testLVT() {
        byte[] kb = GcTest.getKb(1024 * 10);
        System.gc();
    }
//-XX:+PrintGCDetails
    @Test
    public void testLVT2() {
        {
            byte[] kb = GcTest.getKb(1024 * 10);
        }
        System.gc();
    }

    //-XX:+PrintGCDetails
    //[GC (System.gc()) [PSYoungGen: 20644K->1632K(75776K)] 20644K->1640K(249344K), 0.0013865 secs] [Times: user=0.14 sys=0.02, real=0.00 secs]
    //1632K <10M kb被回收了。
    @Test
    public void testLVT3() {
        {
            byte[] kb = GcTest.getKb(1024 * 10);
        }
        int a=10;//lvt 覆盖了kb的slot位置
        System.gc();
    }
}
