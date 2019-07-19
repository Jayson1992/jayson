package com.example.jayson.fragment.one;

import android.content.Intent;
import android.view.View;

import com.example.jayson.R;
import com.example.jayson.activity.second.SecondActivity;
import com.example.jayson.base.fragment.BaseFragment;
import com.example.jayson.tools.SerializableMapTool;
import com.example.jayson.tools.ToastUtil;
import com.example.jayson.view.HeadView;
import com.example.jayson.view.LoadingController;

import java.util.Map;

public class OneFragment extends BaseFragment<IOneView, OnePresenter> implements IOneView {
    private LoadingController loadingController;
    private Map<String, Object> noticeMap;

    @Override
    protected boolean setIsHaveOweHead() {
        return false;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_one;
    }

    @Override
    public OnePresenter createPresenter() {
        return new OnePresenter();
    }

    @Override
    public IOneView createView() {
        return this;
    }

    @Override
    public void init(View view) {
        new HeadView(this).setHead("左边", "主页", "跳转", v -> ToastUtil.showInfo("点击了主页的左边"), v -> {
            Intent intent = new Intent(activity, SecondActivity.class);
            intent.putExtra("item", SerializableMapTool.setBundleMap(noticeMap));
            startActivity(intent);
        });
        loadingController = new LoadingController(mParentLL.getChildAt(1)).setOnErrorRetryClickListener(this::onLoadData);
    }

    @Override
    public void onLoadData() {
        mPresenter.getArticleList();
    }

    @Override
    public void onActivityRestart() {

    }

    @Override
    public void onViewPagerSwitchBack() {

    }

    @Override
    public void showLoading(int loadingType) {
        loadingController.showLoading();
    }

    @Override
    public void hideLoading(int loadingType) {
        loadingController.hideLoading();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.showInfo(msg);
    }

    @Override
    public void showError() {
        loadingController.showError();
    }

    @Override
    public void showNoticeList(Map<String, Object> map) {
        noticeMap = map;
    }
}
