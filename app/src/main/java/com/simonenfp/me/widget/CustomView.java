package com.simonenfp.me.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.simonenfp.me.R;

/**
 * Created by simonenfp on 2016/8/18.
 */
public class CustomView extends View {
    Paint mPaint;
    Context mContext;
    public CustomView(Context context) {
        super(context);
        initCustomView(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomView(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomView(context);
    }

    private void initCustomView(Context context) {
        mPaint = new Paint();
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        设置画布颜色
        canvas.drawColor(Color.argb(0x55,0x0,0x0,0x0));
        //绘制灰色矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0,0,800,800,mPaint);
//        Copy the fields from src into this paint. This is equivalent to calling get() on all of the src fields, and calling the corresponding set() methods on this.
        mPaint.reset();

//        绘制直线
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(20,20,100,100,mPaint);
        mPaint.reset();

//        绘制带边框的矩形
        mPaint.setStrokeWidth(10);
        mPaint.setARGB(255,255,64,129);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(120,20,220,120);
        canvas.drawRect(rectF,mPaint);
        mPaint.reset();

//        绘制实心圆
//        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GREEN);
//        Helper for setFlags(),setting or clearing the ANTI_ALIAS_FLAG bit AntiAliasing smooths out the edges of what is being drawn,
//        but is has no impact on the interior of the shape. See setDither() and setFilterBitmap() to affect how colors are treated.
//        抗锯齿平滑
        mPaint.setAntiAlias(true);
        canvas.drawCircle(300,70,50,mPaint);
        mPaint.reset();

//        绘制椭圆
        mPaint.setColor(Color.YELLOW);
        RectF rectF2=new RectF(380,20, 580, 120);
        canvas.drawOval(rectF2, mPaint);
        mPaint.reset();

//        绘制文字
        mPaint.setColor(Color.rgb(0x0,0x0,0x0));
        mPaint.setTextSize(60);
        mPaint.setUnderlineText(true);
        canvas.drawText("Android", 600, 100, mPaint);
        mPaint.reset();

//        把文字Android平移
        canvas.translate(-300,100);
        mPaint.setTextSize(60);
        mPaint.setUnderlineText(true);
        mPaint.setColor(Color.rgb(0x3F,0x51,0xB5));
        canvas.drawText("Android",600,100,mPaint);
        mPaint.reset();
//        把文字Android旋转
        canvas.rotate(15);
        mPaint.setTextSize(60);
        mPaint.setUnderlineText(true);
        mPaint.setColor(Color.rgb(0x5f,0x2c,0xe9));
        canvas.drawText("Android",600,100,mPaint);
        mPaint.reset();

        canvas.translate(300,-100);
        canvas.rotate(-25);
//        剪裁一部分  剪裁后再绘制只会在剪裁的部分显示，坐标系不变
//        所以需要使用canvas.save()和canvas.restore()将新图层合并到原图层
        canvas.save();
        canvas.clipRect(new Rect(100,500,600,800));
        canvas.drawColor(Color.argb(0x33,0x5f,0x2c,0xe9));
        mPaint.setTextSize(60);
        mPaint.setColor(Color.rgb(0xff,0xff,0xff));
        canvas.drawText("Android 剪裁",80,580,mPaint);
        mPaint.reset();
        canvas.restore();

        mPaint.setTextSize(60);
        mPaint.setColor(Color.rgb(0xff,0xff,0xff));
        canvas.drawText("裁剪后在裁剪外部绘制文字",0,250,mPaint);
        mPaint.reset();
        canvas.rotate(10);


        mPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.beauty1);

//        在调用set，pre，post时可视为将这些方法插入到一个队列。
//        pre表示在队头插入一个方法
//        post表示在队尾插入一个方法
//        set表示清空队列
//        队列中只保留该set方法，其余的方法都会清除。
//        当执行了一次set后pre总是插入到set之前的队列的最前面；post总是插入到set之后的队列的最后面。
        Matrix matrix = new Matrix();
//        canvas.drawBitmap(bitmap,matrix,mPaint);
        matrix.setTranslate(600, 600);
        canvas.drawBitmap(bitmap,matrix,mPaint);
        matrix.preScale(0.5f, 0.5f);
        canvas.drawBitmap(bitmap, matrix, mPaint);
        mPaint.reset();

//        渲染
        mPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.beauty2);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        mPaint.setShader(bitmapShader);
//        canvas.drawRect(new RectF(0,600,500,1100),mPaint);
        RectF rectf1 = new RectF(0,600,500,1000);
        canvas.drawOval(rectf1,mPaint);
        mPaint.reset();

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.argb(0x33,0x5f,0x2c,0xe9));
        mPaint.setStrokeWidth(10);
        canvas.translate(20,1200);
        Path path = new Path();
        path.moveTo(0,0);
        for (int i = 1;i <= 30;i++){
            path.lineTo(i * 30 ,(float) Math.random() * 200);
        }
        canvas.drawPath(path,mPaint);
        canvas.translate(0,200);
        mPaint.setPathEffect(new CornerPathEffect(60));
        canvas.drawPath(path,mPaint);
        canvas.translate(0,200);
        mPaint.setPathEffect(new DashPathEffect(new float[] {15, 5}, 1));
        canvas.drawPath(path,mPaint);
    }
}
