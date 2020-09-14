package edu.ptu.java.myapplication._00_surface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
//不会创建新的窗口来显示内容。内容流直接投放到View中，并且可以和其它普通View一样进行移动，旋转，缩放，动画等变化。
// TextureView必须在硬件加速的窗口中使用
public class _02_TextureViewActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextureView view = new TextureView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        FrameLayout frameLayout = new FrameLayout(this);
        Button button = new Button(this);
        button.setBackgroundColor(Color.RED);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        frameLayout.addView(view);
        frameLayout.addView(button);
        setContentView(frameLayout);
        view.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {


            @Override
            public void onSurfaceTextureAvailable(final SurfaceTexture surface, int width, int height) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                                        while(true){
                    Canvas canvas = view.lockCanvas();
                    Paint paint = new Paint();
                    paint.setStrokeWidth(10);
                    paint.setColor(0xff00fff3);
                    paint.setColor(0xff00ff00);
                    canvas.drawRect(new RectF(12,12,1010,1010), paint);
                    view.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    }
                }).start();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }

        });
    }
}
