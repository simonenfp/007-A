package com.simonenfp.me.module.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;
import com.simonenfp.me.loading.AnimationView;
import com.simonenfp.me.loading.FuckProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimatorActivity extends BaseActivity {


    @Bind(R.id.av)
    AnimationView av;
    @Bind(R.id.progress)
    ImageView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);
        FuckProgressBar fuckProgressBar = new FuckProgressBar(this);
        progress.setImageDrawable(fuckProgressBar);
        fuckProgressBar.start();

    }


}
