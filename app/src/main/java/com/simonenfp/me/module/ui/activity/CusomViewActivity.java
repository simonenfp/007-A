package com.simonenfp.me.module.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CusomViewActivity extends BaseActivity {

    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.iv_2)
    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusom_view);
        ButterKnife.bind(this);

        drawOnBitmap();


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

    public Bitmap getCircleCornerBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap circleCornerBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        return circleCornerBitmap;
    }


}
