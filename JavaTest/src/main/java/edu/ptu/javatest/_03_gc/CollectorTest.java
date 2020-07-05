package edu.ptu.javatest._03_gc;

import org.junit.Test;

public class CollectorTest {

    //-XX:+PrintCommandLineFlags
    @Test
    public void testCollector(){
        //算法 marksweep ，copy， markcompat
        //经典回收器
        //    新生代                                        老年代               整个堆
        //串行 serial                            ,         serial Old
        //并行parNew(serial 多线程版本),parallel scavenge  ,parallel old
        //并发                                                cms,               g1（分区算法，年轻代和老年代不是连续的，打散）
    }
}
