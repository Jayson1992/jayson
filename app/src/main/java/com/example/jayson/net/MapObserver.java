package com.example.jayson.net;


import android.util.Log;

import com.cyber.http.rx.BaseObserver;
import com.cyber.utils.ContextUtil;
import com.example.jayson.R;
import com.example.jayson.data.Constant;
import com.example.jayson.tools.ToastUtil;

import java.util.Map;

import io.reactivex.disposables.Disposable;

public abstract class MapObserver extends BaseObserver<Map<String, Object>> {
    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        onBefore();
    }

    @Override
    public void onResponse(Map<String, Object> responseMap) {
        if (null == responseMap) {
            ToastUtil.showInfo(ContextUtil.getApplicationContext().getString(R.string.return_null));
        } else {
//            int code = (int) responseMap.get("code");
//            if (0 == code) {
//                onResult(responseMap);
//            } else {
//                String msg = responseMap.get("msg") + "";
//                if ("token已失效,请重新登录!".equals(msg)) {
//                    Constant.token = "";
//                }
//            }
//            Map<String, Object> data = (Map<String, Object>) responseMap.get("data");

            if (responseMap.get("state") == null) {
                onResult(responseMap);
                return;
            }
            boolean state = (boolean) responseMap.get("state");
            if (state) {
                onResult(responseMap);
            } else {
                String msg = responseMap.get("message") + "";
//                if ("login".equals(msg)) {
//                    Constant.userInfo.clear();
//                    Constant.reset();
//                    FinishActivityManager.getInstance().needLogin();
//                    return;
//                }
                onError(responseMap);
            }
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onBefore();

    public abstract void onResult(Map<String, Object> resultMap);
}