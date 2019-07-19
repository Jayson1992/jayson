package com.example.jayson.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jayson.R;
import com.example.jayson.base.activity.BaseActivity;
import com.example.jayson.base.presenter.BasePresenter;
import com.example.jayson.base.view.BaseView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;


public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment {

    //引用V层和P层
    protected P mPresenter;
    private V mView;
    private View rootView;
    protected LinearLayout mParentLL;
    protected BaseActivity activity;
    private boolean isVisible, isFirstVisible, isStartActivity;
    protected boolean isHaveOweHead;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
        isFirstVisible = true;
        isHaveOweHead=setIsHaveOweHead();
    }
    public View getRootView() {
        return rootView;
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        if (null == mView) {
            mView = createView();
        }
        if (mPresenter != null && mView != null) {
            mPresenter.attachView(mView);
        }
        if (null == rootView) {
            if(isHaveOweHead){
                rootView=inflater.inflate(setLayoutResId(),container,false);
            }else {
                rootView = inflater.inflate(R.layout.fragment_base, container, false);
                mParentLL = rootView.findViewById(R.id.fragment_base_parent);
                init(LayoutInflater.from(activity).inflate(setLayoutResId(), mParentLL, true));
            }
            if (isVisible) {
                onLoadData();
                isFirstVisible = false;
            }
        }
        return rootView;
    }
    protected abstract boolean setIsHaveOweHead();

    public abstract int setLayoutResId();

    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init(View view);

    public abstract void onLoadData();

    public abstract void onActivityRestart();

    public abstract void onViewPagerSwitchBack();

    @Override
    public void onStart() {
        super.onStart();
        if (isStartActivity && isVisible) {
            onActivityRestart();
            isStartActivity = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isStartActivity = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (null == rootView) {
            return;
        }
        if (isVisible && !isFirstVisible) {
            onViewPagerSwitchBack();
        }
        if (isFirstVisible && isVisible) {
            onLoadData();
            isFirstVisible = false;
        }
    }

    public <K> AutoDisposeConverter<K> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}