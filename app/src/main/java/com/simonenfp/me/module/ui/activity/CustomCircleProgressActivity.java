package com.simonenfp.me.module.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.simonenfp.me.R;
import com.simonenfp.me.widget.CustomCircleProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomCircleProgressActivity extends AppCompatActivity {


    @Bind(R.id.begin)
    TextView begin;
    @Bind(R.id.progress1)
    CustomCircleProgress progress1;
    @Bind(R.id.progress2)
    CustomCircleProgress progress2;
    @Bind(R.id.progress3)
    CustomCircleProgress progress3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_round_progress);
        ButterKnife.bind(this);


        initProgress();
    }

    private void initProgress() {

        progress1.setDuration(6000);
        progress1.setShowPercent(true);
        progress1.setIncreasing(true);

        progress2.setShowPercent(true);
        progress2.setDuration(5000);

        progress3.setDuration(4000);
        progress3.setIncreasing(true);
    }


    @OnClick(R.id.begin)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.begin:
                progress1.startRun();
                progress2.startRun();
                progress3.startRun();
                break;
        }
    }
}
