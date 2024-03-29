package com.cyber.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Locale;

/**
 * 系统工具类
 */
public class SystemUtil {


    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取APP版本
     *
     * @return
     */
    public static String getVersionName() {
        PackageManager packageManager = ContextUtil.getApplicationContext().getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(ContextUtil.getApplicationContext().getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * @return
     */
    public static String getUserAgent() {
        String userAgent = "";
//        手机型号
        String systemModel = SystemUtil.getSystemModel();
//        系统版本
        String systemVersion = SystemUtil.getSystemVersion();
        String deviceBrand = SystemUtil.getDeviceBrand();
        userAgent = "Android/" + deviceBrand + "/" + systemModel + "/" + systemVersion;
        return userAgent;
    }

}
