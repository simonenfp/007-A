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
    private float mAllLength;

    private float mProgress;
    private Context mContext;

    public StripProgressBarDrawable(Context context){
        super(context);
        mContext = context;
    }

    @Override
    protected void initialize() {
        initPaint();
        initPath();
    }

    private void initPaint() {
        mPaintWidth = mBound.bottom - mBound.top;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPaintWidth);
    }

    private void initPath(){

        mBgPath = new Path();
        mBgPath.lineTo(-mBound.right,mBound.top);
        mAllLength =  mBound.right - mBound.left;
    }

    @Override
    protected void computerProgress(float animatedValue) {
        mProgress = animatedValue;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.translate(mAllLength,mPaintWidth/2.0f);

        Path progressDst = new Path();

        progressDst.lineTo(-mBound.right * (1 - mProgress),mBound.top);


        mPaint.setColor(Color.CYAN);
        canvas.drawPath(mBgPath,mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawPath(progressDst,mPaint);
    }

}
