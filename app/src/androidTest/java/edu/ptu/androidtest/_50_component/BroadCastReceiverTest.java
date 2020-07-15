package edu.ptu.androidtest._50_component;

import org.junit.Test;

public class BroadCastReceiverTest {
    @Test
    public void testReceiver(){
        new _00_LifeCyclerTest().testLaucherModeMix2LifeCycle();
//        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("registerReceiver"));
//        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LocalBroadcastManager.class.getSimpleName()));
//        sendBroadcast(new Intent("registerReceiver"));
//        sendBroadcast(new Intent(LocalBroadcastManager.class.getSimpleName()));
    }
}
