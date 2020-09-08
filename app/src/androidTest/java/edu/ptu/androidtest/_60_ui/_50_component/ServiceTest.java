package edu.ptu.androidtest._60_ui._50_component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest.LifeActivity;
import edu.ptu.androidtest.android.service.ServiceActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static edu.ptu.androidtest._60_ui._50_component._00_LifeCyclerTest.startActivityFromApp;

public class ServiceTest {
    @Test
    public void testServiceLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity activity = _00_LifeCyclerTest.startActivityFromInstrumentation(targetContext, ServiceActivity.class);
        Assert.assertTrue(true);
    }
    public   static void startActivityFromInstrumentation(Context context, Class newActivity) {
        Intent intent = new Intent(context, newActivity);
        context.startService(intent);
    }
}
