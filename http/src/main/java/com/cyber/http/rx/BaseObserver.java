package com.cyber.http.rx;


import android.util.Log;

import com.cyber.http.exception.ExceptionEngine;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 对于不习惯使用Map的同学，可以使用这个BaseObserver来做网络请求
 * 然后根据自己后台所返回正确的数据再过滤一下code，可以参考我写的HttpObserver
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        onError(ExceptionEngine.handleException(e));
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onError(Map<String, Object> errorMap);

    protected abstract void onResponse(T responseMap);

}