package edu.ptu.androidtest.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.Nullable;

public class ProcessService extends StandardService {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return new Messenger(new Handler()).getBinder();
    }


}
