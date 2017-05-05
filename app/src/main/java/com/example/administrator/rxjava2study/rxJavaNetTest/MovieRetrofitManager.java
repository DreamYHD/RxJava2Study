package com.example.administrator.rxjava2study.rxJavaNetTest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dreamY on 2017/5/5.
 */

public class MovieRetrofitManager {
    private static final String BASE_URL="https://api.douban.com/v2/movie/";
    private static int DEFAULT_TIMEOUT=5;


    private static final String TAG = "MovieRetrofitManager";
    private Retrofit mRetrofit;
    private MovieService mMovieService;

    private MovieRetrofitManager(){

        OkHttpClient.Builder mBuilder=new OkHttpClient.Builder();
        mBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit=new Retrofit.Builder()
                .client(mBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mMovieService=mRetrofit.create(MovieService.class);
    }
    private static class Single{
        private static final MovieRetrofitManager INSTANCE=new MovieRetrofitManager();
    }
    public static MovieRetrofitManager getInstance(){
        return Single.INSTANCE;
    }
    public MovieService getMovieService(){
        return mMovieService;
    }






}
