package com.cyber.http.utils;

import com.cyber.http.client.ApiFactory;
import com.cyber.http.rx.RxSchedulerHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class HttpUtil {
    private static volatile HttpUtil httpUtil;

    private HttpUtil() {

    }

    public static HttpUtil getInstance() {
        if (httpUtil == null) {
            synchronized (HttpUtil.class) {
                if (httpUtil == null) {
                    httpUtil = new HttpUtil();
                }
            }
        }
        return httpUtil;
    }

    public Observable<Map<String, Object>> startAsyncPostRequest(String url, Map<String, Object> paramMap) {
        return ApiFactory
                .getInstance()
                .post(url, paramMap)
                .compose(RxSchedulerHelper.Obs_io_main());
    }

    public Observable<Map<String, Object>> startAsyncGetRequest(String url, Map<String, Object> paramMap) {
        return ApiFactory
                .getInstance()
                .get(url, paramMap)
                .compose(RxSchedulerHelper.Obs_io_main());
    }

    public Observable<Map<String, Object>> startAsyncGetRequest(String url) {
        return ApiFactory
                .getInstance()
                .get(url)
                .compose(RxSchedulerHelper.Obs_io_main());
    }
}