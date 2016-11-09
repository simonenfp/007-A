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
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.utils.DisplayUtils;

import java.util.Random;

/**
 * Created by simonenfp on 2016/11/3.
 */

public class LoadingDrawable extends Drawable implements Animatable{

    private Context mContext;

    private Paint mPaint = new Paint();

    private RectF mBound = new RectF();

    private Path path_search;
    private Path path_circle;

    private PathMeasure mPathMeasure;

    private static final int[] mColors = new int[]{
            Color.RED, Color.GREEN, Color.BLUE
    };


    public LoadingDrawable(Context context){
        mContext = context;
        initialize();
    }

    public void initialize(){
        initPaint();
        initPath();
    }

    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(DisplayUtils.dip2px(mContext,5.0f));
        mPaint.setStyle(Paint.Style.STROKE);
    }

    private void initPath() {
        path_search = new Path();
        path_circle = new Path();

        RectF searchRectF = new RectF(-50,-50,50,50);//放大镜圆环
        path_search.addArc(searchRectF,45,359.9f);

        RectF circleRectF = new RectF(-100,-100,100,100);//外部圆环
        path_circle.addArc(circleRectF,45,-359.9f);

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(path_circle,false);
        float[] pos = new float[2];
        float[] tan = new float[2];
        mPathMeasure.getPosTan(0,pos,tan);

        Logger.d("pos[0]:"+pos[0]+" pos[1]:"+pos[1]+" tan[0]:"+tan[0]+" tan[1]:"+tan[1]);
        Logger.d("切线角度:"+Math.atan2(tan[1], tan[0])*180.0/Math.PI);
        path_search.lineTo(pos[0],pos[1]);


    }


    private float mAnimatedValue;


    @Override
    public void draw(Canvas canvas) {

        canvas.drawRect(mBound,mPaint);

        canvas.translate(mBound.centerX(),mBound.centerY());

        canvas.drawPath(path_search,mPaint);
        canvas.drawPath(path_circle,mPaint);


        mPathMeasure.setPath(path_search,false);

        float allLength = mPathMeasure.getLength();

        Path dst = new Path();

//        mPathMeasure.getSegment(0,)


    }
    private ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f,0.0f);

    private ValueAnimator.AnimatorUpdateListener mValueAnimatorListener = new ValueAnimator.AnimatorUpdateListener(){
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mAnimatedValue = (float)animation.getAnimatedValue();
            invalidateSelf();
        }
    };

    @Override
    public void start() {
        valueAnimator.addUpdateListener(mValueAnimatorListener);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
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
        Logger.d("left:"+mBound.left + " top:" + mBound.top + " right:" + mBound.right + " bottom:" + mBound.bottom);

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
