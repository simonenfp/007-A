package com.simonenfp.me.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.R;


/**
 * Created by simonenfp on 2016/9/7.
 */
public class CustomCircleProgress extends View {
    /*
    * 中心文字
    * */
    private String mCenterText;
    /*
    * 中心文字颜色
    * */
    private int mCenterTextColor;
    /*
    * 中心文字大小
    * */
    private float mCenterTextSize;
    /*
    * 圆形背景
    * */
    private int mCircleBackground;
    /*
    * 圆环进度颜色
    * */
    private int mRingProgressColor;
    /*
    * 圆环进度背景颜色
    * */
    private int mRingProgressBackground;
    /*
    * 圆环进度宽度
    * */
    private float mRingProgressWidth;
    /*
   * 默认圆形半径
   * */
    int mDefaultRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());

    /*
   * 画笔
   * */
    private Paint mPaint;

    /*
      * view占据的矩形RectF
      * */
    private RectF mRectFBound = new RectF();

    /*
    * 中心文字占位置
    * */
    private Rect mTextRect = new Rect();

    /*
   * 圆环起始弧度,默认360
   * */
    private int mArc = 360;

    /*
    * 当前进度
    * */
    private float mCurrProgress;


    /*
    * 进度开始时刻
    * */
    private long mStartTime ;

    /*
    * 进度是否已开始
    * */
    private boolean mStart = false;

    /**
     * 默认进度完成总时间。
     */
    private int mDuration = 3000;

    /*
    * 进度完成时间倒数
    * */
    private float mDurationReciprocal;

    /*
    * 进度是否渐增，默认是递减
    * */
    private boolean mIncreasing = false;

    /*
    * 是否显示百分比进度，默认不显示
    * */
    private boolean mShowPercent = false;
    /*
    * 百分比进度，默认100%
    * */
    private int mPercent = 100;

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void setIncreasing(boolean mIncreasing) {
        this.mIncreasing = mIncreasing;
        if (mIncreasing){
            mArc = 0;
            mPercent = 0;
        }else {
            mArc = 360;
            mPercent = 100;
        }
    }

    public void setShowPercent(boolean mShowPercent) {
        this.mShowPercent = mShowPercent;
    }

    public CustomCircleProgress(Context context){
        this(context,null);
    }

    public CustomCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CustomCircleProgress(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        //        获得定义的自定义样式属性
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CustomCircleProgress,defStyleAttr,0);

        mCenterText = a.getString(R.styleable.CustomCircleProgress_centerText);
        mCenterTextColor = a.getColor(R.styleable.CustomCircleProgress_centerTextColor,Color.WHITE);
        mCenterTextSize = a.getDimensionPixelSize(R.styleable.CustomCircleProgress_centerTextSize,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        mCircleBackground = a.getColor(R.styleable.CustomCircleProgress_circleBackground,Color.BLACK);

        mRingProgressColor = a.getColor(R.styleable.CustomCircleProgress_ringProgressColor,Color.GRAY);
        mRingProgressBackground = a.getColor(R.styleable.CustomCircleProgress_ringProgressBackground,Color.TRANSPARENT);
        mRingProgressWidth = a.getDimensionPixelSize(R.styleable.CustomCircleProgress_ringProgressWidth,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));

        a.recycle();
        initialize();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);


        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultRadius,mDefaultRadius);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultRadius,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,mDefaultRadius);
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int mViewSize = ((height < width ? height : width));
        setMeasuredDimension(mViewSize,mViewSize);
    }

    private void initialize(){

        if (mCenterText == null){
            mCenterText = "";
        }

        mPaint = new Paint();
        //清除正方形背景颜色
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //设置绘制区的矩形，为圆环外界留出圆环宽度一半的大小
        mRectFBound.set(mRingProgressWidth/2,mRingProgressWidth/2,getWidth() - mRingProgressWidth/2,getHeight() - mRingProgressWidth/2);
        mPaint.setAntiAlias(true);

//        画实心背景 半径为矩形区域减去圆环宽度的一半
        mPaint.setColor(mCircleBackground);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mRectFBound.centerX(),mRectFBound.centerY(),mRectFBound.width()/2 - mRingProgressWidth/2,mPaint);


        //画中心文字 中心文字起点为左下，文字绘制在圆形中间位置
        mPaint.setColor(mCenterTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        if (mShowPercent){
            mCenterText = String.valueOf(mPercent) + "%";
        }
        mPaint.setTextSize(mCenterTextSize);
        mPaint.getTextBounds(mCenterText,0,mCenterText.length(),mTextRect);
        canvas.drawText(mCenterText,mRectFBound.centerX() - mTextRect.width()/2,mRectFBound.centerY() + mTextRect.height()/2,mPaint);

        //        画圆环   因为圆环有宽度，所以是环的中间位置与矩形四边相切
        mPaint.setStrokeWidth(mRingProgressWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(mRingProgressBackground);
        canvas.drawArc(mRectFBound,0,360,false,mPaint);

        mPaint.setColor(mRingProgressColor);
        canvas.drawArc(mRectFBound,0,mArc,false,mPaint);

        updateProgress();
    }

    /*
    * 进度开始
    * */
    public void startRun(){
        mStartTime =  AnimationUtils.currentAnimationTimeMillis();

        mStart = true;
        mDurationReciprocal = 1.0f / (float) mDuration;
        updateProgress();
    }


    /*
    * 更新进度
    * */
    private void updateProgress(){
        if (computeProgressOffset()){

            if (mIncreasing){
                mArc = Math.round(360 * mCurrProgress);
                mPercent = Math.round(100 * mCurrProgress);
            }else {
                mArc = Math.round(360 * (1-mCurrProgress));
                mPercent = Math.round(100 * (1-mCurrProgress));
            }
            postInvalidate();
        }
    }

    /*
    * 根据时间流逝计算进度比例
    * */
    private boolean computeProgressOffset(){
        if (!mStart){
            return false;
        }
        int timePass = (int) (AnimationUtils.currentAnimationTimeMillis() - mStartTime);
        if (timePass < mDuration){
            mCurrProgress = timePass * mDurationReciprocal;
        }else {
            mStart = false;
            mCurrProgress = 1;
        }
        return true;
    }

}
