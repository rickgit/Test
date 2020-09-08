package edu.ptu.androidtest._30_storage.media;

import android.app.Activity;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.SurfaceView;

import org.junit.Test;

import java.io.IOException;

import edu.ptu.androidtest.LifeActivity;
import edu.ptu.androidtest.android.LifeTestActivity;
import edu.ptu.androidtest.android.LifeTestSecondActivity;
import edu.ptu.androidtest.android.surface.SurfaceViewActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static edu.ptu.androidtest._60_ui._50_component._00_LifeCyclerTest.startActivityFromInstrumentation;

public class CanvasCTest {

    @Test
    public void testActivityLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();

//        LifeTestActivity lifeActivity1 = (LifeTestActivity) startActivity(targetContext, LifeTestActivity.class);
//        LifeActivity newActivity = (LifeActivity) startActivity(lifeActivity1, LifeActivity.class);

        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, SurfaceViewActivity.class);

        lifeActivity1.finish();
    }

}
