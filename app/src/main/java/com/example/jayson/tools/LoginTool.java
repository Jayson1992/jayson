//package com.example.jayson.tools;
//
//import android.app.Activity;
//import android.content.Intent;
//
//import com.cyber.uwin.R;
//import com.cyber.uwin.activity.login.LoginActivity;
//import com.cyber.uwin.data.Constant;
//import com.cyber.uwin.manager.FinishActivityManager;
//
//public class LoginTool {
//    public static boolean isLogin() {
//        return Constant.userInfo.size() > 0;
//    }
//
//    public static boolean isJumpToLogin(Activity activity) {
//        boolean isJump = false;
//        if (!isLogin()) {
//            ToastUtil.showInfo(R.string.qcxdl);
//            FinishActivityManager.getInstance().finishWithoutMainActivity();
//            activity.startActivity(new Intent(activity, LoginActivity.class));
//            isJump = true;
//        }
//        return isJump;
//    }
//}
