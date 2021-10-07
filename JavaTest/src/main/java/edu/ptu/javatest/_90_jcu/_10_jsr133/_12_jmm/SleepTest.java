package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm;

import org.junit.Test;

public class SleepTest {
    public boolean isDone;
    @Test
    public void testSleep(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDone){//必须用isDone volatile修饰，Thread.yield,Thread.sleep不会冲主存更新到线程，没有同步语义
                    Thread.yield();
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println("必须用isDone volatile修饰，循环结束");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                    isDone=true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
