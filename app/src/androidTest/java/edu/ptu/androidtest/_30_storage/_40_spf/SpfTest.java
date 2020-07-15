package edu.ptu.androidtest._30_storage._40_spf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.util.Map;

public class SpfTest {
    @Test
    public void testSpf() {
        SharedPreferences spf = InstrumentationRegistry.getInstrumentation().getTargetContext()
                .getSharedPreferences("spf", Context.MODE_PRIVATE);
//        long l = System.nanoTime();
        long l = System.nanoTime();
        spf.edit().putString("key", "2").commit();
        System.out.println(l = (System.nanoTime() - l));//5_362_900

        l = System.nanoTime();
        spf.edit().putString("key", "3").apply();
        System.out.println(l = (System.nanoTime() - l));//736_400

    }
    @Test
    public void testSpfBatch() {
        SharedPreferences spf = InstrumentationRegistry.getInstrumentation().getTargetContext()
                .getSharedPreferences("spf", Context.MODE_PRIVATE);
//        long l = System.nanoTime();
        spf.edit().clear().commit();
        long l = System.nanoTime();
        SharedPreferences.Editor edit = spf.edit();
        for (int i = 0; i < 1000; i++) {
            edit.putString(""+i,i+"");
        }
        edit.commit();
        System.out.println(l = (System.nanoTime() - l));//76_026800

        l = System.nanoTime();
         edit = spf.edit();
        for (int i = 0; i < 1000; i++) {
            edit.putString(""+i,i+"");
        }
        edit.apply();
        System.out.println(l = (System.nanoTime() - l));//2_883000


        l = System.nanoTime();
        Map<String, ?> all = spf.getAll();
        System.out.println( (System.nanoTime() - l));//read 6_384_500
    }
}
