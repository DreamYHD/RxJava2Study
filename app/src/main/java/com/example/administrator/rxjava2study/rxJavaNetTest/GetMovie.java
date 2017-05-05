package com.example.administrator.rxjava2study.rxJavaNetTest;

import com.example.administrator.rxjava2study.rxjavaBaseTest.SchedulerUtils.SchedulerProvider;

import io.reactivex.Observable;

/**
 * Created by dreamY on 2017/5/5.
 */

public class GetMovie {
    private MovieRetrofitManager m=MovieRetrofitManager.getInstance();
    private SchedulerProvider s=SchedulerProvider.getInstance();

    public Observable<MovieBean>getTopMovie(){
        if (m == null) {
            throw new NullPointerException("初始化失败");
        }else {
            return m.getMovieService()
                    .getMovie(0,10)
                    .subscribeOn(s.io())
                    .observeOn(s.ui());
        }

    }
}
