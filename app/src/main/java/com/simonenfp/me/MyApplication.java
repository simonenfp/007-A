package com.simonenfp.me;

import android.app.Application;

/**
 * Created by simonenfp on 2016/6/22.
 */
public class MyApplication extends Application {
    private static MyApplication appInstance;
    public static MyApplication getInstance() {
        if (appInstance == null) {
            throw new RuntimeException(
                    "Application has not initialed but how could you call me!!");
        }

        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}
