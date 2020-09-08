package edu.ptu.androidtest.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;

import androidx.annotation.Nullable;

public class StandardService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(StandardService.class.getName()+" onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(StandardService.class.getName()+" onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println(StandardService.class.getName()+" onStart");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(StandardService.class.getName()+" onUnbind");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(StandardService.class.getName()+" onBind");
        return new Messenger(new Handler()).getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        System.out.println(StandardService.class.getName()+" onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        System.out.println(StandardService.class.getName()+" onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        System.out.println(StandardService.class.getName()+" onDestroy");
        super.onDestroy();
    }
}
