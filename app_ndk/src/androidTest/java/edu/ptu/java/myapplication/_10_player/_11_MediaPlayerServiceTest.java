package edu.ptu.java.myapplication._10_player;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import edu.ptu.java.myapplication._11_media.MediaPlayerService;

/**
 * bgm
 */
public class _11_MediaPlayerServiceTest {

    @Test
    public void test(){
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        targetContext.startService(new Intent(targetContext, MediaPlayerService.class));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
