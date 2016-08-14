package com.simonenfp.me.network.service;

import com.simonenfp.me.module.model.CommonItem;
import com.simonenfp.me.module.model.Data;
import com.simonenfp.me.module.model.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by simonenfp on 2016/7/29.
 */
public interface CommonService {
    @GET("list.json")
    Observable<HttpResult<Data>> getPhotoList(
            @Query("channel") String photoTypeId,
            @Query("adid") String adid,
            @Query("wm") String wm,
            @Query("from") String from,
            @Query("chwm") String chwm,
            @Query("oldchwm") String oldchwm,
            @Query("imei") String imei,
            @Query("uid") String uid,
            @Query("p") int page
    );
}
