package com.example.jayson.tools;

import android.widget.Toast;

import com.cyber.utils.ContextUtil;
import com.example.jayson.R;


public class ToastUtil {
    public static void showInfo(String info) {
        if(info.equals("login")){
            info= ContextUtil.getApplicationContext().getString(R.string.qcxdl);
        }
        Toast.makeText(ContextUtil.getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }

    public static void showInfo(int res_id) {
        Toast.makeText(ContextUtil.getApplicationContext(), res_id, Toast.LENGTH_SHORT).show();
    }
}
