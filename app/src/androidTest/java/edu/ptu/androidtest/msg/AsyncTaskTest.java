package edu.ptu.androidtest.msg;

import android.os.AsyncTask;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AsyncTaskTest {
    @Test
    public void testAsyncTask() {
        final AtomicInteger cout=new AtomicInteger(128);
        for (int i = 0; i < 128; i++) {

            final int finalI = i;
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                    synchronized (cout){
                        cout.getAndDecrement();
                    }
                }
            });
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(cout.get(),0);
    }

    @Test
    public void testAsyncTask2() {
        final AtomicInteger cout=new AtomicInteger(1024);
        for (int i = 0; i < 1024; i++) {

            final int finalI = i;
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                        cout.getAndDecrement();
                }
            });
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(cout.get(),0);
    }
    @Test
    public void testAsyncObject() {
       new AsyncTask<Object,Object,Object>(){

           @Override
           protected Object doInBackground(Object... objects) {
               return null;
           }
       }.execute();

    }
}
