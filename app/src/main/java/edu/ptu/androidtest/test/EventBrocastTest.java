package edu.ptu.androidtest.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import edu.ptu.utils.utils.ClockUtils;

public class EventBrocastTest {
    static String ACTION_TEST = "ACTION_TEST";
    static int localBrocastTime = 0;
    static int fullBrocastTime = 0;
    static int eventTime = 0;

    public static void sendLocalBrocast(Context context) {

        Intent intent = new Intent(ACTION_TEST);
        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            objects.add(""+i);
        }
        intent.putExtra("+i", objects);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        ClockUtils.getInstance().printDiffTime();
    }

    public static void registLocalBrocast(Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (localBrocastTime == 1_000_0) {// 25ms 广播可能eventbus快
                    localBrocastTime = 0;
                    ClockUtils.getInstance().printDiffTime(this,"本地广播");
                    EventBrocastTest.sendBrocast(context);
                } else {
                    localBrocastTime++;
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            }
        }, new IntentFilter(ACTION_TEST));
    }

    public static void sendBrocast(Context context) {

        Intent intent = new Intent(ACTION_TEST);
        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            objects.add(""+i);
        }
        intent.putExtra("+i", objects);
        context.sendBroadcast(intent);
        ClockUtils.getInstance().printDiffTime();
    }

    public static void registBrocast(Context context) {
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (localBrocastTime == 1_000_0) {// 7869ms
                    localBrocastTime = 0;
                    ClockUtils.getInstance().printDiffTime(this,"全局广播");
                    EventBrocastTest.postEventBus();
                } else {
                    localBrocastTime++;
                    context.sendBroadcast(intent);
                }
            }
        }, new IntentFilter(ACTION_TEST));
    }

    //==================================================================
    //==================================================================EventBus
    //==================================================================

    static Subscriber subscriber = new Subscriber();

    public static class MessageEvent { /* Additional fields if needed */
        ArrayList<String> objects = new ArrayList<>();
        {
            for (int i = 0; i < 100; i++) {
                objects.add(""+i);

            }
        }
    }

    public static class Subscriber {
        @Subscribe(threadMode = ThreadMode.BACKGROUND)
        public void onMessageEvent(MessageEvent event) {
            if (eventTime == 1_000_0) {//62ms
                eventTime = 0;
                ClockUtils.getInstance().printDiffTime(this,"EventBus 消息");
            } else {
                eventTime++;
                EventBus.getDefault().post(event);
            }
        }

        ;
    }


    public static void registEventBus() {
        EventBus.getDefault().register(subscriber);
    }

    public static void postEventBus() {
        EventBus.getDefault().post(new MessageEvent());
        ClockUtils.getInstance().printDiffTime();
    }


}
