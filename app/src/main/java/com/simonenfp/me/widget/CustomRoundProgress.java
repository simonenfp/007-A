package com.simonenfp.me.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import retrofit2.http.PUT;

/**
 * Created by simonenfp on 2016/9/7.
 */
public class CustomRoundProgress extends View {

    public CustomRoundProgress(Context context){
        this(context,null);
    }

    public CustomRoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CustomRoundProgress(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        init();
    }

    //RelativeLayout中使用会多次调用onMeasure，
    // 每次OnMeasure宽高赋值无规律，会导致最终宽高有误，设此标识，仅记录第一次size
    boolean mIsMeasure = false;
    int mViewSize;
    int mDefaultRadius = 200;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        Logger.d("widthSpecSize:"+widthSpecSize+" heightSpecSize:"+heightSpecSize);
        Logger.d("widthSpecMode:"+widthSpecMode+"heightSpecMode:"+heightSpecMode);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultRadius,mDefaultRadius);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultRadius,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            Logger.d("高是wrap_content");
            setMeasuredDimension(widthSpecSize,mDefaultRadius);
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Logger.d("getMeasuredWidth:"+width+" getMeasuredHeight:"+height);
        if (!mIsMeasure){
            mViewSize = ((height < width ? height : width)+mProgressWidth);
        }
        setMeasuredDimension(mViewSize,mViewSize);
        Logger.d("getMeasuredWidth1111:"+width+" getMeasuredHeight1111:"+height);
        mIsMeasure = true;

    }


    /*
       * view占据的矩形
       * */
    private Rect mRectBound;
    /*
    * 画笔
    * */
    private Paint mPaint;

    /*
    * 进度条宽度
    * */
    private int mProgressWidth;

    private void init(){
        mPaint = new Paint();
        mProgressWidth = 20;
        mRectBound = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //画个圆
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        getDrawingRect(mRectBound);
        canvas.drawCircle(mRectBound.centerX(),mRectBound.centerY(),mRectBound.width()/2 - mProgressWidth/2,mPaint);
        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setXfermode(mode);
//        canvas.drawCircle();

    }
}
