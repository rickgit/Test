package edu.ptu.java.myapplication._10_player;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import edu.ptu.java.myapplication.R;

//点击声音
public class _10_SoundPoolTest {
    @Test
    public void test() {
        SoundPool soundPool = null;
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder()
            //传入最多播放音频数量,
            .setMaxStreams(1)
            .setAudioAttributes(
                    new AudioAttributes.Builder()//封装音频各种属性的方法
                    .setLegacyStreamType(AudioManager.STREAM_SYSTEM)//设置音频流的合适的属性
                    .build());
            soundPool = builder.build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0);
        }
        //加载音频文件
        final int loadid = soundPool.load(InstrumentationRegistry.getInstrumentation().getTargetContext(), R.raw.win, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(loadid, 1, 1, 0, 0, 1);

            }
        });
        soundPool.play(loadid, 1, 1, 0, 0, 1);
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
