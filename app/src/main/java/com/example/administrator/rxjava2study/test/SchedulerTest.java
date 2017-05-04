package com.example.administrator.rxjava2study.test;

import com.example.administrator.rxjava2study.test.SchedulerUtils.SchedulerProvider;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dreamY on 2017/5/3.
 */

public class SchedulerTest {
    public static void main(String[] args) {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {

                e.onNext("hello Flowable");
                System.out.println(Thread.currentThread().getName());

                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    private Subscription mSubscription;
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("Subscription");
                        mSubscription=s;
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String mS) {

                        System.out.println(mS);
                        System.out.println(Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override

            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
                System.out.println(Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String mS) throws Exception {
                        System.out.println(mS);
                        System.out.println(Thread.currentThread().getName());
                    }
                });




    }
}
