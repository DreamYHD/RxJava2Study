package com.example.administrator.rxjava2study.rxjavaBaseTest;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dreamY on 2017/5/2.
 */

public class MapAndFlatmapTest {
    public static void main(String[] args) {

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
                e.onNext("hello");
                System.out.println(e.requested());
                e.onNext("android");
                System.out.println(e.requested());

                e.onNext("lab");
                System.out.println(e.requested());

                e.onNext("nuc");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String mS) throws Exception {
                        return !mS.equals("hello");
                    }
                }).map(new Function<String, String>() {
            @Override
            public String apply(String mS) throws Exception {
                return mS + "i love you";
            }
        }).doOnCancel(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("cancel");
            }
        }).subscribe(new Subscriber<String>() {
            private Subscription mSubscription;

            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                System.out.println("Subscription");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String mS) {
                System.out.println(mS);

            }

            @Override
            public void onError(Throwable t) {

                System.out.println(t.getMessage().toString());
            }

            @Override
            public void onComplete() {
                mSubscription.cancel();
                System.out.println("complete");

            }
        });
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String mS) throws Exception {
                return false;
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String mS) throws Exception {
                return null;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String mS) throws Exception {
                return null;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String mS) throws Exception {

                    }
                });

        List<String>mStrings=new ArrayList<>();
        mStrings.add("yanghaodong");
        mStrings.add("yang");
        mStrings.add("hao");
        mStrings.add("dong");


        Flowable.just(mStrings)
                .flatMap(new Function<List<String>, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(List<String> mStrings) throws Exception {
                        return Flowable.fromIterable(mStrings);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String mS) throws Exception {
                System.out.println(mS);
            }
        });

        Observable.fromIterable(mStrings)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String mS) throws Exception {
                        System.out.println(mS);
                    }
                });







    }




    }
