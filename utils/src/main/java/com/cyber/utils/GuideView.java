package com.cyber.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class GuideView {
    private ImageView imgView;
    private ViewGroup content;
    private int i = 0;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(android.os.Message msg) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
            params.format = PixelFormat.RGBA_8888;
            params.gravity = Gravity.START | Gravity.BOTTOM;
            params.width = DisplayUtil.getScreenWidth();
            params.height = DisplayUtil.getScreenHeight();
            content.addView(imgView, params);
        }
    };


    public void initGuide(Activity activity, int[] img, boolean isFirst) {
        if (!isFirst) {
            return;
        }
        content = activity.findViewById(android.R.id.content);
        imgView = new ImageView(activity);
        imgView.setLayoutParams(new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        imgView.setImageResource(img[0]);
        handler.sendEmptyMessage(1);


        imgView.setOnClickListener(arg0 -> {
            if (i < img.length) {
                imgView.setImageResource(img[i]);
            }
            if (i < img.length) {
                i++;
            } else if (i == img.length) {
                i = 0;
                content.removeView(imgView);
            }
        });
    }


}