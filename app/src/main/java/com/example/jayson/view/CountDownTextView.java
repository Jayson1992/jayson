package com.example.jayson.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Objects;

public class CountDownTextView extends AppCompatTextView {
    private long mCountDownMillis = 60_000;
    private long mLastMillis;
    private final int MSG_WHAT_START = 10_010;
    private int usableColorId = android.R.color.holo_blue_light;
    private int unusableColorId = android.R.color.darker_gray;


    public CountDownTextView(Context context) {
        super(context);
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_START:
                    if (mLastMillis > 0) {
                        setUsable(false);
                        long mIntervalMillis = 1_000;
                        mLastMillis -= mIntervalMillis;
                        mHandler.sendEmptyMessageDelayed(MSG_WHAT_START, mIntervalMillis);
                    } else {
                        setUsable(true);
                    }
                    break;
            }
            return true;
        }
    });

    public void setUsable(boolean usable) {
        String mHintText = "重新发送";
        if (usable) {
            //可用
            if (!isClickable()) {
                setClickable(usable);
                setTextColor(getResources().getColor(usableColorId));
                setText(mHintText);
            }
        } else {
            //不可用
            if (isClickable()) {
                setClickable(usable);
                setTextColor(getResources().getColor(unusableColorId));
            }
//            String mh = ContextUtil.getApplicationContext().getString(R.string.mh);
//            setText(mLastMillis / 1000 + mh + mHintText);
        }

    }

    public void setCountDownColor(@ColorRes int usableColorId, @ColorRes int unusableColorId) {
        this.usableColorId = usableColorId;
        this.unusableColorId = unusableColorId;
    }

    public void setCountDownMillis(long millis) {
        mCountDownMillis = millis;
    }

    public void start() {
        mLastMillis = mCountDownMillis;
        mHandler.sendEmptyMessage(MSG_WHAT_START);
    }

    public void reset() {
        mLastMillis = 0;
        mHandler.sendEmptyMessage(MSG_WHAT_START);
    }

    @Override
    public void setOnClickListener(@Nullable final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeMessages(MSG_WHAT_START);
                Objects.requireNonNull(onClickListener).onClick(v);
            }
        });

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(MSG_WHAT_START);
    }
}