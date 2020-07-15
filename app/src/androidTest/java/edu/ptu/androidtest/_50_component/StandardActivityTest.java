package edu.ptu.androidtest._50_component;

import android.content.Intent;
import android.os.IBinder;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ServiceTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import edu.ptu.androidtest.android.service.StandardService;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class StandardActivityTest {
    public StandardActivityTest(){}
    @Rule
    public ServiceTestRule activityRule = new ServiceTestRule();
    @Test
    public void test(){
        try {
            Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), StandardService.class);
            activityRule.startService(intent);
            IBinder iBinder = activityRule.bindService(intent);
            activityRule.unbindService();
        } catch (TimeoutException e) {
            e.printStackTrace();
        };
        System.out.println("activity");
    }
}
