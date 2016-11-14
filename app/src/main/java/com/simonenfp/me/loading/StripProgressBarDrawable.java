package com.simonenfp.me.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.utils.DisplayUtils;

/**
 * Created by simonenfp on 2016/11/11.
 */

public class StripProgressBarDrawable extends LoadingDrawable{

    private Paint mPaint;
    private float mPaintWidth;

    private Path mBgPath;
    private float mWidth;
    private float mHeight;

    private float mProgress;
    private Context mContext;

    private static final float DEFAULT_PAINT_WIDTH = 2.0f;


    public StripProgressBarDrawable(Context context){
        super(context);
        mContext = context;
        mPaintWidth = DisplayUtils.dip2px(context,DEFAULT_PAINT_WIDTH);
    }

    @Override
    protected void initialize() {
        initPaint();
        initPath();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mPaintWidth);
    }

    private void initPath(){

        mWidth = mBound.width();
        mHeight = mBound.height();

        mBgPath = new Path();
        mBgPath.addRect(mBound, Path.Direction.CW);
    }

    @Override
    protected void computerProgress(float animatedValue) {
        mProgress = animatedValue;
    }
    @Override
    public void draw(Canvas canvas) {

        Path progressDst = new Path();



        RectF rectF = new RectF(mWidth * mProgress + mHeight/2,mBound.top,mBound.right,mBound.bottom);
        RectF rectF1 = new RectF(mWidth * mProgress,mBound.top,mWidth * mProgress + mHeight,mBound.bottom);
        progressDst.addArc(rectF1,90,270);

        progressDst.addRect(rectF, Path.Direction.CW);


        mPaint.setColor(Color.CYAN);
        canvas.drawPath(mBgPath,mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawPath(progressDst,mPaint);
    }

}
