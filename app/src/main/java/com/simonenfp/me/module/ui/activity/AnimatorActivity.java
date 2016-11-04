package com.simonenfp.me.module.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorActivity extends BaseActivity {


    @Bind(R.id.tv_animator)
    TextView tvAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.tv_animator)
    public void onClick() {
        ObjectAnimator.ofInt(new WrapperView(tvAnimator),"width",500).setDuration(5000).start();
    }
    public class WrapperView{
        private View mTargetView;
        public WrapperView(View textView){
            mTargetView = textView;
        }
        public void setWidth(int width){
            mTargetView.getLayoutParams().width = width;
            mTargetView.requestLayout();
        }
        public int getWidth(){
            return mTargetView.getLayoutParams().width;
        }
    }
}
