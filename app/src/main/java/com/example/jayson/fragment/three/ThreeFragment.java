package com.example.jayson.fragment.three;

import android.view.View;

import com.example.jayson.R;
import com.example.jayson.base.fragment.BaseFragment;
import com.example.jayson.base.presenter.BasePresenter;
import com.example.jayson.base.view.BaseView;
import com.example.jayson.tools.ToastUtil;
import com.example.jayson.view.HeadView;

public class ThreeFragment extends BaseFragment implements IThreeView {
    @Override
    protected boolean setIsHaveOweHead() {
        return false;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_three;
    }

    @Override
    public BasePresenter createPresenter() {
        return new ThreePresenter();
    }

    @Override
    public BaseView createView() {
        return this;
    }

    @Override
    public void init(View view) {
        new HeadView(this)
                .setHead(android.R.drawable.ic_secure, "优惠", "右边", v -> ToastUtil.showInfo("我跳跳跳"), v -> ToastUtil.showInfo("天秀"));
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
