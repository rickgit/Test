package edu.ptu.androidtest._30_storage.media;

import android.app.Activity;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import edu.ptu.androidtest.android.surface.MediaCodecActivity;
import edu.ptu.androidtest.android.surface.SurfaceViewActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static edu.ptu.androidtest._60_ui._50_component._00_LifeCyclerTest.startActivityFromInstrumentation;

public class MediaCodeCTest {

    @Test
    public void testActivityLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();

//        LifeTestActivity lifeActivity1 = (LifeTestActivity) startActivity(targetContext, LifeTestActivity.class);
//        LifeActivity newActivity = (LifeActivity) startActivity(lifeActivity1, LifeActivity.class);

        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, MediaCodecActivity.class);

        lifeActivity1.finish();
    }
    @Test
    public void testMediac(){
        MediaCodecList mcl = new MediaCodecList(MediaCodecList.ALL_CODECS);
        MediaCodecInfo[] mci=      mcl.getCodecInfos();
        for (MediaCodecInfo ci : mci) {
            Log.i("testMediac", "ci =" + ci.getName());
        }
    }

}
