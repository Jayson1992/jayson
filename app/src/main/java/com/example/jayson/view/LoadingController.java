package com.example.jayson.view;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cyber.utils.ContextUtil;
import com.example.jayson.R;


/**
 * 整个项目的加载动画控制器
 */
public class LoadingController {
    private LayoutInflater inflater;
    private View loadingTargetView;
    private ViewGroup parentView;
    private int currentViewIndex;
    private ViewGroup.LayoutParams params;
    private AnimationDrawable loadingAnimationDrawable;
    private View loadingView;
    private View errorView;
    private OnClickListener onErrorRetryClickListener;

    public LoadingController(View loadingTargetView) {
        this.loadingTargetView = loadingTargetView;
        inflater = LayoutInflater.from(ContextUtil.getApplicationContext());
        init();
    }

    private void init() {
        params = loadingTargetView.getLayoutParams();
        if (loadingTargetView.getParent() != null) {
            parentView = (ViewGroup) loadingTargetView.getParent();
        } else {
            parentView = loadingTargetView.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int i = 0; i < count; i++) {
            if (loadingTargetView == parentView.getChildAt(i)) {
                currentViewIndex = i;
                break;
            }
        }
    }

    private void showView(View view) {
        if (parentView.getChildAt(currentViewIndex) != view) {
            parentView.removeViewAt(currentViewIndex);
            parentView.addView(view, currentViewIndex, params);
            if (loadingAnimationDrawable != null) {
                if (view == loadingView) {
                    loadingAnimationDrawable.start();
                } else {
                    loadingAnimationDrawable.stop();
                }
            }
        }
    }

    public void showLoading() {
        if (loadingView != null) {
            showView(loadingView);
            return;
        }
        loadingView = inflater.inflate(R.layout.loading, parentView, false);
        ImageView loadingIV = loadingView.findViewById(R.id.iv_loading);
        loadingAnimationDrawable = (AnimationDrawable) loadingIV.getDrawable();
        showView(loadingView);
    }

    public void showError() {
        if (errorView != null) {
            showView(errorView);
            return;
        }
        errorView = inflater.inflate(R.layout.error, parentView, false);
        Button retryBT = errorView.findViewById(R.id.error_retry);
        retryBT.setOnClickListener(v -> onErrorRetryClickListener.onClick());
        showView(errorView);
    }

    public void hideLoading() {
        showView(loadingTargetView);
    }

    public LoadingController setOnErrorRetryClickListener(OnClickListener onErrorRetryClickListener) {
        this.onErrorRetryClickListener = onErrorRetryClickListener;
        return this;
    }

    public interface OnClickListener {
        void onClick();
    }
}