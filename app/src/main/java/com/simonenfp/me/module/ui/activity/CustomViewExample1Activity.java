package com.simonenfp.me.module.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewExample1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_example1);
    }
}
