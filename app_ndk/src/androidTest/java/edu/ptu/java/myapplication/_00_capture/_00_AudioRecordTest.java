package edu.ptu.java.myapplication._00_capture;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//输出是PCM语音数据
// 直接操纵硬件获取音频流数据，该过程是实时处理
public class _00_AudioRecordTest {
    @Test
    public void testAudioRecord() {
        // //采样频率一般共分为22.05KHz、44.1KHz、48KHz三个等级
        start();

    }

    protected void start() {
        //android.media.MediaCodecInfo 支持的采样率
        int sampleRateInHz = 44100;
        int mRecordBufferSize = AudioRecord.getMinBufferSize(sampleRateInHz
                , AudioFormat.CHANNEL_IN_MONO
                , AudioFormat.ENCODING_PCM_16BIT);// 不同设备缓存不一样
        AudioFormat audioFormat = new AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)//音强/响
                .setSampleRate(sampleRateInHz)//              音调/高
                .setChannelMask(AudioFormat.CHANNEL_IN_MONO)//音品/色
                .build();
        AudioRecord mAudioRecord = new AudioRecord.Builder()
                .setAudioSource(MediaRecorder.AudioSource.MIC)//MIC 麦克风；
                .setAudioFormat(audioFormat)
                .setBufferSizeInBytes(mRecordBufferSize)
                .build();//java.lang.UnsupportedOperationException: Cannot create AudioRecord

        mAudioRecord.startRecording();//开始录制
        FileOutputStream fileOutputStream = null;
        boolean mWhetherRecord = true;
        try {
            File filesDir = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext().getFilesDir();
            fileOutputStream = new FileOutputStream(new File(filesDir, "audioRecord.pcm"));
            byte[] bytes = new byte[mRecordBufferSize];
            long time=System.currentTimeMillis();
            while (mWhetherRecord) {
                mAudioRecord.read(bytes, 0, bytes.length);//读取流
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                System.out.println("录制。。。");
                if ((System.currentTimeMillis()-time)/1000>130){
                    mWhetherRecord=false;
                }
            }
            mAudioRecord.stop();//停止录制
            if (fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
//            addHeadData();//添加音频头部信息并且转成wav格式
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            mAudioRecord.release();
        }
    }

}
