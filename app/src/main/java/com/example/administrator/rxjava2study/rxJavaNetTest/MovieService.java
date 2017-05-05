package com.example.administrator.rxjava2study.rxJavaNetTest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dreamY on 2017/5/5.
 */

public interface MovieService {
    @GET("top250")
    Observable<MovieBean>getMovie(@Query("start")int start,@Query("count")int count);

}
