package com.simonenfp.me.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.R;

/**
 * Created by simonenfp on 2016/8/23.
 */
public class CustomView2 extends View {

    private String contentText;
    private float contentTextSize;
    private int contentTextColor;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);
//        获得定义的自定义样式属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView2);
        contentText = typedArray.getString(R.styleable.CustomView2_contentText);
        contentTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomView2_contentTextSize,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        contentTextColor = typedArray.getColor(R.styleable.CustomView2_contentTextColor, Color.BLACK);

        typedArray.recycle();


        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(contentTextSize);
        mPaint.setColor(contentTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(contentText, 0, contentText.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            mPaint.setTextSize(contentTextSize);
            mPaint.getTextBounds(contentText,0,contentText.length(),mBound);
            float textWidth = mBound.width();
            width = (int)(getPaddingLeft() + textWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(contentTextSize);
            mPaint.getTextBounds(contentText, 0, contentText.length(), mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(contentTextColor);
        Logger.d(mBound.left+","+mBound.top+","+mBound.right+","+mBound.bottom);
        canvas.drawText(contentText, 0,contentTextSize, mPaint);

    }
}
