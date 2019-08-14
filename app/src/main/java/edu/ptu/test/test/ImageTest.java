package edu.ptu.test.test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import edu.ptu.utils.utils.ClockUtils;

public class ImageTest {

    private static int time = 10;

    public static void testLoadImage(final Context context, final ViewGroup view){
        ArrayList<String> imglist = new ArrayList<>();
        imglist.add("https://gratisography.com/thumbnails/gratisography-scary-tracks-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-field-blue-sky-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-butterfly-flower-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-desert-landscape-sky-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-tall-tree-barren-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-small-waterfall-summer-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-lake-sunset-summer-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-ski-lift-summer-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-waves-crashing-rocks-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-black-white-trees-winter-thumbnail-small.jpg");
        imglist.add("https://gratisography.com/thumbnails/gratisography-mountain-peaks-thumbnail-small.jpg");

        final CountDownLatch countDownLatch = new CountDownLatch(time);
        for (int i = 0; i < time; i++) {
            ImageView view1 = new ImageView(context);
            view.addView(view1);
            ViewGroup.LayoutParams layoutParams = view1.getLayoutParams();
            layoutParams.width=40;
            layoutParams.height=40;
            view1.setLayoutParams(layoutParams);
            Glide.with(context).load(imglist.get(i)).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    countDownLatch.countDown();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    countDownLatch.countDown();
                    return false;
                }
            }).into(view1);

        }
        ClockUtils.getInstance().printDiffTime();
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClockUtils.getInstance().printDiffTime();//3核，10张图片，2574ms；8核，10张图片,40pxX40px，网络2090ms，磁盘72ms，内存16ms；
            }
        }).start();

    }
}
