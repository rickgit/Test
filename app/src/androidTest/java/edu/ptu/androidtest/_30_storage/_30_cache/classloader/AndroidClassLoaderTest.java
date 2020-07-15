package edu.ptu.androidtest._30_storage._30_cache.classloader;

import android.content.Context;
import android.os.Looper;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import edu.ptu.javatest._80_storage._70_cache._01_jvm._00_classloader.ClassLoaderTest;

//@RunWith( AndroidJUnit4.class)
public class AndroidClassLoaderTest {

    @Test
    public void testLooper() {
//adb shell am instrument -w -r    -e debug false -e class 'edu.ptu.test.classloader.AndroidClassLoaderTest#testLooper' edu.ptu.test.test/androidx.test.runner.AndroidJUnitRunner
        try {
            Assert.assertEquals(Thread.currentThread().getClass(), Class.forName("android.app.Instrumentation$InstrumentationThread"));
        } catch (ClassNotFoundException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(Looper.getMainLooper() != Looper.myLooper());
    }

    @Test
    public void testClassLoader() {
//        new ClassLoaderTest().testClassLoader();
        try {

            Assert.assertEquals(ClassLoaderTest.class.getClassLoader().getClass(), Class.forName("dalvik.system.PathClassLoader"));
            Assert.assertEquals(ClassLoaderTest.class.getClassLoader().getParent().getClass(), Class.forName("java.lang.BootClassLoader"));
//            Assert.assertEquals(ClassLoaderTest.class.getClassLoader().getParent().getParent(), null);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testInstallClassLoader() {
//        new ClassLoaderTest().testClassLoader();
        try {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

            PathClassLoader classLoader = (PathClassLoader) ClassLoaderTest.class.getClassLoader();

            Assert.assertEquals(classLoader.getClass(), Class.forName("dalvik.system.PathClassLoader"));
            Assert.assertEquals(classLoader.getParent().getClass(), Class.forName("java.lang.BootClassLoader"));
            PathClassLoader path = (PathClassLoader) classLoader;
            Field pathList = BaseDexClassLoader.class.getDeclaredField("pathList");
            pathList.setAccessible(true);
            Object pathListValue = pathList.get(path);
            Field dexElements = pathListValue.getClass().getDeclaredField("dexElements");
            dexElements.setAccessible(true);
            Object dexElementsValue = dexElements.get(pathListValue);
            Class<?> componentType = dexElementsValue.getClass().getComponentType();
            //需要外部read/write权限
            DexClassLoader dexClassLoader = new DexClassLoader(targetContext.getExternalCacheDir().getPath()+"/fix_dex.jar",
                    targetContext.getCacheDir().getPath(), null, classLoader);
            Assert.assertNotNull(dexClassLoader);

            Object pathListNewValue = pathList.get(dexClassLoader);
            Object dexElementsNewValue = dexElements.get(pathListNewValue);
            int pathDexElemValueLength = Array.getLength(dexElementsValue);
            int dexElemNewLength = Array.getLength(dexElementsNewValue);
            Object createNewDexElemList = Array.newInstance(componentType, dexElemNewLength+pathDexElemValueLength);
            for (int i = 0; i < dexElemNewLength + pathDexElemValueLength; i++) {
                if (i<dexElemNewLength)
                    Array.set(createNewDexElemList,i,Array.get(dexElementsNewValue,i));
                else if (i<dexElemNewLength+pathDexElemValueLength){
                    Array.set(createNewDexElemList,i,Array.get(dexElementsValue,i-dexElemNewLength));
                }
            }
            dexElements.set(pathListValue,createNewDexElemList);
            //一旦类加载，就会参数缓存，不会更新
            Assert.assertEquals(classLoader.loadClass("edu.ptu.androidtest.FixBean").getDeclaredFields().length,0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDumpMemo() {
        try {
            Process su = Runtime.getRuntime().exec("su");
            OutputStream outputStream = su.getOutputStream();

            String command = "dumpsys meminfo " + InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName();
            outputStream.write(command.getBytes());
            System.out.println("command :" + command);
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(su.getInputStream()));
            String str = null;
            while ((str = bufferedInputStream.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
}
