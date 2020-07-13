package edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc;

import org.junit.Test;

import java.util.HashMap;

public class GcTest {

    public static byte[] getKb(int size){
        byte[] kb_10=new byte[ 1024*size];
        return kb_10;
    }
    //查看堆栈情况(单位：kb，秒)
    //jps |grep JUnit| awk '{system("jstat -gc "$1" 5000")}'
    @Test
    public void testGcRoot(){
        HashMap<Object, Object> bigMap = new HashMap<>();
        bigMap.put("1",getKb(1024));
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bigMap=null;
        System.gc();
        try {
            Thread.sleep(15000);//查看是否垃圾回收
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new GcTest().testGcRoot();
    }
}
