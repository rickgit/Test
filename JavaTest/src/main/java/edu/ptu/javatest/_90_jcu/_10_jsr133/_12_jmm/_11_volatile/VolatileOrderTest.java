package edu.ptu.javatest._90_jcu._10_jsr133._12_jmm._11_volatile;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

//有序性
public class VolatileOrderTest {
//    private volatile   int x=0;
//    private volatile  int y = 0;
    private     int x=0;
    private    int y = 0;
    private int a, b = 0;

    @Test
    public void testAtomic() {
        HashMap<String, Integer> value = new HashMap();

       while(true){
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            value.clear();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
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
            if (x == 0 && y == 0) {//所有结果都有可能
//                System.out.println("第"+i+"次");
                break;
            }
        }


//        System.out.println(count);//count <=1000
//        Assert.assertTrue(count<=10*1000);
    }
}
