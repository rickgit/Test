package edu.ptu.androidtest.android.surface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.SumPathEffect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import edu.ptu.androidtest.R;

public class SurfaceViewActivity extends FragmentActivity {//appcompatActiviyt：You need to use a Theme.AppCompat theme (or descendant) with this activity.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurfaceView view = new SurfaceView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(view);
        view.getHolder().addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                new Thread(new DrawTask(holder)).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    class DrawTask implements Runnable {
        private final SurfaceHolder holder;
        private final boolean drawing;

        public DrawTask(SurfaceHolder holder) {
            this.holder = holder;
            this.drawing = true;
        }

        @Override
        public void run() {
            while (drawing) {
                draw(holder);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void draw(SurfaceHolder holder) {
            Canvas canvas = holder.lockCanvas();
            try {
                //命令 绘制点，线，弧，圆形，四边形，路径等几何，文本图片多媒体类型，路径
                drawCommon(canvas);
                //canvas的裁剪
                //paint 抗锯齿
                //canvas 策略模式
                drawPathEffect(canvas);
                //canvas 备忘录
                canvas.save();canvas.restore();
                //canvas 白板/画布变换
//                canvas.translate();canvas.scale();canvas.rotate();
                //裁剪
                canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);

            } finally {
                if (canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }

        }

        /**
         *  CornerPathEffect 曲线路径 ；DiscretePathEffect离散路径效果,锈铁丝效果
         *   DashPathEffect  虚线化；PathDashPathEffect Path图形来填充；
         *  ComposePathEffect 组合效果，依次显示；SumPathEffect  叠加效果，重叠显示;
         * @param canvas
         */
        private void drawPathEffect(Canvas canvas) {
            Paint paint = new Paint();
            paint.setStrokeWidth(5);
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.STROKE);
            paint.setPathEffect(new CornerPathEffect(50));//策略模式
            paint.setPathEffect(new DiscretePathEffect(3.0f, 5.0f));//线段长，偏移量
            paint.setPathEffect(new DashPathEffect(new float[] { 20, 10, 5, 10 },0));
            Path p = new Path();
            p.addRect(0, 0, 8, 8, Path.Direction.CCW);
            paint.setPathEffect( new PathDashPathEffect(p, 12, 0,
                    PathDashPathEffect.Style.ROTATE));
            paint.setPathEffect( new ComposePathEffect(
                    new DiscretePathEffect(3.0f, 5.0f),
                    new DashPathEffect(new float[] { 20, 10, 5, 10 },0)));
            paint.setPathEffect( new SumPathEffect(
                    new DiscretePathEffect(3.0f, 5.0f),
                    new DashPathEffect(new float[] { 20, 10, 5, 10 },0)));

            Path path = new Path();
            path.moveTo(20, 300);
            path.lineTo(120, 200);
            path.lineTo(140, 100);
            path.lineTo(220, 200);
            path.lineTo(260, 200);

            path.lineTo(380, 600);
            path.lineTo(420, 200);
            path.lineTo(490, 240);
            path.lineTo(520, 200);
            canvas.drawPath(path, paint);
        }

        private void drawCommon(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);

            //绘制几何，线条，弧形圆形，四边形
            paint.setStrokeWidth(5);
            canvas.drawPoint(420, 420, paint);
            canvas.drawPoints(new float[]{410, 100, 510, 100, 610, 100, 710, 100, 810, 100, 910, 100}, paint);
            canvas.drawLine(0, 0, 100, 100, paint);
            canvas.drawLines(new float[]{480, 400, 580, 200, 680, 400, 780, 200, 880, 400, 980, 200}, paint);
            //use center false:圆弧；true：把圆心连接成扇形
            canvas.drawArc(new RectF(300, 100, 400, 600), 0, 90, false, paint);
            canvas.drawArc(new RectF(600, 100, 700, 600), 0, 90, true, paint);
            canvas.drawArc(new RectF(900, 100, 1000, 600), 0, 360, true, paint);
            canvas.drawCircle(400, 900, 220, paint);


            canvas.drawRect(100, 100, 500, 500, paint);
            canvas.drawRoundRect(new RectF(900, 600, 1000, 800), 50, 50, paint);

            Path path = new Path();
            path.moveTo(200, 910);
            path.lineTo(200, 920);
            path.arcTo(new RectF(200, 920, 20, 940), 0, 90, false);
            //在线效果 https://cubic-bezier.com/
            //弧形型贝塞尔曲线 https://xuanfengge.com/easeing/ceaser/
            int startx = 20, starty = 920;
            int endx = 300, endy = 1111;
            path.moveTo(startx, starty);//起点
            path.quadTo(startx, endy, endx, endy);// 插入点，终点
            //s型贝塞尔曲线
            startx = 800;
            starty = 920;
            endx = 900;
            endy = 1110;
            path.moveTo(startx, starty);//设置起点 //贝塞尔曲线，物理坐标
            path.cubicTo((endx + startx) / 2 + ((endx - startx) / 4), starty, (endx + startx) / 2 - ((endx - startx) / 4), endy, endx, endy);//两个控制点，一个终点

            startx = 400;
            starty = 920;
            int offx = 200;
            int offy = 222;
            path.moveTo(startx, starty);
            //贝塞尔曲线，三个顶点以（200,920）偏移量
            path.rCubicTo(offx / 2 + offx / 4, 0, offx / 2 - offx / 4, offy, offx, offy);
//                path.addxxx;扇形，椭圆，圆形，四边形，路径
//                path.close();//闭合曲线，闭合最后一次moveto和绘制的终点
//                path.reset();path.rewind();//reset相当于重置到new Path阶段，rewind会保留Path的数据结构
            //布尔运算（DIFFERENCE（差集），REVERSE_DIFFERENCE（差集），INTERSECT（交集），UNION（并集），XOR（异或）五种运算逻辑）
            //offset 平移；transform 矩阵变换
//                path.op(new Path(), Path.Op.DIFFERENCE);
            canvas.drawPath(path, paint);


            //绘制多媒体 文本，图片
            paint.setTextSize(72);
            canvas.drawText("你好", 10, 900, paint);
            canvas.drawBitmap(((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap()
                    , 10, 900, paint);

        }
    }


}
