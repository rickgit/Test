package edu.ptu.androidtest._40_throughput;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Looper;
import android.util.ArrayMap;

import androidx.test.runner.AndroidJUnitRunner;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class ActivityThreadTest {
    @Test
    public void test() throws Exception {//主线程 位于ActivityThread#main；三个全局常量 ActivityThread,mainHandler,packageManager
        Class<?> aClass = Class.forName("android.app.ActivityThread");
        Field declaredField = aClass.getDeclaredField("sCurrentActivityThread");
        declaredField.setAccessible(true);
        Object activityThread = declaredField.get(aClass);////全局变量，activityThread是主线程创建的对象

        Field handlerF = aClass.getDeclaredField("sMainThreadHandler");
        handlerF.setAccessible(true);
        Object sMainThreadHandler = handlerF.get(aClass);//全局变量


        Field sPM = aClass.getDeclaredField("sPackageManager");
        sPM.setAccessible(true);
        Object sPackageManager = sPM.get(aClass);//全局变量

        Field mLooper = sMainThreadHandler.getClass().getSuperclass().getDeclaredField("mLooper");
        mLooper.setAccessible(true);
        Looper mainLooper = (Looper) mLooper.get(sMainThreadHandler);

        Field mActivities = activityThread.getClass().getDeclaredField("mActivities");
        mActivities.setAccessible(true);
        ArrayMap<Object,Object> activities = (ArrayMap<Object, Object>) mActivities.get(activityThread);
        Field mInitialApplication = activityThread.getClass().getDeclaredField("mInitialApplication");
        mInitialApplication.setAccessible(true);
        Application initialApplication = (Application) mInitialApplication.get(activityThread);

        Field mInstrumentation = activityThread.getClass().getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThread);
        Assert.assertEquals(instrumentation.getClass(), AndroidJUnitRunner.class);

        Field mAppContext = instrumentation.getClass().getDeclaredField("mAppContext");
        mAppContext.setAccessible(true);
        Context appContext = (Context) mAppContext.get(instrumentation);
        Assert.assertEquals(appContext,instrumentation.getTargetContext());
    }
}
