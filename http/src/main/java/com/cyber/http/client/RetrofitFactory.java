package com.cyber.http.client;


import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitFactory {
    private static volatile Retrofit retrofit;

    private RetrofitFactory() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.baidu.com")
                            .client(OKHttpFactory.getInstance())
                            .addConverterFactory(Retrofit2ConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}