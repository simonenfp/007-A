package com.simonenfp.me.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import rx.internal.util.unsafe.MpmcArrayQueue;

/**
 * Created by simonenfp on 2016/8/18.
 */
public class CustomView extends View {
    Paint mPaint;
    public CustomView(Context context) {
        super(context);
        initCustomView();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomView();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomView();
    }

    private void initCustomView() {
        mPaint = new Paint();
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
//        剪裁一部分  剪裁后再绘制只会在剪裁的部分显示，
//        所以需要使用canvas.save()和canvas.restore()将新图层合并到原图层
        canvas.save();
        canvas.clipRect(new Rect(100,500,600,800));
        canvas.drawColor(Color.argb(0x33,0x5f,0x2c,0xe9));
        mPaint.setTextSize(60);
        mPaint.setColor(Color.rgb(0xff,0xff,0xff));
        canvas.drawText("Android 剪裁部分",80,580,mPaint);
        mPaint.reset();
        canvas.restore();

        mPaint.setTextSize(60);
        mPaint.setColor(Color.rgb(0xff,0xff,0xff));
        canvas.drawText("裁剪后在裁剪外部绘制文字",0,250,mPaint);




    }
}
