package com.simonenfp.me.module.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.R;
import com.simonenfp.me.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickView(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.tv_main_retrofit:
                intent = new Intent(mContext,RetrofitActivity.class);
                break;
            case R.id.tv_main_custom_view_primary:
                intent = new Intent(mContext,CustomViewPrimaryActivity.class);
                break;
            case R.id.tv_main_custom_view_example:
                intent = new Intent(mContext,CustomViewExample1Activity.class);
                break;
            case R.id.tv_main_custom_round_progress:
                intent = new Intent(mContext,CustomCircleProgressActivity.class);
                break;
            case R.id.tv_main_animator:
                intent = new Intent(mContext,AnimatorActivity.class);
                break;
            default:
                break;
        }
        if (intent != null){
            startActivity(intent);
        }

    }
}