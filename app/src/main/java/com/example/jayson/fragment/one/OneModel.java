package com.example.jayson.fragment.one;

import com.cyber.http.utils.HttpUtil;
import com.example.jayson.net.API;

import java.util.Map;

import io.reactivex.Observable;

class OneModel {
    Observable<Map<String, Object>> getArticleList() {
        return HttpUtil.getInstance().startAsyncGetRequest(API.ARTICLE_LIST);
    }
}
