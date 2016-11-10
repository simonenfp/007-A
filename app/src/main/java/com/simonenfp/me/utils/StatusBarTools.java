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

import com.simonenfp.me.R;

/**
 * Created by simonenfp on 2016/6/23.
 */
public class StatusBarTools {

    public static void dealStatusBar(Activity activity){
        //set transparent status after 4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            Window window = activity.getWindow();


            //set transparent status and let main layout extend to status
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //set transparent navigation and let main layout extend to navigation
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

            //set contentView rootView padding in order to set aside the location of the power strip
            View rootView = mContentView.getChildAt(0);
            if (rootView != null){
                rootView.setFitsSystemWindows(true);
//                rootView.setPadding(0,getStatusBarHeight(activity),0,0);
            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){


            Window window = activity.getWindow();

            window.getDecorView().setSystemUiVisibility(
                    //Let the main layout extend to status
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //Let the main layout extend to navigation
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //set navigation transparent
//            window.setNavigationBarColor(Color.TRANSPARENT);



            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, true);
//                mChildView.setPadding(0,getStatusBarHeight(activity),0,0);
            }
        }
    }
    //Get status bar height
    public static float getStatusBarHeight(Context context) {
        float result = 0;
        float resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset((int)resId);
        }else if (resId == 0){
            result = DisplayUtils.dip2px(context,20);
        }
        return result;
    }

}
