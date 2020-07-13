package edu.ptu.javatest._90_jcu._15_pool;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    @Test
    public void testThreadPool(){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4,
                4, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory(), (runnable, threadPoolExecutor) -> {

        });
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    @Test
    public void testCtlOf(){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4,
                4, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory(), (runnable, threadPoolExecutor) -> {

        });

        Method ctlOf = null;
        try {


            Assert.assertEquals(Integer.toBinaryString(getThreadPoolExecutorConst("RUNNING"))
                    ,"11100000000000000000000000000000");
            Assert.assertEquals( "11100000000000000000000000000000".length(),32);
            Assert.assertEquals( Integer.SIZE - 3 ,29);
            Assert.assertEquals(Integer.toBinaryString(getThreadPoolExecutorConst("SHUTDOWN"))
                    ,"0");
            Assert.assertEquals( Integer.toBinaryString(getThreadPoolExecutorConst("STOP"))
                    , "100000000000000000000000000000");
            Assert.assertEquals( "100000000000000000000000000000".length(),30);
            Assert.assertEquals(Integer.toBinaryString(getThreadPoolExecutorConst("TIDYING"))
                    ,"1000000000000000000000000000000");
            Assert.assertEquals( "1000000000000000000000000000000".length(),31);
            Assert.assertEquals( Integer.toBinaryString(getThreadPoolExecutorConst("TERMINATED"))
                    ,"1100000000000000000000000000000");
            Assert.assertEquals( "1100000000000000000000000000000".length(),31);


            //容量和状态公用一个Int对象，前3位是状态，后29位是容量
            Assert.assertEquals( Integer.toBinaryString(getThreadPoolExecutorConst("CAPACITY"))
                    ,"11111111111111111111111111111");
            Assert.assertEquals( "11111111111111111111111111111".length(),29);

            Assert.assertEquals(Integer.toBinaryString(~getThreadPoolExecutorConst("CAPACITY"))//
                    ,"11100000000000000000000000000000");

            //容量和状态公用一个ctlOf 对象，前3位是状态，后29位是容量
            ctlOf = ThreadPoolExecutor.class.getDeclaredMethod("ctlOf", int.class, int.class);
            ctlOf.setAccessible(true);
            Object invoke = ctlOf.invoke(poolExecutor, getThreadPoolExecutorConst("RUNNING"), 0);
            Assert.assertEquals(Integer.toBinaryString((Integer) invoke),"11100000000000000000000000000000");

            Assert.assertTrue(getThreadPoolExecutorConst("RUNNING")<0);
            Assert.assertTrue(getThreadPoolExecutorConst("RUNNING")<getThreadPoolExecutorConst("SHUTDOWN"));
        } catch ( Throwable e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    public static int getThreadPoolExecutorConst(String field){
        Object o = null;
        try {
        Field running = ThreadPoolExecutor.class.getDeclaredField(field);
        running.setAccessible(true);
            o = running.get(ThreadPoolExecutor.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (int) o;
    }

    @Test
    public void testTaskQueue(){
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();//SynchronousQueue
//        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);//LinkedBlockingQueue
//        ExecutorService poolExecutor = Executors.newScheduledThreadPool(3);// ScheduledThreadPoolExecutor.DelayedWorkQueue()
//        ExecutorService poolExecutor =  Executors.newSingleThreadExecutor();//LinkedBlockingQueue()
        for (int i = 0; i < 5; i++) {
            try {//SynchronousQueue 消耗task
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
//            System.out.print("  "+poolExecutor.toString().substring(poolExecutor.getClass().getName().length())+" ");
            System.out.print("BlockingQueue "+getThreadPoolExecutorBlockingQueue(poolExecutor).size());
            System.out.println("  ; work "+getThreadPoolWorkers(poolExecutor).size());//需要看队列是否允许添加task
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        poolExecutor.purge();
        //            System.out.print("  "+poolExecutor.toString().substring(poolExecutor.getClass().getName().length())+" ");
        System.out.print("BlockingQueue "+getThreadPoolExecutorBlockingQueue(poolExecutor).size());
        System.out.println("  ; work "+getThreadPoolWorkers(poolExecutor).size());//需要看队列是否允许添加task
    }
    //先放在corethread,然后放在队列，然后放在maximumPoolSize，RejectedExecutionHandler处理
    //回收线程，出列shutdown，阻塞获取task或者超时获取task

    //拒绝策略 DiscardOldestPolicy AbortPolicy CallerRunsPolicy DiscardPolicy
    @Test
    public void testWorkThreadRecycle(){
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4,
//                1, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(10, false),
//                new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4,
                1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),//设置最大容量
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        System.out.println("加载不了的数据"+runnable.toString());
                    }
                });
//        poolExecutor.allowCoreThreadTimeOut(true);//设置后，core 线程也能回收
        for (int i = 0; i < 1500; i++) {

            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
//            System.out.print("  "+poolExecutor.toString().substring(poolExecutor.getClass().getName().length())+" ");
            System.out.print("BlockingQueue "+getThreadPoolExecutorBlockingQueue(poolExecutor).size());
            System.out.println("  ; work "+getThreadPoolWorkers(poolExecutor).size());//需要看队列是否允许添加task
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //            System.out.print("  "+poolExecutor.toString().substring(poolExecutor.getClass().getName().length())+" ");
        System.out.print("BlockingQueue "+getThreadPoolExecutorBlockingQueue(poolExecutor).size());
        System.out.println("  ; work "+getThreadPoolWorkers(poolExecutor).size());//需要看队列是否允许添加task
    }
    public static BlockingQueue getThreadPoolExecutorBlockingQueue(Object threadPool ){
        threadPool = getRealExecutorService(threadPool);


        Object o = null;
        try {
        Field running = ThreadPoolExecutor.class.getDeclaredField("workQueue");
        running.setAccessible(true);
            o = running.get(threadPool);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (BlockingQueue) o;
    }



    public static HashSet getThreadPoolWorkers(Object threadPool ){
        threadPool = getRealExecutorService(threadPool);
        Object o = null;
        try {
        Field running = ThreadPoolExecutor.class.getDeclaredField("workers");
        running.setAccessible(true);
            o = running.get(threadPool);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (HashSet) o;
    }

    private static Object getRealExecutorService(Object threadPool) {
        if (threadPool.getClass().getSimpleName().toLowerCase().equals("FinalizableDelegatedExecutorService".toLowerCase())) {
            try {
                Field e = threadPool.getClass().getSuperclass().getDeclaredField("e");
                e.setAccessible(true);
                threadPool = e.get(threadPool);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
        return threadPool;
    }
}
