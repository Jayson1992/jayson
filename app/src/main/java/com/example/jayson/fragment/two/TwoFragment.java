package com.example.jayson.fragment.two;

import android.view.View;

import com.example.jayson.R;
import com.example.jayson.base.fragment.BaseFragment;
import com.example.jayson.base.presenter.BasePresenter;
import com.example.jayson.base.view.BaseView;
import com.example.jayson.tools.ToastUtil;

public class TwoFragment extends BaseFragment implements ITwoView {
    @Override
    protected boolean setIsHaveOweHead() {
        return true;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_two;
    }

    @Override
    public BasePresenter createPresenter() {
        return new TwoPresenter();
    }

    @Override
    public BaseView createView() {
        return this;
    }

    @Override
    public void init(View view) {
        view.findViewById(R.id.fragment_two).setOnClickListener(v -> ToastUtil.showInfo("点击了一下"));
    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void onActivityRestart() {

    }

    @Override
    public void onViewPagerSwitchBack() {

    }
}
