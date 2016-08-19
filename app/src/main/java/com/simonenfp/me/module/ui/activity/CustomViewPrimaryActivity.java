package com.simonenfp.me.module.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomViewPrimaryActivity extends BaseActivity {

    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.iv_2)
    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusom_view);
        ButterKnife.bind(this);

        //创建画布，并在上面绘制文字
        drawOnBitmap();

        //绘制圆角bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.beauty1);
        bitmap = getCircleCornerBitmap(bitmap,20);
        iv2.setImageBitmap(bitmap);
    }

    private void drawOnBitmap() {
        //创建一个600x300的bitmap
        Bitmap bitmap = Bitmap.createBitmap(800, 200, Bitmap.Config.ARGB_8888);
        //创建画布
        Canvas canvas = new Canvas(bitmap);
//        Fill the entire canvas' bitmap (restricted to the current clip) with the specified color and porter-duff xfermode.
        canvas.drawColor(Color.YELLOW);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
//        在画布上写text，以画布左上角为原点
//        create canvas and draw on bitmap
        canvas.drawText("create canvas and draw on bitmap", 0, 60, paint);
        iv1.setImageBitmap(bitmap);

    }

    public Bitmap getCircleCornerBitmap(Bitmap bitmap,float radius){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap circleCornerBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleCornerBitmap);
//        canvas.drawColor(Color.RED);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        Rect rect = new Rect(0,0,width,height);
        RectF rectF = new RectF(rect);
//        2.方向上的圆角半径。3.方向上的圆角半径。
        canvas.drawRoundRect(rectF,radius,radius,paint);
        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        paint.setXfermode(mode);
//        第一个Rect 代表要绘制的bitmap 区域，第二个 Rect 代表的是要将bitmap 绘制在屏幕的什么地方
        canvas.drawBitmap(bitmap,rect,rect,paint);

        return circleCornerBitmap;
    }


}
