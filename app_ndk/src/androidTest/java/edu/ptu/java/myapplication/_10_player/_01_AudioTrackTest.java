package edu.ptu.java.myapplication._10_player;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//输出是PCM语音数据
// 直接操纵硬件获取音频流数据，该过程是实时处理
public class _01_AudioTrackTest {
    @Test
    public void testAudioTrack() {
        // //采样频率一般共分为22.05KHz、44.1KHz、48KHz三个等级
        startTrack();

    }

    protected void startTrack() {
        //android.media.MediaCodecInfo 支持的采样率
        int sampleRateInHz = 44100;
        int mRecordBufferSize = AudioTrack. getMinBufferSize(sampleRateInHz
                , AudioFormat.CHANNEL_OUT_STEREO//此处需要修改为out
                , AudioFormat.ENCODING_PCM_16BIT);// 不同设备缓存不一样
        AudioFormat audioFormat = new AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)//音强/响
                .setSampleRate(sampleRateInHz)//              音调/高
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)//音品/色
                .build();
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        AudioTrack audioTrack = new AudioTrack.Builder()
                .setAudioFormat(audioFormat)
                .setBufferSizeInBytes(mRecordBufferSize)
                .setAudioAttributes(attributes)
                .build();//java.lang.UnsupportedOperationException: Cannot create AudioRecord
        FileInputStream fis = null;
        try {
            byte[] bytes = new byte[mRecordBufferSize];
            long time=System.currentTimeMillis();
            File filesDir = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext().getFilesDir();
            fis = new FileInputStream(new File(filesDir, "audioRecord.pcm"));
            int size=0;
            while ((size = fis.read(bytes)) > 0) {
                int ret = audioTrack.write(bytes, 0, bytes.length);
                if (ret == AudioTrack.ERROR_BAD_VALUE || ret == AudioTrack.ERROR_INVALID_OPERATION || ret == AudioManager.ERROR_DEAD_OBJECT) {
                    break;
                }
                System.out.println("播放"+size);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            audioTrack.stop();
            audioTrack.release();
        }
    }
}
