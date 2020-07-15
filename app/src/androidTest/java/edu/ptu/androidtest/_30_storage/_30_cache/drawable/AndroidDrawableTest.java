package edu.ptu.androidtest._30_storage._30_cache.drawable;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.IdRes;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import edu.ptu.androidtest.R;

public class AndroidDrawableTest {
    /**
     * <pre>
     *           +-------------------------------------+
     *          |  dpi    Resolution          density |
     *          +-------------------------------------+
     *    ldpi  |  120                             .75|
     *          +-------------------------------------+
     *    mdpi  |  160                            1   |
     *          +-------------------------------------+
     *    hdpi  |  240      480*800               1.5 |
     *          +-------------------------------------+
     *   xhdpi  |  320      800*1280              2   |
     *          +-------------------------------------+
     *  xxhdpi  |  480     1080*1920              3   |
     *          +-------------------------------------+
     * xxxhdpi  |  640     3840*2160              4   |
     *          +-------------------------------------+
     *
     * </pre>
     */
    @Test
    public void testDpi(){
        System.out.println(InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDisplayMetrics().density);
        System.out.println(InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDisplayMetrics().densityDpi);
    }
    public static void printDrawableinfo(Drawable drawable) {
        String info ="{ " +
                "\t"+"width:"+ drawable.getIntrinsicWidth() + ",height:" + drawable.getIntrinsicHeight()+", " +
                "getOpacity :"+(drawable.getOpacity()== PixelFormat.OPAQUE)   +
                "\t"+"}," +
                "\t"+"{ " +
                "densityDpi:" +InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDisplayMetrics().densityDpi+","+
                "\t"+"density:" +InstrumentationRegistry.getInstrumentation().getTargetContext().getResources().getDisplayMetrics().density+
                "}" ;
        System.out.println(info);
    }
    public static Drawable getNoneDrawable_4x1024x1024(){
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        float density = targetContext.getResources().getDisplayMetrics().density;
        if (density==1.5){
            return targetContext.getResources().getDrawable(R.drawable._1024x1024_linear_hdpi);
        }else if (density==2){
            return targetContext.getResources().getDrawable(R.drawable._1024x1024_linear_xhdpi);
        }else if (density==3){
            return targetContext.getResources().getDrawable(R.drawable._1024x1024_linear_xxhdpi);
        }else {//mdpi
            return targetContext.getResources().getDrawable(R.drawable._1024x1024_linear);
        }
    }
    public static long ARGB_8888_1M =4*1024*1024;
    public static @IdRes int getNoneDrawableRid_4x1024x1024(){
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        float density = targetContext.getResources().getDisplayMetrics().density;
        if (density==1.5){
            return  (R.drawable._1024x1024_linear_hdpi);
        }else if (density==2){
            return  (R.drawable._1024x1024_linear_xhdpi);
        }else if (density==3){
            return  (R.drawable._1024x1024_linear_xxhdpi);
        }else {//mdpi
            return  (R.drawable._1024x1024_linear);
        }
    }
}
