package com.cyber.http.utils;


import com.cyber.http.body.MultipartBuilder;
import com.cyber.http.body.UploadFileRequestBody;
import com.cyber.http.client.ApiFactory;
import com.cyber.http.rx.FileUploadObserver;
import com.cyber.http.rx.RxSchedulerHelper;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;

public class UploadFileUtil {
    private static volatile UploadFileUtil uploadFileUtil;

    private UploadFileUtil() {

    }

    public static UploadFileUtil getInstance() {
        if (uploadFileUtil == null) {
            synchronized (HttpUtil.class) {
                if (uploadFileUtil == null) {
                    uploadFileUtil = new UploadFileUtil();
                }
            }
        }
        return uploadFileUtil;
    }

    /**
     * 单上传文件的封装.
     *
     * @param url  完整的接口地址
     * @param file 需要上传的文件
     */
    public Observable<Map<String, Object>> upLoadFile(String url, File file) {
        return ApiFactory
                .getInstance()
                .uploadFile(url, MultipartBuilder.fileToMultipartBody(file, new UploadFileRequestBody(file)))
                .compose(RxSchedulerHelper.Obs_io_main());
    }
}