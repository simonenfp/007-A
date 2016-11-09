package com.simonenfp.me.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.simonenfp.me.R;

/**
 * Created by simonenfp on 2016/11/3.
 */

public class AnimationView extends ImageView {
    public LoadingDrawable loadingDrawable;
    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        loadingDrawable = new LoadingDrawable(context);
        setImageDrawable(loadingDrawable);
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
