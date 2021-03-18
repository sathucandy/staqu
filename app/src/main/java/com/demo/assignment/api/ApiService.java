package com.demo.assignment.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private Retrofit retrofit;

    public ApiService() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder().build())
                .baseUrl("https://api.stackexchange.com/2.2/questions/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <S> S getApi(Class<S> cls) {
        return retrofit.create(cls);
    }

}
