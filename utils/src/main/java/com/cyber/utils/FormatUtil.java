package com.cyber.utils;

import java.text.DecimalFormat;

public class FormatUtil {
    public static String formatData(String money) {
        DecimalFormat df = new DecimalFormat("0.00");
        double cash;
        if (!AndroidUtil.isEmpty(money)) {
            cash = Double.valueOf(money);
        } else {
            cash = 0.00;
        }
        String format = df.format(cash);
        if (".00".equals(format))
            format = "0.00";
        return format;
    }
}
