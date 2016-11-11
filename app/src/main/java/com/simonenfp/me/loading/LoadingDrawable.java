package com.simonenfp.me.loading;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import com.orhanobut.logger.Logger;

/**
 * Created by simonenfp on 2016/11/10.
 */

public abstract class LoadingDrawable extends Drawable implements Animatable{


    protected RectF mBound = new RectF();

    private final static long mDefaultDuration = 3000;

    public LoadingDrawable(Context context){
        setAnimator();
    }

    protected ValueAnimator valueAnimator;

    private ValueAnimator.AnimatorUpdateListener mValueAnimatorListener = new ValueAnimator.AnimatorUpdateListener(){
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            computerProgress((float)animation.getAnimatedValue());
            invalidateSelf();
        }
    };

    protected void setAnimatorListener(Animator.AnimatorListener animatorListener){
        valueAnimator.addListener(animatorListener);
    }

    protected abstract void computerProgress(float progress);

    @Override
    public void start() {
        valueAnimator.start();
    }

    protected void setAnimator(){
        valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.addUpdateListener(mValueAnimatorListener);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(mDefaultDuration);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    public void stop() {

        valueAnimator.removeAllUpdateListeners();
        valueAnimator.setRepeatCount(0);
        valueAnimator.setDuration(0);
        valueAnimator.end();
    }

    @Override
    public boolean isRunning() {
        return valueAnimator.isRunning();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mBound.set(bounds);
        initialize();

    }

    protected abstract void initialize();

    @Override
    public int getOpacity() {
        //not sure,so be safe
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }
}
