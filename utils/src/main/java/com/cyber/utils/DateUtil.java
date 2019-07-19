package com.cyber.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {

    /**
     * 日期选择
     *
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static long showDatePickerDialog(final Activity activity, int themeResId, final TextView tv,
                                            Calendar calendar) {
        new DatePickerDialog(
                activity,
                themeResId,
                (view, year, monthOfYear, dayOfMonth) -> tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
        String time = tv.getText().toString();
        return stringTOMillisecond(time, "yyyy-MM-dd");
    }

    public static void showDatePickerDialog(final Activity activity, int themeResId, DatePickerDialog.OnDateSetListener listener) {
        new DatePickerDialog(
                activity,
                themeResId,
                listener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
    }

    public static String millisecondToDate(long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }

    public static String millisecondToDate(long millisecond, String pattern) {
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(date);
    }


    public static String getSystemTime(String pattern) {
        long system = System.currentTimeMillis();
        return millisecondToDate(system, pattern);
    }

    public static long stringTOMillisecond(String str, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
            Date date = format.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(date);
    }

    /**
     * 获取过去或者未来 任意天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static List<String> getFutureDays(int intervals) {
        List<String> futureDaysList = new ArrayList<>();
        for (int i = 0; i < intervals; i++) {
            futureDaysList.add(getFutureDate(i));
        }
        return futureDaysList;
    }

    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(today);
    }

    public static String getFutureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(today);
    }
}
