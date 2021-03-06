package com.simonenfp.me.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by simonenfp on 2016/7/29.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;
    private boolean isDestroy;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestroy = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
    }

    protected void showToast(String s){
        if (!isDestroy) {
            Snackbar.make(getView(), s, Snackbar.LENGTH_SHORT).show();
        }
    }

}
