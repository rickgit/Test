package edu.ptu.androidtest._30_storage._41_mmkv;

import androidx.test.platform.app.InstrumentationRegistry;

import com.tencent.mmkv.MMKV;

import org.junit.Test;

public class MmkvTest {
    @Test
    public void testMmkv(){
        String initialize = MMKV.initialize(InstrumentationRegistry.getInstrumentation().getTargetContext());
        System.out.println(initialize);
        MMKV mmkv = MMKV.mmkvWithID("MMKV_ID", MMKV.SINGLE_PROCESS_MODE, null);
        long l = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            mmkv.putString(""+i,i+"");
        }
        System.out.println(l = (System.nanoTime() - l));//19_964_100 相比sharedpreference快3倍
    }
}
