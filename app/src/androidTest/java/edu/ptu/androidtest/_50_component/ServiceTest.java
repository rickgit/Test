package edu.ptu.androidtest._50_component;

import android.content.Context;
import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest.android.service.ServiceActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class ServiceTest {
    @Test
    public void testLaucherModeMix2LifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        _00_LifeCyclerTest.startActivityFromInstrumentation(targetContext, ServiceActivity.class);
        Assert.assertTrue(true);
    }
    public   static void startActivityFromInstrumentation(Context context, Class newActivity) {
        Intent intent = new Intent(context, newActivity);
        context.startService(intent);
    }
}
