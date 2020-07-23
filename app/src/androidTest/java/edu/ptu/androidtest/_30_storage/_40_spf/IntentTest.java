package edu.ptu.androidtest._30_storage._40_spf;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest._60_ui._50_component._00_LifeCyclerTest;
import edu.ptu.androidtest.android.act.LauchmodeStandardUIActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class IntentTest {
    @Test
    public void testIntent() {
//        Intent intent = new Intent("");

        Context context = getInstrumentation().getTargetContext();
//        _00_LifeCyclerTest.startActivityFromInstrumentation(context, LauchmodeStandardUIActivity.class);


        Instrumentation.ActivityMonitor monitor;
        Intent intent = new Intent(context, LauchmodeStandardUIActivity.class);
        intent.putExtra("a", new int[1020 * 1024 ]);//  android.os.TransactionTooLargeException
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        monitor = getInstrumentation().addMonitor(LauchmodeStandardUIActivity.class.getName(), null, false);
        Activity newActivityObj = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        Assert.assertNotNull("newActivity is Null", newActivityObj);
        getInstrumentation().removeMonitor(monitor);

    }
}
