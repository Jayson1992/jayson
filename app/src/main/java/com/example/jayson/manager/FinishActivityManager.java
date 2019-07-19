package com.example.jayson.manager;

import android.app.Activity;
import android.content.Intent;


import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

public class FinishActivityManager {
    private FinishActivityManager() {
    }

    private static volatile FinishActivityManager activityManager;
    private Stack<WeakReference<Activity>> mActivityStack;

    public static FinishActivityManager getInstance() {
        if (activityManager == null) {
            synchronized (FinishActivityManager.class) {
                if (activityManager == null) {
                    activityManager = new FinishActivityManager();
                }
            }
        }
        return activityManager;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(new WeakReference<>(activity));
    }

    /**
     * 检查弱引用是否释放，若释放，则从栈中清理掉该元素
     */
    public void checkWeakReference() {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity temp = activityReference.get();
                if (temp == null) {
                    it.remove();
                }
            }
        }
    }

    /**
     * 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        checkWeakReference();
        if (mActivityStack != null && !mActivityStack.isEmpty()) {
            return mActivityStack.lastElement().get();
        }
        return null;
    }

    /**
     * 关闭当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        if (activity != null) {
            finishActivity(activity);
        }
    }

    /**
     * 获取制定类名的activity
     */
    public Activity getActivity(Class<?> cls) {
        if (mActivityStack != null) {
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity activity = activityReference.get();
                if (activity == null) {
                    it.remove();
                    continue;
                }
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 关闭指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity temp = activityReference.get();
                // 清理掉已经释放的activity
                if (temp == null) {
                    it.remove();
                    continue;
                }
                if (temp == activity) {
                    it.remove();
                }
            }
            activity.finishAfterTransition();
        }
    }

    /**
     * 关闭指定类名的所有Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity activity = activityReference.get();
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove();
                    continue;
                }
                if (activity.getClass().equals(cls)) {
                    it.remove();
                    activity.finishAfterTransition();
                }
            }
        }
    }

    public void finishWithoutMainActivity() {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            for (Iterator<WeakReference<Activity>> it = mActivityStack.iterator(); it.hasNext(); ) {
                WeakReference<Activity> activityReference = it.next();
                Activity activity = activityReference.get();
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove();
                    continue;
                }
//                if (!(activity instanceof MainActivity)) {
//                    it.remove();
//                    activity.finishAfterTransition();
//                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mActivityStack != null) {
            for (WeakReference<Activity> activityReference : mActivityStack) {
                Activity activity = activityReference.get();
                if (activity != null) {
                    activity.finishAfterTransition();
                }
            }
            mActivityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            // 退出JVM,释放所占内存资源,0表示正常退出
            System.exit(0);
            // 从系统中kill掉应用程序
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void needLogin() {
//        checkWeakReference();
//        if (mActivityStack != null && !mActivityStack.isEmpty()) {
//            WeakReference<Activity> lastElement = mActivityStack.lastElement();
//            Activity last = lastElement.get();
//            if (last.getClass().equals(LoginActivity.class)) {
//                return;
//            }
//            for (int i = 0; i < mActivityStack.size(); i++) {
//                WeakReference<Activity> element = mActivityStack.get(i);
//                Activity activity = element.get();
//                if (!activity.getClass().equals(MainActivity.class)) {
//                    activity.finishAfterTransition();
//                    mActivityStack.remove(element);
//                }
//            }
//            WeakReference<Activity> weakReference = mActivityStack.get(0);
//            Activity activity = weakReference.get();
//            activity.startActivity(new Intent(activity, LoginActivity.class));
//        }
//    }
}
