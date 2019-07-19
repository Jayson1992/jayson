package com.example.jayson.tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.cyber.utils.ContextUtil;
import com.example.jayson.R;

import java.util.Objects;

public class CopyTool {
    public static void copy(String content) {
        ClipboardManager cmb = (ClipboardManager) ContextUtil.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("content", content);
        Objects.requireNonNull(cmb).setPrimaryClip(mClipData);
        ToastUtil.showInfo(R.string.wbfzcg);
    }
}
