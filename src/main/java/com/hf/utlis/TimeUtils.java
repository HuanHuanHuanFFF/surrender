package com.hf.utlis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateFormat(Date date) {
        return "CST:" + format.format(date);
    }

    public static Date getCurrentTime() {
        return new Date();
    }
}