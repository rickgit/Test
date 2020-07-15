package edu.ptu.androidtest.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

public class StandardBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("StandardBroadCastReceiver"+intent.getAction()+" "+ Process.myPid());
    }
}
