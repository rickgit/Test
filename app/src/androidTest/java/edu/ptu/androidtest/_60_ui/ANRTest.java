package edu.ptu.androidtest._60_ui;

import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;

import org.junit.Test;

public class ANRTest {
    @Test
    public void testANR(){
        new Handler(msg -> false);
        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        Message obtain = Message.obtain(new Handler(), () -> {

        });

        new Handler().getLooper().getQueue().addIdleHandler(() -> false);
    }
}
