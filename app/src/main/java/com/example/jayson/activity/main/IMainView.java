package com.example.jayson.activity.main;

import com.example.jayson.base.view.BaseView;
import com.uber.autodispose.AutoDisposeConverter;

interface IMainView extends BaseView {
    void showLoading(int loadingType);

    void hideLoading(int loadingType);

    void showMsg(String msg);

    void showError();

    <T> AutoDisposeConverter<T> bindAutoDispose();
}
