package edu.ptu.androidtest._30_storage._42_file;

import androidx.test.platform.app.InstrumentationRegistry;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.junit.Test;

public class GlideTest {
    @Test
    public void testLoad(){
        RequestManager with = Glide.with(InstrumentationRegistry.getInstrumentation().getTargetContext());

    }
}
