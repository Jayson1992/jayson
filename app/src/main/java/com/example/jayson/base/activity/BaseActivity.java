package com.example.jayson.base.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jayson.R;
import com.example.jayson.base.presenter.BasePresenter;
import com.example.jayson.base.view.BaseView;
import com.example.jayson.manager.FinishActivityManager;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;


public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity {
    protected P mPresenter;
    protected V mView;
    protected LinearLayout mParentLL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinishActivityManager.getInstance().addActivity(this);
        if (null == mPresenter) {
            mPresenter = createPresenter();
        }
        if (null == mView) {
            mView = createView();
        }
        if (mPresenter != null && mView != null) {
            mPresenter.attachView(mView);
        }
    }
    public void setContentView(int layoutResID){
        super.setContentView(R.layout.activity_base);
        mParentLL = findViewById(R.id.activity_base_parent);
        LayoutInflater.from(this).inflate(layoutResID, mParentLL, true);
    }
    public void setContentView(int layoutResID,boolean isHaveOweHead){
        if(isHaveOweHead){
            super.setContentView(layoutResID);
        }else{
            setContentView(layoutResID);
        }
    }


    public abstract P createPresenter();

    public abstract V createView();

    public <K> AutoDisposeConverter<K> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        FinishActivityManager.getInstance().finishActivity(this);
    }
}