package com.example.administrator.rxjava2study.test.SchedulerUtils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dreamY on 2017/5/4.
 */

public interface BaseSchedulerProvider {

    //main thread
    Scheduler ui();

    //io thread
    Scheduler io();

    //new thread
    Scheduler newThread();

    //computer thread
    Scheduler computation();

    //current Thread
    Scheduler currentThread();


}
