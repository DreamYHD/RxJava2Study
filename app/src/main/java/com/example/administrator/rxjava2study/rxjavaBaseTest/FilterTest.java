package com.example.administrator.rxjava2study.rxjavaBaseTest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by dreamY on 2017/5/2.
 */

public class FilterTest {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                for (int i = 0; i <10 ; i++) {
                    e.onNext(i);
                }
                e.onComplete();
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer mInteger) throws Exception {
                return mInteger!=10;
            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer mInteger) throws Exception {
                return mInteger+10;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Disposable");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
                for (int i = 0; i <11 ; i++) {
                  e.onNext(i);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR).doOnCancel(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("cancle");
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer mInteger) throws Exception {
                return mInteger!=10;
            }
        }).subscribe(new Subscriber<Integer>() {
            private Subscription mSubscription;
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription=s;
                System.out.println("Subscription");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer mInteger) {

                System.out.println(mInteger);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                mSubscription.cancel();
                System.out.println("complete");
            }
        });


    }
}
