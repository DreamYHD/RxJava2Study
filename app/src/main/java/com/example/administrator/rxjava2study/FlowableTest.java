package com.example.administrator.rxjava2study;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by dreamY on 2017/5/2.
 */

public class FlowableTest {
    public static void main(String[] args) {

        Flowable mFlowable= Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {

                e.onNext("hello");
                e.onNext("nuc");
                e.onNext("android");
                e.onNext("lab");

                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber mSubscriber=new Subscriber<String>() {
            Subscription mSubscription;
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscription");
                mSubscription=s;
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
                System.out.println("complete");
                mSubscription.cancel();

            }
        };

        mFlowable.subscribe(mSubscriber);



    }
}
