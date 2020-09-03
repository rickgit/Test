package edu.ptu.androidtest._60_ui._50_component;

import android.app.Activity;
import android.content.Context;

import org.junit.Test;

import edu.ptu.androidtest.FragmentLifeActivity;
import edu.ptu.androidtest.LifeActivity;
import edu.ptu.androidtest.android.LifeTestActivity;
import edu.ptu.androidtest.android.LifeTestSecondActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static edu.ptu.androidtest._60_ui._50_component._00_LifeCyclerTest.startActivityFromInstrumentation;

public class _01_LifeCyclerRecreateTest {

    @Test
    public void testBackHome() {
        Context targetContext = getInstrumentation().getTargetContext();
        Activity lifeActivity1 = startActivityFromInstrumentation(targetContext, LifeActivity.class);
        System.out.println();
    }
}
