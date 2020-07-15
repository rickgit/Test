package edu.ptu.androidtest._30_storage._40_spf.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class SpTest {
    @Test
    public void testSp(){
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences spf = targetContext.getSharedPreferences("spf", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = spf.edit();
        edit.putString("key","value");
        boolean commit = edit.commit();
        edit.apply();
    }
}
