package com.simonenfp.me.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
//    boolean mIsMeasure = false;
//    int mViewSize;
    int mDefaultRadius = 200;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        Logger.d("widthSpecMode:"+widthSpecMode+" heightSpecMode"+heightSpecMode);
        Logger.d("widthSpecSize:"+widthSpecSize+" heightSpecSize"+heightSpecSize);


        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultRadius,mDefaultRadius);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultRadius,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,mDefaultRadius);
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Logger.d("width000:"+width+" height000:"+height);
        int mViewSize = ((height < width ? height : width));
        setMeasuredDimension(mViewSize,mViewSize);
        Logger.d("width111:"+getMeasuredWidth()+" height111:"+getMeasuredHeight());
    }


    /*
       * view占据的矩形Rect
       * */
    private Rect mRectBound;
    /*
      * view占据的矩形RectF
      * */
    private RectF mRectFBound;
    /*
    * 画笔
    * */
    private Paint mPaint;

    /*
    * 进度条宽度
    * */
    private int mProgressWidth;

    /*
    * 中心文字
    * */
    private String mCenterText;
    /*
    * 中心文字占地
    * */
    private Rect mTextRect;

    private long curTime ;

    private void init(){
        mPaint = new Paint();
        mProgressWidth = 20;
        mRectBound = new Rect();
        mRectFBound = new RectF();
        mTextRect = new Rect();
        mCenterText = "跳过跳过跳过";

        curTime = System.currentTimeMillis();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        //设置绘制区的矩形，为圆环外界留出圆环宽度一半的大小
        mRectBound.set(mProgressWidth/2,mProgressWidth/2,getWidth() - mProgressWidth/2,getHeight() - mProgressWidth/2);

        mRectFBound.set(mRectBound);
        mPaint.setAntiAlias(true);
        //清除正方形背景颜色
        setBackgroundColor(Color.TRANSPARENT);


//        画实心背景 半径为矩形区域减去圆环宽度的一半
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mRectBound.centerX(),mRectBound.centerY(),mRectBound.width()/2 - mProgressWidth/2,mPaint);



//        canvas.drawCircle(mRectBound.centerX(),mRectBound.centerY(),mRectBound.width()/2 - mProgressWidth/2,mPaint);
//        canvas.drawCircle();

        //画中心文字 中心文字起点为左下，文字绘制在圆形中间位置
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(30);
        if (change){
            mCenterText = "ddddd";
            change = false;
        }else {
            mCenterText = "ssssss";
            change = true;
        }
        mPaint.getTextBounds(mCenterText,0,mCenterText.length(),mTextRect);
        canvas.drawText(mCenterText,mRectBound.centerX() - mTextRect.width()/2,mRectBound.centerY() + mTextRect.height()/2,mPaint);

//        画圆环   因为圆环有宽度，所以是环的中间位置与矩形四边相切
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectFBound,0,360*((timeMillis - d)/timeMillis),false,mPaint);
        Logger.d("ddddddddddd:"+d);


    }
    private boolean change = true;
    /**
     * 进度时间。
     */
    private long timeMillis = 3000;
    /*
    * 总进度
    * */
    private int progress = 100;
    private long d = 0;

    public void updateProgress(){

        post(pro);


    }

    private Runnable pro = new Runnable() {
        @Override
        public void run() {

            if (d <= timeMillis){
                d = d+1;
                invalidate();


                postDelayed(pro, timeMillis / 10);
            }
        }
    };
}
