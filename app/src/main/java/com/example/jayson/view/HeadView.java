package com.example.jayson.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyber.utils.AndroidUtil;
import com.example.jayson.R;
import com.example.jayson.base.activity.BaseActivity;
import com.example.jayson.base.fragment.BaseFragment;

public class HeadView {
    private TextView mLeftTV, mMiddleTV, mRightTV;
    private ImageView mLeftIV, mMiddleIV, mRightIV;
    private LinearLayout mHeadLL, mParentLL;

    public HeadView(BaseActivity activity) {
        mParentLL = activity.findViewById(R.id.activity_base_parent);
        View headView = LayoutInflater.from(activity).inflate(R.layout.head, mParentLL, false);
        mParentLL.addView(headView, 0);
        init(headView);
    }

    public HeadView(BaseFragment fragment) {
        View rootView = fragment.getRootView();
        mParentLL = rootView.findViewById(R.id.fragment_base_parent);
        View headView = LayoutInflater.from(fragment.getBaseActivity()).inflate(R.layout.head, mParentLL, false);
        mParentLL.addView(headView, 0);
        init(headView);
    }

    public void setVisibility(int flag) {
        mHeadLL.setVisibility(flag);
    }

    private void init(View view) {
        mHeadLL = view.findViewById(R.id.head_ll);
        mLeftTV = view.findViewById(R.id.head_left_text);
        mMiddleTV = view.findViewById(R.id.head_middle_text);
        mRightTV = view.findViewById(R.id.head_right_text);
        mLeftIV = view.findViewById(R.id.head_left_image);
        mMiddleIV = view.findViewById(R.id.head_middle_image);
        mRightIV = view.findViewById(R.id.head_right_image);
    }

    public void setHead(String leftText, String middleText, String rightText, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        setHead(leftText, 0, middleText, 0, rightText, 0, leftListener, rightListener);

    }

    public void setHead(String leftText, String middleText, int rightDrawable, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        setHead(leftText, 0, middleText, 0, "", rightDrawable, leftListener, rightListener);

    }

    public void setHead(String leftText, int middleDrawable, String rightText, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        setHead(leftText, 0, "", middleDrawable, rightText, 0, leftListener, rightListener);

    }

    public void setHead(int leftDrawable, String middleText, String rightText, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        setHead("", leftDrawable, middleText, 0, rightText, 0, leftListener, rightListener);

    }

    public void setHead(int leftDrawable, String middleText, int rightDrawable, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        setHead("", leftDrawable, middleText, 0, "", rightDrawable, leftListener, rightListener);

    }

    public void setHead(String leftText, int leftDrawable, String middleText, int middleDrawable,
                        String rightText, int rightDrawable, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        if (!AndroidUtil.isEmpty(middleText) && middleDrawable == 0) {
            mMiddleIV.setVisibility(View.GONE);
            mMiddleTV.setVisibility(View.VISIBLE);
            mMiddleTV.setText(middleText);
        } else if (AndroidUtil.isEmpty(middleText) && middleDrawable != 0) {
            mMiddleIV.setVisibility(View.VISIBLE);
            mMiddleTV.setVisibility(View.GONE);
            mMiddleIV.setImageResource(middleDrawable);
        } else {
            mMiddleIV.setVisibility(View.GONE);
            mMiddleTV.setVisibility(View.GONE);
        }
        //如果一边又文字，另外一边没有文字，那么用INVISIBLE来控制空间的隐藏，使得两边看起来对称,其它情况也是如此
        if (!AndroidUtil.isEmpty(leftText) && AndroidUtil.isEmpty(rightText) && leftDrawable == 0 && rightDrawable == 0) {
            mLeftTV.setVisibility(View.VISIBLE);
            mRightTV.setVisibility(View.INVISIBLE);
            mLeftIV.setVisibility(View.GONE);
            mRightIV.setVisibility(View.GONE);
            mLeftTV.setText(leftText);
            mRightTV.setText(leftText);
            if (leftListener != null)
                mLeftTV.setOnClickListener(leftListener);
        } else if (AndroidUtil.isEmpty(leftText) && !AndroidUtil.isEmpty(rightText) && leftDrawable == 0 && rightDrawable == 0) {
            mLeftTV.setVisibility(View.INVISIBLE);
            mRightTV.setVisibility(View.VISIBLE);
            mLeftIV.setVisibility(View.GONE);
            mRightIV.setVisibility(View.GONE);
            mLeftTV.setText(rightText);
            mRightTV.setText(rightText);
            if (rightListener != null)
                mRightTV.setOnClickListener(rightListener);
        } else if (AndroidUtil.isEmpty(leftText) && AndroidUtil.isEmpty(rightText) && leftDrawable != 0 && rightDrawable == 0) {
            mLeftTV.setVisibility(View.GONE);
            mRightTV.setVisibility(View.GONE);
            mLeftIV.setVisibility(View.VISIBLE);
            mRightIV.setVisibility(View.INVISIBLE);
            mLeftIV.setImageResource(leftDrawable);
            mRightIV.setImageResource(leftDrawable);
            if (leftListener != null)
                mLeftIV.setOnClickListener(leftListener);
        } else if (AndroidUtil.isEmpty(leftText) && AndroidUtil.isEmpty(rightText) && leftDrawable == 0 && rightDrawable != 0) {
            mLeftTV.setVisibility(View.GONE);
            mRightTV.setVisibility(View.GONE);
            mLeftIV.setVisibility(View.INVISIBLE);
            mRightIV.setVisibility(View.VISIBLE);
            mLeftIV.setImageResource(rightDrawable);
            mRightIV.setImageResource(rightDrawable);
            if (rightListener != null)
                mRightIV.setOnClickListener(rightListener);
        } else if (!AndroidUtil.isEmpty(leftText) && !AndroidUtil.isEmpty(rightText) && leftDrawable == 0 && rightDrawable == 0) {
            mLeftTV.setVisibility(View.VISIBLE);
            mRightTV.setVisibility(View.VISIBLE);
            mLeftIV.setVisibility(View.GONE);
            mRightIV.setVisibility(View.GONE);
            mLeftTV.setText(leftText);
            mRightTV.setText(rightText);
            if (leftListener != null)
                mLeftTV.setOnClickListener(leftListener);
            if (rightListener != null)
                mRightTV.setOnClickListener(rightListener);
        } else if (!AndroidUtil.isEmpty(leftText) && AndroidUtil.isEmpty(rightText) && leftDrawable == 0 && rightDrawable != 0) {
            mLeftTV.setVisibility(View.VISIBLE);
            mRightTV.setVisibility(View.GONE);
            mLeftIV.setVisibility(View.GONE);
            mRightIV.setVisibility(View.VISIBLE);
            mLeftTV.setText(leftText);
            mRightIV.setImageResource(rightDrawable);
            if (leftListener != null)
                mLeftTV.setOnClickListener(leftListener);
            if (rightListener != null)
                mRightIV.setOnClickListener(rightListener);
        } else if (AndroidUtil.isEmpty(leftText) && !AndroidUtil.isEmpty(rightText) && leftDrawable != 0 && rightDrawable == 0) {
            mLeftTV.setVisibility(View.GONE);
            mRightTV.setVisibility(View.VISIBLE);
            mLeftIV.setVisibility(View.VISIBLE);
            mRightIV.setVisibility(View.GONE);
            mLeftIV.setImageResource(leftDrawable);
            mRightTV.setText(rightText);
            if (leftListener != null)
                mLeftIV.setOnClickListener(leftListener);
            if (rightListener != null)
                mRightTV.setOnClickListener(rightListener);
        } else if (AndroidUtil.isEmpty(leftText) && AndroidUtil.isEmpty(rightText) && leftDrawable != 0 && rightDrawable != 0) {
            mLeftTV.setVisibility(View.GONE);
            mRightTV.setVisibility(View.GONE);
            mLeftIV.setVisibility(View.VISIBLE);
            mRightIV.setVisibility(View.VISIBLE);
            mLeftIV.setImageResource(leftDrawable);
            mRightIV.setImageResource(rightDrawable);
            if (leftListener != null)
                mLeftIV.setOnClickListener(leftListener);
            if (rightListener != null)
                mRightIV.setOnClickListener(rightListener);
        } else {
            mLeftTV.setVisibility(View.GONE);
            mRightTV.setVisibility(View.GONE);
            mLeftIV.setVisibility(View.GONE);
            mRightIV.setVisibility(View.GONE);
        }
    }
}