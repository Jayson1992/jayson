package com.example.jayson.fragment.one;

import com.example.jayson.base.view.BaseView;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.Map;

interface IOneView extends BaseView {

    void showLoading(int loadingType);

    void hideLoading(int loadingType);

    void showMsg(String msg);

    void showError();
    
    <T> AutoDisposeConverter<T> bindAutoDispose();

    void showNoticeList(Map<String,Object> map);

}
