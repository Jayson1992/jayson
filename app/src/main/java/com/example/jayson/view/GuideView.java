package com.example.jayson.view;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.cyber.utils.DisplayUtil;


public class GuideView {
    private ImageView imageView;
    private ViewGroup content;
    private int clickCount = 0;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(@Nullable android.os.Message msg) {
            final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
            params.format = PixelFormat.RGBA_8888;
            params.gravity = Gravity.START | Gravity.BOTTOM;
            params.width = DisplayUtil.getScreenWidth();
            params.height = DisplayUtil.getScreenHeight();
            content.addView(imageView, params);
        }
    };


    public void initGuide(Activity activity, final int[] img) {
        content = activity.findViewById(android.R.id.content);
        imageView = new ImageView(activity);
        imageView.setLayoutParams(new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(img[clickCount]);
        handler.sendEmptyMessage(1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCount++;
                if (clickCount < img.length) {
                    imageView.setImageResource(img[clickCount]);
                }
                if (clickCount == img.length) {
                    content.removeView(imageView);
                }
            }
        });
    }


}