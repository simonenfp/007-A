package com.simonenfp.me.loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.simonenfp.me.R;

/**
 * Created by simonenfp on 2016/11/3.
 */

public class AnimationView extends ImageView {

    public LoadingDrawable loadingDrawable;

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.AnimationView);
        int loadingDrawableId = typedArray.getInt(R.styleable.AnimationView_loadingDrawable,0);
        if (loadingDrawableId == 0){
            loadingDrawable = new SearchLoadingDrawable(context);
            setImageDrawable(loadingDrawable);
        }else if (loadingDrawableId == 1){
            loadingDrawable = new MaterialLoadingDrawable(context);
            setImageDrawable(loadingDrawable);
        }else if (loadingDrawableId == 2){
            loadingDrawable = new StripProgressBarDrawable(context);
            setImageDrawable(loadingDrawable);
        }

        typedArray.recycle();


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        loadingDrawable.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loadingDrawable.stop();
    }


}
