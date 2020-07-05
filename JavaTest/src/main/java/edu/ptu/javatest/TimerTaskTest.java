package edu.ptu.javatest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTaskTest {
    @Test
    public void testTimer(){

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(1);
            }
        },0);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(2);
            }
        };
        timer.schedule(timerTask,0);
//        timer.cancel();
        try {
            Thread.sleep(TimeUnit.SECONDS.toSeconds(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
