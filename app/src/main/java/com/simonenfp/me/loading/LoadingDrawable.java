package com.simonenfp.me.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import com.simonenfp.me.utils.DisplayUtils;

import java.util.Random;

/**
 * Created by simonenfp on 2016/11/3.
 */

public class LoadingDrawable extends Drawable implements Animatable{

    private Context mContext;

    private Paint mPaint = new Paint();

    private RectF mBound = new RectF();

    private static final int[] mColors = new int[]{
            Color.RED, Color.GREEN, Color.BLUE
    };

    private Random mRandom = new Random();

    public LoadingDrawable(Context context){
        mContext = context;
        initialize();
    }

    public void initialize(){
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(DisplayUtils.dip2px(mContext,3.0f));
    }
    private ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);

    private ValueAnimator.AnimatorUpdateListener mValueAnimatorListener = new ValueAnimator.AnimatorUpdateListener(){
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            computer(animation.getAnimatedFraction());
            invalidateSelf();
        }
    };

    private AnimatorListenerAdapter mAnimatorListenerAdapter = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationRepeat(Animator animation) {
            super.onAnimationRepeat(animation);

        }
    };

    private void computer(float progress){

    }

    @Override
    public void start() {
        valueAnimator.addUpdateListener(mValueAnimatorListener);
//        valueAnimator.addListener(mAnimatorListenerAdapter);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
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

    }

    private PointF mStart = new PointF(0,0);
    private PointF mEnd = new PointF(0,0);
    private PointF mControl1 = new PointF(0,0);

    @Override
    public void draw(Canvas canvas) {

        mEnd.x = mEnd.x + 200;
        mEnd.y = mEnd.y + 0;
        mControl1.x = mControl1.x + 80;
        mControl1.y = mControl1.y + 200;


        canvas.drawPoint(mStart.x,mStart.y,mPaint);
        canvas.drawPoint(mEnd.x,mEnd.y,mPaint);
        canvas.drawPoint(mControl1.x,mControl1.y,mPaint);

        Path path = new Path();
        path.quadTo(mControl1.x,mControl1.y,mEnd.x,mEnd.y);
        canvas.drawPath(path,mPaint);

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        //not sure,so be safe
        return PixelFormat.TRANSLUCENT;
    }
}
