package edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class _30_WeakReference {
    @Test
    public void test() throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> objectWeakReference = new WeakReference<Object>(o, referenceQueue);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println("Thread start  " + objectWeakReference.get()+" "+referenceQueue.poll());
                    try {
                        if (referenceQueue.remove() != null)//poll不会阻塞，remove会阻塞
                            System.out.println("回收");
                        else System.out.println("没回收");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread.sleep(300);

        System.out.println("对象设置为null");
        o = null;//设置后GC才会加入队列
        System.gc();
        System.out.println("对象  " + objectWeakReference.get());


        Thread.sleep(3000);

    }
}
