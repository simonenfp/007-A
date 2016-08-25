package com.simonenfp.me.module.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;
import com.simonenfp.me.widget.MyScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewExample1Activity extends BaseActivity {

    @Bind(R.id.scrollView)
    MyScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_example1);
        ButterKnife.bind(this);
    }

    public void onScrollTv(View view) {
        scrollView.smoothScrollTo(300,400);
    }


}
