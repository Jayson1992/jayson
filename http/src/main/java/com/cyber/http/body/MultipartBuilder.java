package com.cyber.http.body;


import com.cyber.http.rx.FileUploadObserver;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultipartBuilder {
    /**
     * 单文件上传构造.
     *
     * @param file        文件
     * @param requestBody 请求体
     * @return MultipartBody
     */
    public static MultipartBody fileToMultipartBody(File file, RequestBody requestBody) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("file", file.getName(), requestBody);
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    /**
     * 多文件上传构造.
     *
     * @param files              文件列表
     * @param fileUploadObserver 文件上传回调
     * @return MultipartBody
     */
    public static MultipartBody filesToMultipartBody(List<File> files,
                                                     FileUploadObserver fileUploadObserver) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            UploadFileRequestBody uploadFileRequestBody =
                    new UploadFileRequestBody(file);
            builder.addFormDataPart("name", file.getName(), uploadFileRequestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
}