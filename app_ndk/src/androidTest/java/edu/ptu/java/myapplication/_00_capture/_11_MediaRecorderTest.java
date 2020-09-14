package edu.ptu.java.myapplication._00_capture;

import android.hardware.Camera;
import android.media.MediaRecorder;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Timer;
import java.util.TimerTask;

//麦克风录音、从摄像头录像等
public class _11_MediaRecorderTest {
    @Test
    public void testMediaRecord() {

        Camera camera = Camera.open();

        camera.unlock();
        try {
            File filesDir = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext().getFilesDir();
             File file =new File(filesDir, "video.mp4");
            if (file.exists()) {
                // 如果文件存在，删除它，演示代码保证设备上只有一个录音文件
                file.delete();
            }

            final MediaRecorder mediaRecorder = new MediaRecorder();
            mediaRecorder.reset();
            // 设置音频录入源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置视频图像的录入源
//            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//camera1
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);//camera2 配合textureview
            // 设置录入媒体的输出格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            // 设置音频的编码格式 // 设置视频的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
// 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
            mediaRecorder.setVideoSize(1080, 1920);
            // 设置视频的采样率，每秒30帧
            mediaRecorder.setVideoFrameRate(30);
            // 设置录制视频文件的输出路径
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            // 设置捕获视频图像的预览界面
//            mediaRecorder.setPreviewDisplay(sv_view.getHolder().getSurface());

            mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    // 发生错误，停止录制
                    stop(mediaRecorder);
//                          mediaRecorder = null;
                }
            });

            // 准备、开始
            mediaRecorder.prepare();
            mediaRecorder.start();
            System.out.println("开始录影");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(13000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }stop(mediaRecorder);
                }
            });
            thread.start();
            thread.join();
            System.out.println("停止录影");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void stop(MediaRecorder mediaRecorder) {
            // 如果正在录制，停止并释放资源
            mediaRecorder.stop();
            mediaRecorder.release();
    }

}