package com.simonenfp.me.module.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;
import com.simonenfp.me.loading.AnimationView;
import com.simonenfp.me.loading.StripProgressBarDrawable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimatorActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);

    }


}
