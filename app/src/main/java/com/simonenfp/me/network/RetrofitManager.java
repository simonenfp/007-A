package com.simonenfp.me.network;

import com.simonenfp.me.module.model.Data;
import com.simonenfp.me.module.model.HttpResult;
import com.simonenfp.me.network.service.CommonService;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simonenfp on 2016-08-14.
 */
public class RetrofitManager {
    private static CommonService commonService;

    private static RetrofitManager instance;

    private RetrofitManager(){}

    public static RetrofitManager getInstance(){
        if (instance == null){
            instance = new RetrofitManager();
        }
        return instance;
    }

    public static CommonService getCommonService (){
        if (commonService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl("http://api.sina.cn/sinago/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            commonService = retrofit.create(CommonService.class);
        }
        return commonService;

    }

    public Observable<HttpResult<Data>> getPhotoListObservable(String photoId, int page){
        return getCommonService().getPhotoList( photoId,
                "4ad30dabe134695c3b7c3a65977d7e72", "b207", "6042095012", "12050_0001",
                "12050_0001", "867064013906290", "802909da86d9f5fc", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

}
