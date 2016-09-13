package com.simonenfp.me.module.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.R;
import com.simonenfp.me.widget.CustomRoundProgress;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomRoundProgressActivity extends AppCompatActivity {

    @Bind(R.id.progress)
    CustomRoundProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_round_progress);
        ButterKnife.bind(this);


        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("onClick");
                progress.updateProgress();
            }
        });

    }
}
