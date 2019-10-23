package com.boman.upms.common.constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateKit {

    public DateKit() {

    }

    public static Date nowOfDate() {
        return new Date();
    }

    public static String format(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 以yyyy-MM-dd HH:mm:ss 格式化时间
     */
    public static String now() {

        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间
     * @param format
     */
    public static String now(String format) {

        return format(new Date(), format);
    }

    /**
     * 获取星期
     * @param date
     */
    public static String getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(7);
        String wk = "";
        switch(week) {
            case 1:
                wk = "星期天";
                break;
            case 2:
                wk = "星期一";
                break;
            case 3:
                wk = "星期二";
                break;
            case 4:
                wk = "星期三";
                break;
            case 5:
                wk = "星期四";
                break;
            case 6:
                wk = "星期五";
                break;
            case 7:
                wk = "星期六";
        }
        return wk;
    }
}
