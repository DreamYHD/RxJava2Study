package com.example.administrator.rxjava2study.rxjavaBaseTest.SchedulerUtils;

import io.reactivex.Scheduler;

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
