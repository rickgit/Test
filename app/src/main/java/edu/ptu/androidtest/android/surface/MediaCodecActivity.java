package edu.ptu.androidtest.android.surface;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaCodecActivity extends FragmentActivity {

    private SurfaceView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new SurfaceView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(view);
        view.getHolder().addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                new Thread(new DrawTask(holder)).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    class DrawTask implements Runnable {
        private final SurfaceHolder holder;
        private final boolean drawing;

        public DrawTask(SurfaceHolder holder) {
            this.holder = holder;
            this.drawing = true;
        }

        @Override
        public void run() {
            try {
                testMediaCodec(holder.getSurface());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        //获取指定类型媒体文件所在轨道
        private int getMediaTrackIndex(MediaExtractor videoExtractor, String MEDIA_TYPE) {
            int trackIndex = -1;
            // 获得轨道数量
            int trackNum = videoExtractor.getTrackCount();
            for (int i = 0; i < trackNum; i++) {
                MediaFormat mediaFormat = videoExtractor.getTrackFormat(i);
                String mime = mediaFormat.getString(MediaFormat.KEY_MIME);
                if (mime.startsWith(MEDIA_TYPE)) {
                    trackIndex = i;
                    break;
                }
            }
            return trackIndex;
        }
        private MediaFormat chooseVideoTrack(MediaExtractor extractor) {
            int count = extractor.getTrackCount();
            for (int i = 0; i < count; i++) {
                MediaFormat format = extractor.getTrackFormat(i);
                if (format.getString(MediaFormat.KEY_MIME).startsWith("video/")){
                    extractor.selectTrack(i);//选择轨道
                    return format;
                }
            }
            return null;
        }


        public void testMediaCodec(Surface surface) throws IOException {
              videoExtractor = new MediaExtractor();
            try {
                videoExtractor.setDataSource("/sdcard/out_m.mp4");//需要到设置看下是否有外部存储权限
            } catch (IOException e) {
                e.printStackTrace();
            }
            int trackIndex = getMediaTrackIndex(videoExtractor, "video/");
//        MediaCodecList codecList = new MediaCodecList(MediaCodecList.REGULAR_CODECS);
            if (trackIndex >=0) {
                MediaFormat format = videoExtractor.getTrackFormat(trackIndex);
                // 指定解码后的帧格式
                format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatRGBFlexible);

                String mimeType = format.getString(MediaFormat.KEY_MIME);
                int width = format.getInteger(MediaFormat.KEY_WIDTH);
                int height = format.getInteger(MediaFormat.KEY_HEIGHT);
                // duration 是微秒 1 毫秒 = 1000微秒
                long duration = format.getLong(MediaFormat.KEY_DURATION);
//            if (callback != null) {
//                callback.getMediaBaseMsg(width, height, duration);
//            }

                // 切换到视频信道
                videoExtractor.selectTrack(trackIndex);
                // 创将解码视频的MediaCodec，解码器
                mediaCodec = MediaCodec.createDecoderByType(mimeType);
                // 配置绑定 surface
                mediaCodec.configure(format, surface, null, 0);
            }

            if (mediaCodec == null) {
//            Log.v(TAG, "MediaCodec null");
                return;
            }
            mediaCodec.start();
            mInputBuffers = mediaCodec.getInputBuffers();
            mOutputBuffers = mediaCodec.getOutputBuffers();
            new Thread(new EncoderThread()).start();
        }
    }
    private MediaCodec mediaCodec;
    MediaExtractor videoExtractor;
    ByteBuffer[] mInputBuffers;
    ByteBuffer[] mOutputBuffers;
    private class EncoderThread implements Runnable {
        boolean mIsAvailable=true;
        @Override
        public void run() {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            long startTime = System.currentTimeMillis();
            while (mIsAvailable) {
                int inputBufferIndex = mediaCodec.dequeueInputBuffer(-1);
                if (inputBufferIndex >= 0) {
                    ByteBuffer inputBuffer = mInputBuffers[inputBufferIndex];
                    inputBuffer.clear();
                    int sampleSize = videoExtractor.readSampleData(inputBuffer, 0);
                    if (sampleSize > 0) {
                        videoExtractor.advance();
                        mediaCodec.queueInputBuffer(inputBufferIndex, 0, sampleSize, videoExtractor.getSampleTime(), 0);
                    }
                }

                int outputBufferIndex = mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
                if (outputBufferIndex >= 0) {
                    long sleepTime = bufferInfo.presentationTimeUs / 1000 - (System.currentTimeMillis() - startTime);
                    if (sleepTime > 0) {
                        SystemClock.sleep(sleepTime);
                    }
                    ByteBuffer outBuffer = mOutputBuffers[outputBufferIndex];
                    mediaCodec.releaseOutputBuffer(outputBufferIndex, true);
                }
            }
            videoExtractor.release();
            mediaCodec.stop();
            mediaCodec.release();
            Log.i("==", "播放完成");
        }
    }

}
