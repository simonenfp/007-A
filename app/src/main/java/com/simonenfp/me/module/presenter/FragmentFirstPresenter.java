package com.simonenfp.me.module.presenter;

import com.orhanobut.logger.Logger;
import com.simonenfp.me.module.model.Data;
import com.simonenfp.me.module.model.HttpResult;
import com.simonenfp.me.module.model.PhotoEntity;
import com.simonenfp.me.module.view.FragmentFirstView;
import com.simonenfp.me.network.HttpMethods;
import com.simonenfp.me.subscribers.MySubscribers;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by simonenfp on 2016/7/29.
 */
public class FragmentFirstPresenter{
    private FragmentFirstView fragmentFirstView;
    public FragmentFirstPresenter(FragmentFirstView fragmentFirstView){
        this.fragmentFirstView = fragmentFirstView;
    }
    protected Subscription subscription;
    public void getData(String id,int startPage){
        unsubscribe_m();
        subscription =  HttpMethods.getInstance()
                .getPhotoListObservable(id,startPage)
                .map(new Func1<Data, List<PhotoEntity>>() {
                    @Override
                    public List<PhotoEntity> call(Data dataHttpResult) {
                        return dataHttpResult.list;
                    }
                })
                .subscribe( new Subscriber<List<PhotoEntity>>() {

                    @Override
                    public void onStart() {
                        fragmentFirstView.requestBefore();
                    }

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
