package com.simonenfp.me.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simonenfp.me.R;
import com.simonenfp.me.utils.StatusBarTools;

/**
 * Created by simonenfp on 2016/7/27.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Context mContext;
    public ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        MyActivityManager.getInstance().addActivity(this);

    }

    private void initActionBarView() {
        //back button
         ivBack =  (ImageView)findViewById(R.id.iv_main_top_bar_back);
        if (ivBack != null){
            ivBack.setOnClickListener(this);
        }

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        ViewGroup contentParent = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, null);

        contentParent.addView(LayoutInflater.from(this).inflate(layoutResID,null));

        super.setContentView(contentParent);

        //         Hide the status bar.
        StatusBarTools.dealStatusBar(this);

        initActionBarView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityManager.getInstance().finishSpecifiedActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_main_top_bar_back:
                finish();
                break;
        }
    }
}
