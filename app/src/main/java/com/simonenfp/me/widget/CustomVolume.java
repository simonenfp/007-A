package com.simonenfp.me.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by simonenfp on 2016/8/26.
 */
public class CustomVolume extends View {
    Paint mPaint;
    RectF mRectF;
    int mCircleWidth;
    public CustomVolume(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRectF = new RectF();
        mCircleWidth = 30;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = 100;
        int rx = radius + mCircleWidth/2 + 300;
        int ry = radius + mCircleWidth/2 + 400;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.argb(0x33,0x5f,0x2c,0xe9));
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(rx,ry,radius,mPaint);



        //绘制圆弧
        mPaint.setColor(Color.argb(0xff,0x43,0x93,0x12));
        mPaint.setStrokeWidth(mCircleWidth-10);
        mRectF = new RectF(rx - radius,ry - radius,rx + radius,ry + radius);
        canvas.drawArc(mRectF,0,arc,false,mPaint);



//        canvas.drawLine(320,800,520,800,mPaint);

        //绘制百分比
        mPaint.reset();
        mPaint.setColor(Color.argb(0x88,0x43,0x93,0x12));
        mPaint.setTextSize(40);
        String s = String.valueOf(progress) + "%";
        Rect rect = new Rect();
        mPaint.getTextBounds(s,0,s.length(),rect);
        canvas.drawText(s,rx - rect.width()/2,ry + rect.height()/2,mPaint);


    }

    private int lastY;
    private int arc;
    private int progress;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (y - lastY)/2;
                int deltaArc = arc - deltaY;
                if (deltaArc >= 0 && deltaArc <= 360){
                    arc = deltaArc;
                }else if (deltaArc < 0){
                    arc = 0;
                }else {
                    arc = 360;
                }
//                arc = arc - deltaY >= 0 && arc - deltaY <= 360 ? arc - deltaY : arc;

                progress = arc * 100 / 360 ;

                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastY = y;
        return true;
    }
}
