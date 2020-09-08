package edu.ptu.androidtest._30_storage.media.sound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MediaPlayerService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mediaPlayer = new MediaPlayer();
//            mp = MediaPlayer.create(MediaPlayerService.this, R.raw.bg1);
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void onStart(Intent intent, int startId) {

        // 开始播放音乐
        if(null!=mediaPlayer) {
            mediaPlayer.start();
            // 音乐播放完毕的事件处理
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    // 循环播放
                    try {
                        mp.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            // 播放音乐时发生错误的事件处理
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // TODO Auto-generated method stub
                    // 释放资源
                    try {
//                        if (!isEmptyObj(mp)) {
                            mp.release();
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return false;
                }
            });
        }

        super.onStart(intent, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null!=mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
