package com.simonenfp.me.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.utils.DisplayUtils;

/**
 * Created by simonenfp on 2016/11/10.
 */

public class MaterialLoadingDrawable extends LoadingDrawable {

    private Context mContext;
    private Paint mPaint;

    private float mProgress;

    private static final float DEFAULT_STROKE_WIDTH = 4.0f;

    private int[] mColors = new int[]{Color.BLUE,Color.GRAY,Color.RED};

    private int mCurrentColor = Color.BLUE;

    private int repeatCount = 0;

    private AnimatorListenerAdapter mAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationRepeat(Animator animation) {
            if (repeatCount == mColors.length - 1){
                repeatCount = 0;
            }else {
                repeatCount ++;
            }
//            Logger.d("repeatCount:"+repeatCount);
            mCurrentColor = mColors[repeatCount];
        }
    };

    public MaterialLoadingDrawable(Context context) {
        super(context);
        mContext = context;
        initialize();
    }

    private void initialize() {
        setAnimatorListener(mAnimatorListener);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(DisplayUtils.dip2px(mContext,DEFAULT_STROKE_WIDTH));
    }

    @Override
    protected void computerProgress(float progress) {
        mProgress = progress;
    }

    @Override
    public void draw(Canvas canvas) {

        mPaint.setColor(mCurrentColor);

        canvas.translate(mBound.centerX(),mBound.centerY());

        canvas.rotate(mProgress * 360.0f);

        RectF circle = new RectF(-mBound.centerX()/2.0f,-mBound.centerY()/2.0f,mBound.centerX()/2.0f,mBound.centerY()/2.0f);

//        canvas.drawRect(circle,mPaint);

        if (mProgress < 0.5){

            canvas.drawArc(circle,0,mProgress * 2 * 360.0f,false,mPaint);
        }else {
            canvas.drawArc(circle,0,(1-mProgress) * 2 * -360.0f,false,mPaint);
        }




    }
}
