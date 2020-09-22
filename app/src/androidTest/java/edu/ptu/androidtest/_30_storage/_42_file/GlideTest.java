package edu.ptu.androidtest._30_storage._42_file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.platform.app.InstrumentationRegistry;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

import org.junit.Test;

import edu.ptu.androidtest._30_storage._50_network.OkhttpTest;

import static android.graphics.Bitmap.Config.ALPHA_8;
import static android.graphics.Bitmap.Config.RGB_565;

public class GlideTest {
    @Test
    public void testLoad(){
          Glide.with(InstrumentationRegistry.getInstrumentation().getTargetContext())
                  .load("https://pic1.zhimg.com/v2-8fccd08a7b91e8d00e0680a92792e424_r.jpg")
                  .centerCrop()
                  .into(new SimpleTarget<Drawable>() {
                      @Override
                      public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                      }
                  });
        Bitmap bitmap = Bitmap.createBitmap(200, 400, RGB_565);
    }
}
