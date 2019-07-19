package com.example.jayson;

import android.app.Application;

import com.cyber.utils.ContextUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtil.init(this);
    }
}
