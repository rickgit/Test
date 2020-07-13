package edu.ptu.javatest._90_jcu;

import org.junit.Assert;
import org.junit.Test;
//保证内存可见性
public class _01_VolatileTest {
    @Test
    public void testFieldNoVolatile() {
        ThreadFieldNoVolatile threadFieldNoVolatile = new ThreadFieldNoVolatile();
        new Thread(threadFieldNoVolatile).start();
        long l = System.currentTimeMillis();
        boolean timeout = false;
        while (true) {
            if (threadFieldNoVolatile.flag || (timeout = ((System.currentTimeMillis() - l) > 3000))) {//永远不会执行，使用的是栈里的lvt数据
                System.out.println("break;");
                break;
            }
        }

        Assert.assertTrue(timeout);
    }

    @Test
    public void testFieldNoVolatileWhileDelay() {
        ThreadFieldNoVolatile threadFieldNoVolatile = new ThreadFieldNoVolatile();
        new Thread(threadFieldNoVolatile).start();
        long l = System.currentTimeMillis();
        boolean timeout = false;
        while (true) {
            try {
                Thread.sleep(300);//暂停，到堆读取数据到栈里
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (threadFieldNoVolatile.flag || (timeout = ((System.currentTimeMillis() - l) > 3000))) {
                System.out.println("break;");
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - l);
        Assert.assertFalse(timeout);
    }

    @Test
    public void testFieldNoVolatileSyncObj() {
        ThreadFieldNoVolatile threadFieldNoVolatile = new ThreadFieldNoVolatile();
        new Thread(threadFieldNoVolatile).start();
        long l = System.currentTimeMillis();
        boolean timeout = false;
        while (true) {
            synchronized (threadFieldNoVolatile) {
                if (threadFieldNoVolatile.flag || (timeout = ((System.currentTimeMillis() - l) > 3000))) {
                    System.out.println("break;");
                    break;
                }
            }
        }
        System.out.println(System.currentTimeMillis() - l);
        Assert.assertFalse(timeout);
    }

    public static class ThreadFieldNoVolatile implements Runnable {
        Boolean flag = false;

        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag=true");
        }
    }

    @Test
    public void testFieldNoVolatileStaticFlag() {
        ThreadFieldStatic thread = new ThreadFieldStatic();
        new Thread(thread).start();
        long l = System.currentTimeMillis();
        boolean timeout = false;
        while (true) {
            if (thread.flag || (timeout = ((System.currentTimeMillis() - l) > 3000))) {
                System.out.println("break;");
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - l);
        Assert.assertTrue(timeout);
    }

    public static class ThreadFieldStatic implements Runnable {
        static Boolean flag = false;

        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag=true");
        }
    }

    @Test
    public void testFieldVolatile() {
        ThreadFieldVolatile threadFieldVolatile = new ThreadFieldVolatile();
        new Thread(threadFieldVolatile).start();
        long l = System.currentTimeMillis();
        boolean timeout = false;
        while (true) {
            if (threadFieldVolatile.flag || (timeout = ((System.currentTimeMillis() - l) > 3000))) {
                System.out.println("break;");
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - l);
        Assert.assertFalse(timeout);
    }

    public static class ThreadFieldVolatile implements Runnable {
        volatile boolean flag;

        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag=true");
        }
    }
}
