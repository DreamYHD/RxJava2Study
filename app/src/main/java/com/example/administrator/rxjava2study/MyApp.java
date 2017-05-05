package com.example.administrator.rxjava2study;

import android.app.Application;
import android.content.Context;

import com.example.administrator.rxjava2study.rxJavaNetTest.MovieRetrofitManager;
import com.example.administrator.rxjava2study.rxjavaBaseTest.SchedulerUtils.SchedulerProvider;

/**
 * Created by dreamY on 2017/5/5.
 */

public class MyApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        MovieRetrofitManager.getInstance();
        SchedulerProvider.getInstance();
    }
    public static Context getContext(){
        return mContext;
    }
}
