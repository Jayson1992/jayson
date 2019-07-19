package com.cyber.http.rx;


import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulerHelper {
    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FloTransformer
     */
    public static <T> FlowableTransformer<T, T> Flo_io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObsTransformer
     */
    public static <T> ObservableTransformer<T, T> Obs_io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
