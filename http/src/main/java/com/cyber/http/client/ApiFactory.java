package com.cyber.http.client;


import com.cyber.http.api.RetrofitAPI;

public class ApiFactory {
    private static volatile RetrofitAPI retrofitAPI;

    private ApiFactory() {

    }

    public static RetrofitAPI getInstance() {
        if (retrofitAPI == null) {
            synchronized (ApiFactory.class) {
                if (retrofitAPI == null) {
                    retrofitAPI = RetrofitFactory.getInstance().create(RetrofitAPI.class);
                }
            }
        }
        return retrofitAPI;
    }
}
