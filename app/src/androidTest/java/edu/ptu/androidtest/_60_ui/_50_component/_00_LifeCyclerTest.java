package edu.ptu.androidtest._60_ui._50_component;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest.LifeActivity;
import edu.ptu.androidtest.android.act.LauchmodeStandardUIActivity;
import edu.ptu.androidtest.android.LifeTestActivity;
import edu.ptu.androidtest.android.LifeTestSecondActivity;
import edu.ptu.androidtest.android.SingleInstanceActivity;
import edu.ptu.androidtest.android.SingleTaskActivity;
import edu.ptu.androidtest.android.SingleTopActivity;
import edu.ptu.androidtest.android.StandardActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class _00_LifeCyclerTest {

    //D/LifecycleMonitor: Lifecycle
    //旋转屏幕
    @Test
    public void testActivityLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();

//        LifeTestActivity lifeActivity1 = (LifeTestActivity) startActivity(targetContext, LifeTestActivity.class);
//        LifeActivity newActivity = (LifeActivity) startActivity(lifeActivity1, LifeActivity.class);

        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, LifeTestActivity.class);
        Activity newActivity2 = startActivityFromApp(lifeActivity1, LifeActivity.class);
        Activity newActivity3 = startActivityFromApp(newActivity2, LifeTestSecondActivity.class);
        newActivity3.finish();
        newActivity2.finish();
        lifeActivity1.finish();
    }

    //日志过滤：LifecycleMonitor
    //adb shell dumpsys activity activities | sed -En -e '/Running activities/,/Run #0/p'
    @Test
    public void testLaucherModeStandardLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, StandardActivity.class);
        Activity newActivity2 = startActivityFromApp(lifeActivity1, StandardActivity.class);
        Activity newActivity3 = startActivityFromApp(newActivity2, StandardActivity.class);
        newActivity3.finish();
        newActivity2.finish();
        lifeActivity1.finish();
    }
    @Test
    public void testLaucherModeSingleTopLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, SingleTopActivity.class);
        Activity newActivity2 = startActivityFromApp(lifeActivity1, SingleTopActivity.class);
        Activity newActivity3 = startActivityFromApp(newActivity2, SingleTopActivity.class);
        newActivity3.finish();
        newActivity2.finish();
        lifeActivity1.finish();
    }

    @Test
    public void testLaucherModeSingleTaskLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, SingleTaskActivity.class);
        Activity newActivity2 = startActivityFromApp(lifeActivity1, SingleTaskActivity.class);
        Activity newActivity3 = startActivityFromApp(newActivity2, SingleTaskActivity.class);
        newActivity3.finish();
        newActivity2.finish();
        lifeActivity1.finish();
    }
    @Test
    public void testLaucherModeSingleInstanceLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, SingleInstanceActivity.class);
        Activity newActivity2 = startActivityFromApp(lifeActivity1, SingleInstanceActivity.class);
        Activity newActivity3 = startActivityFromApp(newActivity2, SingleInstanceActivity.class);
        newActivity3.finish();
        newActivity2.finish();
        lifeActivity1.finish();
    }

    @Test
    public void testLaucherModeMixLifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity newActivity1 = startActivityFromInstrumentation(targetContext, StandardActivity.class);
        Activity newActivity2 = startActivityFromApp(newActivity1, SingleTopActivity.class);
        Activity newActivity3 = startActivityFromApp(newActivity2, SingleTaskActivity.class);
        Activity newActivity4 = startActivityFromApp(newActivity3, SingleInstanceActivity.class);

//       adb shell am start -n edu.ptu.test/edu.ptu.androidtest.android.StandardActivity
//       adb shell am start -n edu.ptu.test/edu.ptu.androidtest.android.SingleTopActivity
//       adb shell am start -n edu.ptu.test/edu.ptu.androidtest.android.SingleTaskActivity
//       adb shell am start -n edu.ptu.test/edu.ptu.androidtest.android.SingleInstanceActivity
//       adb shell am stop -n edu.ptu.test/edu.ptu.androidtest.android.StandardActivity
//       adb shell am start -n edu.ptu.test/edu.ptu.androidtest.android.SingleTopActivity
//       adb shell am start -n edu.ptu.test/edu.ptu.androidtest.android.SingleTaskActivity
//       adb shell am stop -n edu.ptu.test/edu.ptu.androidtest.android.SingleInstanceActivity
        newActivity4.finish();
        newActivity3.finish();
        newActivity2.finish();
        newActivity1.finish();
    }
    @Test
    public void testLaucherModeMix2LifeCycle() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, LauchmodeStandardUIActivity.class);
        lifeActivity1.finish();

    }
    public   static Activity  startActivityFromInstrumentation(Context context, Class newActivity) {
        Instrumentation.ActivityMonitor monitor;
        Intent intent = new Intent(context, newActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        monitor = getInstrumentation().addMonitor(newActivity.getName(), null, false);
        Activity newActivityObj = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        Assert.assertNotNull("newActivity is Null", newActivityObj);
        getInstrumentation().removeMonitor(monitor);
        return newActivityObj;
    }

    public static Activity startActivityFromApp(Activity context, Class newActivity) {
        Instrumentation.ActivityMonitor monitor;
        Intent intent = new Intent(context, newActivity);
        context.startActivity(intent);
        monitor = getInstrumentation().addMonitor(newActivity.getName(), null, false);
        Activity newActivityObj = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        Assert.assertNotNull("newActivity is Null", newActivityObj);
        getInstrumentation().removeMonitor(monitor);
        return newActivityObj;
    }
    private <T extends Activity> Activity startActivitySync(Activity context, Class newActivity) {
        Intent intent = new Intent(context, newActivity);
        Activity activity = getInstrumentation().startActivitySync(intent);
        Assert.assertNotNull("newActivity is Null", activity);
        return activity;
    }
    private <T extends Activity> void startActivity(Activity context, Class newActivity) {
        Intent intent = new Intent(context, newActivity);
        context.startActivity(intent);
    }
}
