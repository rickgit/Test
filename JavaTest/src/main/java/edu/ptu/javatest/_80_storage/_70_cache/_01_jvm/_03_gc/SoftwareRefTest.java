package edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftwareRefTest {
    public static class Meterial {
//        int run;
//
//        @Override
//        protected void finalize() throws Throwable {//重写该方法，要gc第二次才会回收
//            super.finalize();
//            run++;
//            System.out.println("finalize");
//        }
    }

    @Test
    public void testSoftRef() {//  -Xmx10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
        SoftReference<Meterial> softRef = new SoftReference<>(new Meterial());
        System.gc();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softRef.get());
        try {
            int[] ints = new int[(int) (7168*1024*0.2  )];//edi:survi:old=1:1:3；保证不填充满新生代
            int[] ints2 = new int[(int) ( (7168-6815)*1024 )];//老年代剩余空间

            try {
                Thread.sleep(3000);//更改时间会影响是否回收
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.gc();//根据本次GC 老年区阀值，更改临界点 [Full GC (System.gc()) [PSYoungGen: 1412K->1412K(2560K)] [ParOldGen: 7026K->6936K(7168K)]

        } finally {
            System.out.println(softRef.get());
        }


    }

    @Test
    public void testWeakRef() {//-Xmx8m -Xmx8m
        WeakReference<Meterial> weakRef = new WeakReference(new Meterial());
        System.gc();

        Assert.assertNull(weakRef.get());
        ReferenceQueue queue = new ReferenceQueue();
        weakRef = new WeakReference(new Meterial(), queue);
        queue.poll();


    }
    public static class PhantomRefMeterial {


//        @Override
//        protected void finalize() throws Throwable {
//            super.finalize();
//
//        }
    }



    @Test
    public void testPhantomRef() {//-Xmx8m -Xmx8m
        ReferenceQueue referenceQueue = new ReferenceQueue();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("            等待");
                    try {
                        if ((referenceQueue.remove() != null)) System.out.println("receiver msg");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                }
            }
        });
        thread.start();
        PhantomReference<Meterial> weakRef = new PhantomReference( new PhantomRefMeterial(), referenceQueue);//java 9 使用clean
        Assert.assertNull(weakRef.get());
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void testFinalRef() {//-Xmx8m -Xmx8m



    }
}
