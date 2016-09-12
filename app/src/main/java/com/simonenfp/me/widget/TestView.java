package com.simonenfp.me.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

/**
 * Created by simonenfp on 2016-09-12.
 */
public class TestView extends TextView {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        Logger.d("widthSpecMode:"+widthSpecMode+" heightSpecMode"+heightSpecMode);
        Logger.d("widthSpecSize:"+widthSpecSize+" heightSpecSize"+heightSpecSize);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Logger.d("width000:"+width+" height000:"+height);
        int mViewSize = ((height < width ? height : width));
        setMeasuredDimension(mViewSize,mViewSize);
        Logger.d("width111:"+getMeasuredWidth()+" height111:"+getMeasuredHeight());
    }
}
