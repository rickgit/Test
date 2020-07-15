package edu.ptu.androidtest._30_storage._30_cache.heap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import androidx.annotation.IdRes;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import edu.ptu.javatest._80_storage._70_cache._01_jvm._02_heap.HeapTest;
import edu.ptu.javatest._80_storage._70_cache._01_jvm._03_gc.GcTest;
import edu.ptu.androidtest.R;
import edu.ptu.androidtest._30_storage._30_cache.drawable.AndroidDrawableTest;

import static edu.ptu.androidtest._30_storage._30_cache.drawable.AndroidDrawableTest.ARGB_8888_1M;
import static edu.ptu.androidtest._30_storage._30_cache.drawable.AndroidDrawableTest.getNoneDrawable_4x1024x1024;

public class AndroidHeapTest {
    //top -d 1 | grep edu.ptu.test.heap.AndroidHeapTest
    //Explicit concurrent mark sweep GC freed
    @Test
    public void testHeap() {
        new HeapTest().testSurvivorSwap();
    }

    //获取heap大小限制
    //getprop|grep heapgrowthlimit
    // adb shell  dumpsys meminfo  edu.ptu.test|grep Dalvik Heap
    @Test
    public void testOutOfMemoryError() {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            byte[] b = GcTest.getKb(1014 * 10);
            objects.add(b);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static DecimalFormat df = new DecimalFormat(",###");

    public static void printMeminfo() {
        //应用内存：单位b
        StringBuilder appmeminfo = new StringBuilder("{ " +//"\n" +
                "\t" + "maxMemory:" + df.format(Runtime.getRuntime().maxMemory()) + ", " +//"\n" +
                "\t" + "totalMemory:" + df.format(Runtime.getRuntime().totalMemory()) + ", " +//"\n" +
                "\t" + "freeMemory:" + df.format(Runtime.getRuntime().freeMemory()) + ", " +//"\n" +
                "\t" + "useMemory:" + df.format((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) + ", " +//"\n" +
                "} ");
        //内存不够时候，{ 	getNativeHeapSize:4,718,592, 	getNativeHeapFreeSize:1,340,656, 	getNativeHeapAllocatedSize:3,377,936, }
        //内存不过，不一定相等
//        Assert.assertTrue(Debug.getNativeHeapSize(),Debug.getNativeHeapFreeSize() + Debug.getNativeHeapAllocatedSize());
        String nativie = "{ " +//"\n" +
                "\t" + "getNativeHeapSize:" + df.format(Debug.getNativeHeapSize()) + ", " +//"\n" +
                "\t" + "getNativeHeapFreeSize:" + df.format(Debug.getNativeHeapFreeSize()) + ", " +//"\n" +
                "\t" + "getNativeHeapAllocatedSize:" + df.format(Debug.getNativeHeapAllocatedSize()) + ", " +//"\n" +
                "}";
        System.out.println(appmeminfo.append(",").append(nativie).toString());
    }


    @Test
    public void testDrawableFromDpi() {
        printMeminfo();

        ArrayList<Object> objects = new ArrayList<>();
//        printDrawableMeminfo(objects,R.drawable._1024x1024_linear);
        printDrawableMeminfo(objects, R.drawable._1024x1024_linear_hdpi);

//        printDrawableMeminfo(objects,R.drawable._1024x1024_linear_xhdpi);
//        printDrawableMeminfo(objects,R.drawable._1024x1024_linear_xxhdpi);

        objects.clear();
        System.gc();
        printMeminfo();
    }

    @Test
    public void testImageFromDiffMethod() {
        printMeminfo();

        ArrayList<Object> objects = new ArrayList<>();
        int x1024_linear_hdpi = R.drawable._1024x1024_linear_hdpi;
        printDrawableMeminfo(objects, x1024_linear_hdpi);
        objects.add(BitmapFactory.decodeResource(InstrumentationRegistry.getInstrumentation().getTargetContext().getResources(), x1024_linear_hdpi));
        printMeminfo();
        objects.add(BitmapFactory.decodeStream(InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().openRawResource(x1024_linear_hdpi)));
        printMeminfo();

        objects.clear();
        System.gc();
        printMeminfo();
    }

    @Test
    public void testImageFromReuse() {
        printMeminfo();

        ArrayList<Object> objects = new ArrayList<>();
        int x1024_linear_hdpi = R.drawable._1024x1024_linear_hdpi;
        printDrawableMeminfo(objects, x1024_linear_hdpi);
        objects.add(BitmapFactory.decodeResource(InstrumentationRegistry.getInstrumentation().getTargetContext().getResources(), x1024_linear_hdpi));
        printMeminfo();
        objects.add(BitmapFactory.decodeStream(InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().openRawResource(x1024_linear_hdpi)));
        printMeminfo();

        objects.clear();
        System.gc();
        printMeminfo();
    }

    private void printDrawableMeminfo(ArrayList<Object> objects, @IdRes int r) {
        Drawable drawable = InstrumentationRegistry.getInstrumentation().getTargetContext().getDrawable(r);
        AndroidDrawableTest.printDrawableinfo(drawable);
        objects.add(drawable);
        printMeminfo();
    }

    /**
     * android 6 heap增长
     * java.lang.OutOfMemoryError: Failed to allocate a 4194316 byte allocation with 2685040 free bytes and 2MB until OOM
     * at dalvik.system.VMRuntime.newNonMovableArray(Native Method)
     * at android.graphics.Bitmap.nativeCreate(Native Method)
     * at android.graphics.Bitmap.createBitmap(Bitmap.java:831)
     * <p>
     * Android nativeheap增长
     * getNativeHeapAllocatedSize
     * java.lang.OutOfMemoryError
     * at android.graphics.Bitmap.nativeCreate(Native Method)
     * at android.graphics.Bitmap.createBitmap(Bitmap.java:1046)
     * at android.graphics.Bitmap.createBitmap(Bitmap.java:1000)
     * at android.graphics.Bitmap.createBitmap(Bitmap.java:950)
     * at android.graphics.Bitmap.createBitmap(Bitmap.java:911)
     * 理论：
     * Android3.0~Android7.1 vmheap
     * Android8.0+ nativeheap
     */
    @Test
    public void testImageMemory() {
        Bitmap bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
        Assert.assertTrue(bitmap.getByteCount() == ARGB_8888_1M);
        printMeminfo();
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        try {
            for (int i = 0; i < 100_0000; i++) {
                bitmaps.add(Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888));
                System.out.println("times: " + i);
                printMeminfo();
            }
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), OutOfMemoryError.class);
        }
    }

    @Test
    public void testDrawableMemory() {
        Drawable drawable = getNoneDrawable_4x1024x1024();
        Drawable drawable2 = getNoneDrawable_4x1024x1024();
        Assert.assertNotEquals(drawable, drawable2);
        printMeminfo();
        ArrayList<Drawable> bitmaps = new ArrayList<>();

        for (int i = 0; i < 100_0000; i++) {
            bitmaps.add(getNoneDrawable_4x1024x1024());
            System.out.println("times: " + i);
            printMeminfo();
        }

    }

    @Test
    public void testBitmapDrawableMemory() {
        printMeminfo();
        ArrayList<Drawable> bitmaps = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            new BitmapDrawable(Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888));
            System.out.println("times: " + i);
            printMeminfo();
        }
    }

    @Test
    public void testBitmapsMemory() {
        printMeminfo();
        ArrayList<Drawable> bitmaps = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
            System.out.println("times: " + i);
            printMeminfo();
        }
    }

    @Test
    public void testQualityCompress() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Drawable drawable = targetContext.getResources().getDrawable(R.drawable._1024x1024_linear_hdpi);
        try {
//            ((BitmapDrawable) drawable).getBitmap().compress(Bitmap.CompressFormat.JPEG, 30,
//                    new BufferedOutputStream(new FileOutputStream(targetContext.getExternalCacheDir().getAbsolutePath())));

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30,
                    new BufferedOutputStream(out));

            Bitmap result = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));//size 还是4*1024*1024内存占用不变，文件大小压缩
            Assert.assertTrue(result.getByteCount() < bitmap.getByteCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSizeCompressUseSample() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Drawable drawable = targetContext.getResources().getDrawable(R.drawable._1024x1024_linear_hdpi);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            //采样率
            options.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeResource(targetContext.getResources(), AndroidDrawableTest.getNoneDrawableRid_4x1024x1024(), options);

            Assert.assertEquals(bitmap.getByteCount(), (ARGB_8888_1M / options.inSampleSize / options.inSampleSize));//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSizeCompress() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Drawable drawable = AndroidDrawableTest.getNoneDrawable_4x1024x1024();//当前分辨率，4m
        try {
            Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
            Assert.assertEquals(bmp.getByteCount(), bmp.getRowBytes() * bmp.getHeight());
            Assert.assertEquals(bmp.getByteCount(), ARGB_8888_1M);

            int radio_param = 2;
            Bitmap result = Bitmap.createBitmap(bmp.getWidth() / radio_param, bmp.getHeight() / radio_param, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);  //创建画布（工作区域为bitmap的大小）
            canvas.drawBitmap(bmp, null, new Rect(0, 0, bmp.getWidth() / radio_param, bmp.getHeight() / radio_param), null);  //将源bitmap 画到 canvas上.此时的relust 就是源bitmap缩放的值。
            Assert.assertTrue(result.getByteCount() < bmp.getByteCount());//内存大小压缩
            Assert.assertEquals(result.getByteCount(), (ARGB_8888_1M / radio_param / radio_param));
            // 把压缩后的数据存放到baos中
            result.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        } catch (Exception e) {
        }
    }

}
