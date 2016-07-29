package com.simonenfp.me.network.api;

import com.simonenfp.me.module.model.CommonItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by simonenfp on 2016/7/29.
 */
public interface CommonItemApi {
    @GET("search")
    Observable<List<CommonItem>> search(@Query("q") String query);
}
