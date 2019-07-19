package com.example.jayson.activity.second;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.jayson.R;
import com.example.jayson.base.activity.BaseActivity;
import com.example.jayson.base.presenter.BasePresenter;
import com.example.jayson.base.view.BaseView;
import com.example.jayson.tools.SerializableMapTool;
import com.example.jayson.tools.ToastUtil;
import com.example.jayson.view.HeadView;

public class SecondActivity extends BaseActivity {
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        new HeadView(this).setHead(android.R.drawable.ic_delete, "第二页", android.R.drawable.ic_input_add, v -> {
            finishAfterTransition();
        }, v -> {
            ToastUtil.showInfo("点击了第二页的右边");
        });
        ToastUtil.showInfo(SerializableMapTool.getIntentMap(getIntent(), "item") + "");
    }
}
