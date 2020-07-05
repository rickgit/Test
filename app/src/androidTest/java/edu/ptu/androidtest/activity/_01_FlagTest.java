package edu.ptu.androidtest.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest.LifeActivity;
import edu.ptu.androidtest.android.LifeTestActivity;
import edu.ptu.androidtest.android.LifeTestSecondActivity;
import edu.ptu.androidtest.android.SingleInstanceActivity;
import edu.ptu.androidtest.android.SingleTaskActivity;
import edu.ptu.androidtest.android.SingleTopActivity;
import edu.ptu.androidtest.android.StandardActivity;

import static android.content.Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static edu.ptu.androidtest.activity._00_LifeCyclerTest.startActivityFromApp;
import static edu.ptu.androidtest.activity._00_LifeCyclerTest.startActivityFromInstrumentation;

public class _01_FlagTest {
    @Test
    public void testFLAG_ACTIVITY_CLEAR_TOP(){
        Context targetContext = getInstrumentation().getTargetContext();

//        LifeTestActivity lifeActivity1 = (LifeTestActivity) startActivity(targetContext, LifeTestActivity.class);
//        LifeActivity newActivity = (LifeActivity) startActivity(lifeActivity1, LifeActivity.class);

        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, StandardActivity.class);
        Activity lifeActivity2 = startActivityFromApp(lifeActivity1, SingleTopActivity.class);
        Activity lifeActivity3 = startActivityFromApp(lifeActivity2, SingleTaskActivity.class);
        Activity lifeActivity4 = startActivityFromApp(lifeActivity3, SingleInstanceActivity.class);
        lifeActivity1 = startActivityWithFlag(lifeActivity4, StandardActivity.class,FLAG_ACTIVITY_CLEAR_TOP);
          Assert.assertTrue(true);
    } @Test
    public void testFLAG_FLAG_ACTIVITY_BROUGHT_TO_FRONT(){
        Context targetContext = getInstrumentation().getTargetContext();

//        LifeTestActivity lifeActivity1 = (LifeTestActivity) startActivity(targetContext, LifeTestActivity.class);
//        LifeActivity newActivity = (LifeActivity) startActivity(lifeActivity1, LifeActivity.class);

        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, StandardActivity.class);
        Activity lifeActivity2 = startActivityFromApp(lifeActivity1, SingleTopActivity.class);
        Activity lifeActivity3 = startActivityFromApp(lifeActivity2, SingleTaskActivity.class);
        Activity lifeActivity4 = startActivityFromApp(lifeActivity3, SingleInstanceActivity.class);
        lifeActivity1 = startActivityWithFlag(lifeActivity4, StandardActivity.class,FLAG_ACTIVITY_BROUGHT_TO_FRONT);
          Assert.assertTrue(true);
    }
    private   Activity startActivityWithFlag(Activity context, Class newActivity,int flag) {
        Instrumentation.ActivityMonitor monitor;
        Intent intent = new Intent(context, newActivity);
        intent.setFlags(flag);
        context.startActivity(intent);
        monitor = getInstrumentation().addMonitor(newActivity.getName(), null, false);
        Activity newActivityObj = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        Assert.assertNotNull("newActivity is Null", newActivityObj);
        getInstrumentation().removeMonitor(monitor);
        return newActivityObj;
    }
}
