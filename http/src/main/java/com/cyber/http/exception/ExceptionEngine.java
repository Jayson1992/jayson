package com.cyber.http.exception;

import android.util.MalformedJsonException;


import com.alibaba.fastjson.JSONObject;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * 错误/异常处理工具
 */
public class ExceptionEngine {

    private static final int UN_KNOWN_ERROR = 100;//未知错误
    private static final int ANALYTIC_SERVER_DATA_ERROR = 101;//解析(服务器)数据错误
    private static final int CONNECT_ERROR = 103;//网络连接错误
    private static final int TIME_OUT_ERROR = 104;//网络连接超时

    public static Map<String, Object> handleException(Throwable e) {
        Map<String, Object> resultMap = new HashMap<>();
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpExc = (HttpException) e;
            try {
                String str = Objects.requireNonNull(Objects.requireNonNull(httpExc.response()).errorBody()).string();
                resultMap = JSONObject.parseObject(str);
            } catch (Exception ignored) {
            }
            return resultMap;
        } else if (e instanceof com.alibaba.fastjson.JSONException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            resultMap.put("code", ANALYTIC_SERVER_DATA_ERROR);
            resultMap.put("message", "解析错误");
            return resultMap;
        } else if (e instanceof ConnectException || e instanceof SSLHandshakeException || e instanceof UnknownHostException) {//连接网络错误
            resultMap.put("code", CONNECT_ERROR);
            resultMap.put("message", "连接失败");
            return resultMap;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            resultMap.put("code", TIME_OUT_ERROR);
            resultMap.put("message", "网络超时");
            return resultMap;
        } else {  //未知错误
            resultMap.put("code", UN_KNOWN_ERROR);
            resultMap.put("message", "未知错误");
            return resultMap;
        }
    }
}
