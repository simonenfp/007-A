package com.simonenfp.me.module.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.module.model.CommonItem;
import com.simonenfp.me.module.view.FragmentFirstView;
import com.simonenfp.me.network.Network;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simonenfp on 2016/7/29.
 */
public class FragmentFirstPresenter {
    private FragmentFirstView fragmentFirstView;
    public FragmentFirstPresenter(FragmentFirstView fragmentFirstView){
        this.fragmentFirstView = fragmentFirstView;
    }
    protected Subscription subscription;
    Observer<List<CommonItem>> observer = new Observer<List<CommonItem>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            fragmentFirstView.showToast("出错");
        }

        @Override
        public void onNext(List<CommonItem> commonItems) {
            fragmentFirstView.initRecycleView(commonItems);
        }
    };
    public void getData(){
//        unsubscribe_m();
//        subscription = Network.getCommonItemApi()
//                .search("可爱")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
        final ArrayList<CommonItem> arrayList = new ArrayList<CommonItem>();
        CommonItem commonItem = new CommonItem();
        commonItem.setDescription("aaaaa");
        arrayList.add(commonItem);
        arrayList.add(commonItem);
        arrayList.add(commonItem);
        Observable.create(new Observable.OnSubscribe<List<CommonItem>>() {
            @Override
            public void call(Subscriber<? super List<CommonItem>> subscriber) {
                subscriber.onNext(arrayList);
            }
        }).subscribe(new Observer<List<CommonItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<CommonItem> stringList) {

                Logger.d(stringList.get(0).getDescription());
                fragmentFirstView.initRecycleView(stringList);
            }
        });

    }
    protected void unsubscribe_m() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
