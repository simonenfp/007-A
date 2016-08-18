package com.simonenfp.me.module.presenter;

import com.simonenfp.me.app.MyApplication;
import com.simonenfp.me.R;
import com.simonenfp.me.module.view.RetrofitActivityView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by simonenfp on 2016/6/22.
 */
public class RetrofitActivityPresenter {

    private RetrofitActivityView retrofitActivityView;

    public RetrofitActivityPresenter(RetrofitActivityView retrofitActivityView){
        this.retrofitActivityView = retrofitActivityView;
    }
    public void getTabData(){
        String [] array = MyApplication.getInstance().getApplicationContext().getResources().getStringArray(R.array.activity_retrofit_tab_title);
        if (array != null && array.length > 0){
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(array));
            retrofitActivityView.initTabLayoutTitle(list);
        }


    }
}
