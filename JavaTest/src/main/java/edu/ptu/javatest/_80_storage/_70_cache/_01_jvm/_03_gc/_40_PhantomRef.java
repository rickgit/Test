package edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;


//import sun.misc.Unsafe;


//PhantomReference实现堆外内存的自动释放

public class _40_PhantomRef {
//    public static long getUsafeHeap()   {
//        Field f = null;
//        try {
//            f = Unsafe.class.getDeclaredField("theUnsafe");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        f.setAccessible(true);
//        Unsafe us = null;
//        try {
//            us = (Unsafe) f.get(null);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return us.allocateMemory(1024  );
//    }
//    @Test
//    public void test(){
////        ByteBuffer.allocateDirect()//里面是使用Clear
//        HashMap<Reference, FreeTask> heapObjFreeTaskHashMap = new HashMap<>();
////        Unsafe unsafe = Unsafe.getUnsafe();//java.lang.SecurityException: Unsafe
//        long address = getUsafeHeap();
//        HeapObj heapObj = new HeapObj(address);//访问堆外内存
//        ReferenceQueue<HeapObj> queue = new ReferenceQueue<>();
//        PhantomReference<HeapObj> reference = new PhantomReference<>(heapObj, queue);
//        heapObjFreeTaskHashMap.put(reference,new FreeTask(address));
//        //堆外内存没有强引用
//        //clear thread
//        new Thread(() -> {
//            while (true){
//                try {
//                    Reference<? extends HeapObj> remove = queue.remove();
//                    FreeTask freeTask = heapObjFreeTaskHashMap.get(remove);
//                    freeTask.run();//free
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//    static class HeapObj {
//        long address;
//        public HeapObj(long address){
//            this.address=address;
//        }
//
//    }
//    static class FreeTask implements Runnable{
//        long address;
//        public FreeTask(long address){
//            this.address=address;
//        }
//
//        @Override
//        public void run() {
//            Unsafe.getUnsafe().freeMemory(address);
//        }
//    }
}
