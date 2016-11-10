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

public class SearchLoadingDrawable extends LoadingDrawable{

    private Context mContext;

    private Paint mPaint = new Paint();

    private Path path_search;
    private Path path_circle;

    private PathMeasure mPathMeasure;

    private float mAnimatedValue;

    private static final float DEFAULT_STROKE_WIDTH = 4.0f;

    public SearchLoadingDrawable(Context context){
        super(context);
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
        mPaint.setStrokeWidth(DisplayUtils.dip2px(mContext,DEFAULT_STROKE_WIDTH));
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

//        Logger.d("pos[0]:"+pos[0]+" pos[1]:"+pos[1]+" tan[0]:"+tan[0]+" tan[1]:"+tan[1]);
//        Logger.d("切线角度:"+Math.atan2(tan[1], tan[0])*180.0/Math.PI);
        path_search.lineTo(pos[0],pos[1]);


    }

    @Override
    protected void setAnimator() {
        super.setAnimator();
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
    }

    @Override
    protected void computerProgress(float progress) {
        mAnimatedValue = progress;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.translate(mBound.centerX(),mBound.centerY());

//        canvas.drawPath(path_search,mPaint);
//        canvas.drawPath(path_circle,mPaint);


        mPathMeasure.setPath(path_search,false);

        float allLength = mPathMeasure.getLength();

        Path dst = new Path();

        Logger.d("start:"+ allLength * mAnimatedValue + " end:"+allLength);

        mPathMeasure.getSegment(allLength * mAnimatedValue,allLength,dst,true);

        canvas.drawPath(dst,mPaint);


    }



}
