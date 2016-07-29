package com.simonenfp.me.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by simonenfp on 2016/7/27.
 */
public class MyActivityManager {
    private static MyActivityManager instance;
    private static Stack<Activity> activityStack;
    private MyActivityManager(){}
    public static MyActivityManager getInstance(){
        if (instance == null){
            instance = new MyActivityManager();
        }
        return instance;
    }

    /*
    * add activity to stack
    * */
    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }
    /*
    * obtain current activity(last added to stack)
    * */
    public Activity currentActivity(){
        return activityStack.lastElement();
    }

    /*
    * finish current activity
    * */
    public void finishCurrentActivity(){
        finishSpecifiedActivity(activityStack.lastElement());
    }
    /**
     * finish a specified Activity
     */
    public void finishSpecifiedActivity(Activity activity){
        if(activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    * finish a specified class name activity
    * */

    public void finishSpecifiedActivity(Class<?> clazz){
        for (Activity activity : activityStack){
            if (activity.getClass().equals(clazz)){
                finishSpecifiedActivity(activity);
            }
        }
    }

    /*
    * finish all activity
    * */
    public void finishAllActivity(){
        for (Activity activity : activityStack){
            if (null != activity){
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /*
    * exit app
    * */
    public void ExitApp(Context context){
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            manager.restartPackage(context.getPackageName());
            System.exit(0);
        }catch (Exception e){
        }
    }

}
