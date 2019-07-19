package com.cyber.http.manager;

import io.reactivex.disposables.Disposable;

/**
 * Http请求管理接口
 */
public interface RequestManager<T> {
    /**
     * 根据TAG添加disposable
     *
     * @param tag        哪一个disposable
     * @param disposable disposable对象
     */
    void add(T tag, Disposable disposable);

    /**
     * 不根据tag添加disposable
     *
     * @param disposable
     */
    void add(Disposable disposable);

    /**
     * 移除
     *
     * @param tag 根据tag移除请求
     */
    void remove(T tag);

    /**
     * 取消 根据tag取消请求
     *
     * @param tag
     */
    void cancel(T tag);

    /**
     * 取消全部
     */
    void cancelAll();

}