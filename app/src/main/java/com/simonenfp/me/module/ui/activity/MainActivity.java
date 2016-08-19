package com.simonenfp.me.module.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRetrofit(View view){
        Intent intent = new Intent(mContext,RetrofitActivity.class);
        startActivity(intent);
    }

    public void onCustomViewPrimary(View view){
        Intent intent = new Intent(mContext,CustomViewPrimaryActivity.class);
        startActivity(intent);
    }
    public void onCustomViewExample1(View view){
        Intent intent = new Intent(mContext,CustomViewExample1Activity.class);
        startActivity(intent);
    }
}