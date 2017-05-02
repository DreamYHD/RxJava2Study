package com.example.administrator.rxjava2study;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dreamY on 2017/5/2.
 */

public class ObservableTest {
    public static void main(String[] args) {
        /**
         * 不支持被背压，当有大量数据的时候会oom(out of memory)，官方给出的数据是1000
         */
        Observable mObservable= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                for (long i = 0; i <1000 ; i++) {
                    e.onNext("hello");
                }

                e.onComplete();
            }
        });
        Observer mObserver=new Observer<String>() {
            //这个是新加入的方法，在订阅后发送数据之前会首先调用这个方法，Disposable可以用于取消订阅
            private Disposable mDisposable;
            int i=0;
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("disposable");
                mDisposable=d;

            }

            @Override
            public void onNext(String value) {

                System.out.println(value);
                i++;
                if (i==10){
                    mDisposable.dispose();
                    System.out.println("stop by dispose");
                }

            }


            @Override
            public void onError(Throwable e) {

                System.out.println("error");
            }

            @Override
            public void onComplete() {

                System.out.println("success");
            }
        };
        mObservable.subscribe(mObserver);


    }
}
