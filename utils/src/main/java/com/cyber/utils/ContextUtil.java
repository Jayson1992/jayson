package com.cyber.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class ContextUtil {

    private static Context ApplicationContext;

    public static Context getApplicationContext() {
        return ApplicationContext;
    }

    /* Public Methods */

    /**
     * 初始化context，如果由于不同机型导致反射获取context失败可以在Application调用此方法
     *
     * @param context
     */
    public static void init(Context context) {
        ApplicationContext = context;
    }

    public static Application getApplication() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            final Object obj = new Object();
            final Application[] res = {null};
            synchronized (obj) {
                try {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        res[0] = getApplication();
                        obj.notify();
                    });
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return res[0];
        }
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
