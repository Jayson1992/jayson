package com.cyber.http.client;

import android.util.Log;
import android.webkit.CookieManager;

import androidx.annotation.NonNull;

import com.cyber.utils.SystemUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpFactory {
    private static final int TIMEOUT_READ = 10;
    private static final int TIMEOUT_CONNECTION = 10;
    private static volatile OkHttpClient okHttpClient;

    private OKHttpFactory() {
    }

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OKHttpFactory.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .cookieJar(new CookieJar() {
                                @Override
                                public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
                                    CookieManager cookieManager = CookieManager.getInstance();
                                    for (Cookie cookie : cookies) {
                                        cookieManager.setCookie(url.toString(), cookie.toString());
                                    }
                                }

                                @NonNull
                                public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
                                    CookieManager cookieManager = CookieManager.getInstance();
                                    List<Cookie> cookies = new ArrayList<>();
                                    if (cookieManager.getCookie(url.toString()) != null) {
                                        String[] splitCookies = cookieManager.getCookie(url.toString()).split("[,;]");
                                        for (String splitCookie : splitCookies) {
                                            cookies.add(Cookie.parse(url, splitCookie.trim()));
                                        }
                                    }
                                    return cookies;
                                }
                            })
                            .addInterceptor(new HeaderInterceptor())
                            .addInterceptor(new HttpLoggingInterceptor(
                                    message -> {
//                                        Log.i("cj", message);
                                    })
                                    .setLevel(HttpLoggingInterceptor.Level.HEADERS))
                            //失败重连
                            .retryOnConnectionFailure(true)
                            //time out
                            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }


    public static class HeaderInterceptor implements Interceptor {
        @NonNull
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", SystemUtil.getUserAgent())
                    .addHeader("X-Requested-With", "XMLHttpRequest")
                    .addHeader("X-Agent", "android" + SystemUtil.getSystemVersion())
                    .addHeader("X-Version", SystemUtil.getVersionName())
                    .addHeader("X-Device", SystemUtil.getSystemModel());
            return chain.proceed(requestBuilder.build());
        }
    }
}
