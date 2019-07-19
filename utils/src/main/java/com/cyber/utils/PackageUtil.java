package com.cyber.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtil {
    public static String getVersion() {
        try {
            PackageInfo pi = ContextUtil.getApplicationContext().getPackageManager().getPackageInfo(ContextUtil.getApplicationContext().getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
