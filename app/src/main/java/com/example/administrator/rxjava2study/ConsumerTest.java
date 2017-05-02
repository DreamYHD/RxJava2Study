package com.example.administrator.rxjava2study;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by dreamY on 2017/5/2.
 */

public class ConsumerTest {
    public static void main(String[] args) {
        //不含参数的Consumer默认处理onNext事件
        Consumer mConsumer= new Consumer<String>() {
            @Override
            public void accept(String mS) throws Exception {

                System.out.println(mS);
            }
        };

        Consumer mConsumer1= new Consumer<Error>() {
            @Override
            public void accept(Error mError) throws Exception {
                System.out.println(mError.getMessage().toString());
            }
        };

        Action mAction=new Action() {
            @Override
            public void run() throws Exception {

                System.out.println("complete");
            }
        };
        Consumer mConsumer2=new Consumer() {
            @Override
            public void accept(Object mO) throws Exception {
                System.out.println("success");
            }
        };
        Observable mObservable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                e.onNext("hello");
               // e.onError(new NullPointerException());
                e.onNext("android");
                e.onComplete();
                e.onNext("lab");
            }
        });

        mObservable.subscribe(mConsumer,mConsumer1,mAction);



    }
}
