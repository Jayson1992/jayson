package com.example.jayson.fragment.one;

import com.example.jayson.base.presenter.BasePresenter;
import com.example.jayson.net.MapObserver;

import java.util.Map;

class OnePresenter extends BasePresenter<IOneView> {
    private OneModel oneModel;

    OnePresenter() {
        oneModel = new OneModel();
    }

    void getArticleList() {
        oneModel.getArticleList()
                .as(mView.bindAutoDispose())
                .subscribe(new MapObserver() {
                    @Override
                    public void onBefore() {
                        mView.showLoading(0);
                    }

                    @Override
                    public void onResult(Map<String, Object> resultMap) {
                        mView.hideLoading(0);
                        mView.showNoticeList(resultMap);
                        mView.showMsg(resultMap+"");
                    }

                    @Override
                    protected void onError(Map<String, Object> errorMap) {
                        mView.showError();
                        mView.showMsg(errorMap.get("msg") + "");
                    }
                });
    }
}
