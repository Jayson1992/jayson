package com.cyber.http.manager;


import android.util.ArrayMap;

import java.util.Objects;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Http请求管理实现类
 */
public class RequestManagerImpl implements RequestManager<Object> {

    private static volatile RequestManagerImpl mInstance;
    /**
     * 带tag的网络请求列表
     */
    private ArrayMap<Object, Disposable> mMaps;
    /**
     * 不带tag的网络请求列表
     */
    private CompositeDisposable mDisposables;

    public static RequestManagerImpl getInstance() {
        if (mInstance == null) {
            synchronized (RequestManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new RequestManagerImpl();
                }
            }
        }
        return mInstance;
    }

    private RequestManagerImpl() {
        if (null == mMaps)
            mMaps = new ArrayMap<>();
        if (null == mDisposables)
            mDisposables = new CompositeDisposable();
    }

    @Override
    public void add(Object tag, Disposable disposable) {
        mMaps.put(tag, disposable);
    }

    @Override
    public void add(Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    public void remove(Object tag) {
        if (!mMaps.isEmpty()) {
            mMaps.remove(tag);
        }
    }

    @Override
    public void cancel(Object tag) {
        if (mMaps.isEmpty()) {
            return;
        }
        if (mMaps.get(tag) == null) {
            return;
        }
        if (!Objects.requireNonNull(mMaps.get(tag)).isDisposed()) {
            Objects.requireNonNull(mMaps.get(tag)).dispose();
        }
        mMaps.remove(tag);
    }

    @Override
    public void cancelAll() {
        if (mMaps.isEmpty()) {
            return;
        }
        //遍历取消请求
        Disposable disposable;
        Set<Object> keySet = mMaps.keySet();
        for (Object key : keySet) {
            disposable = mMaps.get(key);
            if (!Objects.requireNonNull(disposable).isDisposed()) {
                disposable.dispose();
            }
        }
        mMaps.clear();
        if (mDisposables.size() > 0)
            mDisposables.clear();
    }


    /**
     * 判断是否取消了请求
     *
     * @param tag
     * @return
     */
    public boolean isDisposed(Object tag) {
        if (mMaps.isEmpty() || mMaps.get(tag) == null) return true;
        return Objects.requireNonNull(mMaps.get(tag)).isDisposed();
    }
}