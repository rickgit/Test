package edu.ptu.javatest._90_jcu;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
//保证原子性（读改写）
public class _02_CAS {

    @Test
   public void testThreadWithAdd(){

        ThreadWithAdd threadWithAdd = new ThreadWithAdd();
        for (int i = 0; i < 10; i++) {
            new Thread(threadWithAdd).start();
        }
        try {
            Thread.sleep(1000);//Junit需要等待其他线程完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static class ThreadWithAdd implements Runnable{
         volatile int num;
        @Override
        public void run() {
            try {
                Thread.sleep(300);//加上这个保证线程已经从内存读到线程里
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("current num : "+(++num));;//读改写，会出现问题
        }


    }
    @Test
    public void testThreadWithCASAdd(){

        ThreadWithCASAdd threadWithAdd = new ThreadWithCASAdd();
        for (int i = 0; i < 10; i++) {
            new Thread(threadWithAdd).start();
        }
        try {
            Thread.sleep(1000);//Junit需要等待其他线程完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static class ThreadWithCASAdd implements Runnable{
        AtomicInteger num=new AtomicInteger(0);
        @Override
        public void run() {
            try {
                Thread.sleep(300);//加上这个保证线程已经从内存读到线程里
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("current num : "+num.incrementAndGet());
        }


    }
}
