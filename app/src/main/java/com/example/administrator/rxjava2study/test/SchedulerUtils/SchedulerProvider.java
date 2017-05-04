package com.example.administrator.rxjava2study.test.SchedulerUtils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dreamY on 2017/5/4.
 */

public class SchedulerProvider implements BaseSchedulerProvider{

    private SchedulerProvider mSchedulerProvider;

    private SchedulerProvider(){

    }

    public static SchedulerProvider getInstance(){

        return Holder.INSTANCE;
    }
    private static class Holder{
        private static final SchedulerProvider INSTANCE=new SchedulerProvider();
    }
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler currentThread() {
        return Schedulers.trampoline();
    }
}
