package com.simonenfp.me.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by simonenfp on 2016/6/23.
 */
public class StatusBarTools {

    public static void dealStatusBar(Activity activity){
        Window window = activity.getWindow();
        //set transparent status
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        //set contentView rootView padding in order to set aside the location of the power strip
        View rootView = mContentView.getChildAt(0);
        if (rootView != null){
            rootView.setPadding(0,getStatusBarHeight(activity),0,0);
        }

    }
    //Get status bar height
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

}
