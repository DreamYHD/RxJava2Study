package com.example.administrator.rxjava2study;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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

            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer mInteger) throws Exception {
                return false;
            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer mInteger) throws Exception {
                return null;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
         Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                e.onNext("hello");
                e.onNext("android");
                e.onNext("lab");
                e.onComplete();
            }
        }).filter(new Predicate<String>() {

            @Override
            public boolean test(String mS) throws Exception {
                if (mS.equals("android")) {
                    return true;
                }
                return false;
        }}).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

                System.out.println("complete");
            }
        });

    }
}
