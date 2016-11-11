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

public class FuckProgressBar extends Drawable implements Animatable{

    private Paint mPaint;
    private ValueAnimator valueAnimator;
    protected RectF mBound = new RectF();

    private Path mBgPath;
    private PathMeasure mMeasure;
    private float mAllLength;

    private float mProgress;
    private Context mContext;

    private final static long mDefaultDuration = 3000;

    private ValueAnimator.AnimatorUpdateListener mValueAnimatorListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            computerProgress((float)animation.getAnimatedValue());
            invalidateSelf();
        }
    };

    public FuckProgressBar(Context context){
        initialize(context);
    }

    private void initialize(Context context) {
        mContext = context;
        setAnimator();

    }

    @Override
    protected void onBoundsChange(Rect bounds) {

        super.onBoundsChange(bounds);
        mBound.set(bounds);
        initPaint();
        initPath();
    }

    private void setAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.addUpdateListener(mValueAnimatorListener);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(mDefaultDuration);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.CYAN);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DisplayUtils.dip2px(mContext,mBound.bottom - mBound.top));
    }

    private void computerProgress(float animatedValue) {
        mProgress = animatedValue;
    }

    private void initPath(){
        mBgPath = new Path();
//        mBgPath.moveTo(0,(mBound.bottom - mBound.top)/2);
        mBgPath.lineTo(mBound.right,(mBound.bottom - mBound.top)/2);

        mMeasure = new PathMeasure();
        mMeasure.setPath(mBgPath,false);
        mAllLength =  mMeasure.getLength();
    }
    @Override
    public void draw(Canvas canvas) {


        Path progressDst = new Path();

        mMeasure.getSegment(0,mAllLength * mProgress,progressDst,true);

        mPaint.setColor(Color.CYAN);
        canvas.drawPath(mBgPath,mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawPath(progressDst,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        valueAnimator.start();
    }

    @Override
    public void stop() {
        valueAnimator.end();
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
