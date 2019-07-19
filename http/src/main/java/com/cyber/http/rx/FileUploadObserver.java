package com.cyber.http.rx;


import com.cyber.http.exception.ExceptionEngine;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class FileUploadObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        onBefore();
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
        onAfter();
    }

    public abstract void onBefore();

    public abstract void onResponse(T t);

    public abstract void onError(Map<String, Object> errorMap);

    public abstract void onAfter();
}