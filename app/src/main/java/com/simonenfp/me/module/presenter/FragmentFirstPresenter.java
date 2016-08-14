package com.simonenfp.me.module.presenter;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.module.model.CommonItem;
import com.simonenfp.me.module.model.Data;
import com.simonenfp.me.module.model.HttpResult;
import com.simonenfp.me.module.model.PhotoEntity;
import com.simonenfp.me.module.view.FragmentFirstView;
import com.simonenfp.me.network.Network;
import com.simonenfp.me.network.RetrofitManager;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
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
    public void getData(String id,int startPage){
//        unsubscribe_m();

        subscription = RetrofitManager.getInstance()
                .getPhotoListObservable(id,startPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        fragmentFirstView.requestBefore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Func1<HttpResult<Data>, List<PhotoEntity>>() {
                    @Override
                    public List<PhotoEntity> call(HttpResult<Data> dataHttpResult) {
                        return dataHttpResult.data.list;
                    }
                })
                .subscribe(new Observer<List<PhotoEntity>>() {
                    @Override
                    public void onCompleted() {
                        fragmentFirstView.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentFirstView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<PhotoEntity> photoEntities) {
                        fragmentFirstView.updateView(photoEntities);
                    }
                });


    }
    protected void unsubscribe_m() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
