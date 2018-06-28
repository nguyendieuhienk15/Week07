package com.example.bookclean.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit != null){
            return retrofit;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
