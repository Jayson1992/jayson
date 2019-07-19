package com.cyber.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description：
 * Created by kang on 2018/3/9.
 */

public class FileUtils {
    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(final File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回 true，是目录则返回 false
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(final File file) {
        // 如果存在，是目录则返回 true，是文件则返回 false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(final String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getDefaultDirectory(Context context) {
        String path;
        if (TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())
                && context.getExternalCacheDir() != null) {
            path = context.getExternalCacheDir().getAbsolutePath() + File.separator;
        } else {
            path = context.getCacheDir().getAbsolutePath() + File.separator;
        }
        File dir = new File(path).getParentFile();
        if (dir.exists() || dir.mkdirs()) {
            return path;
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    public static void deleteApkFile(String url) {
        //Apk文件路径可自定义，此处用 download 文件夹
        String[] split = url.split("/");
        String fileName = split[split.length - 1];
        File apkFile = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getPath(), fileName);
        apkFile.delete();
    }

    public static String getJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = ContextUtil.getApplicationContext().getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
//    public static MultipartBody filesToMultipartBody(List<File> files) {
//        MultipartBody.Builder builder = new MultipartBody.Builder();
//        for (File file : files) {
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
//            builder.addFormDataPart("file", file.getName(), requestBody);
//        }
//        builder.setType(MultipartBody.FORM);
//        MultipartBody multipartBody = builder.build();
//        return multipartBody;
//    }
}
