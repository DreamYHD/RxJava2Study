package com.example.administrator.rxjava2study.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dreamY on 2017/5/2.
 */

public class ZipAndMergerTest {
    public static void main(String[] args) {
        /**
         *
         * merger把两个或者多个Observables合并到他们发射的数据项里面
         */
       Observable mObservable=Observable.create(new ObservableOnSubscribe<String>() {
           @Override
           public void subscribe(ObservableEmitter e) throws Exception {

               e.onNext("hello");
               e.onNext("android");
               e.onNext("lab");

               e.onComplete();
           }
       });
        Observable mObservable1=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                e.onNext("bao");
                e.onNext("qian");
                e.onNext("yue");
                e.onComplete();
            }
        });
        Observable mObservable2=Observable.merge(mObservable1,mObservable);
        mObservable2.subscribe(new Consumer() {
            @Override
            public void accept(Object mO) throws Exception {
                System.out.println(mO);
            }
        });
        /**
         * zip合并两个或者多个Observables发射出来的数据项，根据指定的函数 Fun*变化他们，并且发射一个新值
         */

        Observable mObservable3=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                e.onNext("2015");
                e.onNext("2016");
                e.onNext("2017");
                e.onComplete();
            }
        });

        Observable mObservable4=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                e.onNext("i");
                e.onNext("love");
                e.onNext("you");
                e.onComplete();
            }
        });
        Observable mObservable5=Observable.zip(mObservable3, mObservable4, new BiFunction<String,String,String>() {
            @Override
            public String apply(String mO, String mO2) throws Exception {
                return mO+mO2;
            }

        });
        mObservable5.subscribe(new Consumer<String>() {
            @Override
            public void accept(String mO) throws Exception {

                System.out.println(mO);
            }
        });




    }
}
