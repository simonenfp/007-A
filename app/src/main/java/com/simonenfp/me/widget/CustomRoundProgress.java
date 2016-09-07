package com.simonenfp.me.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import retrofit2.http.PUT;

/**
 * Created by simonenfp on 2016/9/7.
 */
public class CustomRoundProgress extends TextView {

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int viewSize = ((width > height ? width : height) + 20);

        Logger.d("width:"+width+" height:"+height + " viewSize:"+viewSize);

        setMeasuredDimension(viewSize,viewSize);
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
        super.onDraw(canvas);
        //画个圆
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        //获取边界并保存在Rect对象里
        getDrawingRect(mRectBound);
        canvas.drawCircle(mRectBound.centerX(),mRectBound.centerY(),mRectBound.height()/2,mPaint);
    }
}
