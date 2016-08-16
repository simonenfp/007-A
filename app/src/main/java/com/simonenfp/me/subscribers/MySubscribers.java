package com.simonenfp.me.subscribers;

import com.simonenfp.me.module.presenter.FragmentFirstPresenter;

import rx.Subscriber;
import rx.Subscription;
import rx.observers.Subscribers;

/**
 * Created by simonenfp on 2016/8/16.
 */
public class MySubscribers<T> extends Subscriber<T> {
    private SubscriberOnNextListener listener;
    public MySubscribers(SubscriberOnNextListener listener){
        this.listener = listener;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }
}
