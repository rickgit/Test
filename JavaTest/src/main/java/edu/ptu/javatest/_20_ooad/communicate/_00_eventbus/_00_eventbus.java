package edu.ptu.javatest._20_ooad.communicate._00_eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.Test;

public class _00_eventbus {
    public static class BussinessEvent{

    }
    @Subscribe(sticky = true)
    public void onEvent(BussinessEvent event ){
        System.out.println();
    }
    @Test
    public void testEventBus(){
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new BussinessEvent());
        EventBus.getDefault().unregister(this);

    }
}
