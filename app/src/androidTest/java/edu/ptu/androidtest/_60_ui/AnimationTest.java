package edu.ptu.androidtest._60_ui;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import org.junit.Test;

public class AnimationTest {
    public static class ObjectAnim{
        Integer resultCode;
        //必须要有这个方法才能自动设置值


        public void setResultCode(Integer resultCode) {
            this.resultCode = resultCode;
        }
    }
    @Test
    public void testAnimation(){
        //android.animation.ValueAnimator#start(boolean)
        //android.util.AndroidRuntimeException: Animators may only be run on Looper threads
        ObjectAnim target = new ObjectAnim();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator.ofObject(target, "resultCode", new TypeEvaluator<Integer>() {
                    @Override
                    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                        return (int)(startValue+fraction*(endValue-startValue));
                    }


                }, 1, 2, 34, 34, 34, 34).setDuration(3000).start();
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
