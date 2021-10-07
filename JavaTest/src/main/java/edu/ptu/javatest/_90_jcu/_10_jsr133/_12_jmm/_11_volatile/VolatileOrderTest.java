package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

import org.junit.Test;

import java.util.HashMap;

//有序性
public class VolatileOrderTest {
//    private volatile   int x=0;
//    private volatile  int y = 0;
    private     boolean mChangedX;
    private    boolean mChangedY;
    private boolean mAssignX, mAssignY ;

    @Test
    public void testAtomic() {
        HashMap<String, Integer> value = new HashMap();
        int i = 0;
       while(true){
           mChangedX = false;
           mChangedY = false;
            mAssignX = false;
           mAssignY = false;
            value.clear();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {Thread.yield();
                    mAssignX = true;Thread.yield();
                    mChangedX = mAssignY;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {Thread.yield();
                    mAssignY = true;Thread.yield();
                    mChangedY = mAssignX;
                }
            });

            try {
                thread.start();

                thread2.start();
                thread.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!mChangedX && !mChangedY) {//所有结果都有可能
                System.out.println("第"+(i)+"次");
                break;
            }
            i++;
        }


//        System.out.println(count);//count <=1000
//        Assert.assertTrue(count<=10*1000);
    }
}
