package com.cyber.http.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST
    Observable<Map<String, Object>> post(@Url String url, @FieldMap Map<String, Object> paramMap);

    @GET
    Observable<Map<String, Object>> get(@Url String url, @QueryMap Map<String, Object> paramMap);

    @GET
    Observable<Map<String, Object>> get(@Url String url);

    @POST
    Observable<Map<String, Object>> uploadFile(@Url String url, @Body MultipartBody body);
}
