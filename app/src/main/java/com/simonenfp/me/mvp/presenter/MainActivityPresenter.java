package com.simonenfp.me.mvp.presenter;

import android.content.res.TypedArray;

import com.simonenfp.me.MyApplication;
import com.simonenfp.me.R;
import com.simonenfp.me.mvp.view.MainActivityView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by simonenfp on 2016/6/22.
 */
public class MainActivityPresenter {

    private MainActivityView mainActivityView;

    public MainActivityPresenter(MainActivityView mainActivityView){
        this.mainActivityView = mainActivityView;
    }
    public void getTabData(){
        String [] array = MyApplication.getInstance().getApplicationContext().getResources().getStringArray(R.array.activity_main_tab_title);
        if (array != null && array.length > 0){
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(array));
            mainActivityView.initTabLayoutTitle(list);
        }


    }
}
