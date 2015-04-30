package org.luapp.core.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

public class FormatUtil {

    private static String[] SIZE_UNITS = { "B", "KB", "MB", "GB", "TB", "PB" };

    private static final ThreadLocal<DateFormat[]> defaultDateFormat = new ThreadLocal<DateFormat[]>() {

        @Override
        protected DateFormat[] initialValue() {
            DateFormat[] dateFormats = new DateFormat[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                    new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz") };

            return dateFormats;
        }
    };

    /**
     * 中文格式的货币表示
     * 
     * @param num
     * @return
     */
    public static String convertNumToMoney(int num) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);

        String moneyStr = nf.format(num);

        return moneyStr;
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {

        if (date == null) {
            return "";
        }

        return defaultDateFormat.get()[0].format(date);
    }

    /**
     * 获取文件的大小，1KB,2MB字符形式显示
     * 
     * @param size
     * @return
     */
    public static String getSize(long size) {
        int i = 0;

        while (size > 1024) {
            size = size / 1024;

            if (i >= SIZE_UNITS.length - 1) {
                break;
            }
            i++;
        }

        return Math.round(size * 100) / 100 + SIZE_UNITS[i];
    }

    /**
     * 友好的显示时间，比如  2小时前  发布
     * 
     * @param time
     * @return
     */
    public static String friendlyTime(Date time) {

        if (time == null) {
            return "未知日期";
        }
        // 获取距当前时间秒数 
        long ct = (System.currentTimeMillis() - time.getTime()) / 1000;

        if (ct < 60) {
            return "刚刚";
        }

        if (ct < 3600) {
            return ct / 60 + "分钟前";
        }

        if (ct >= 3600 && ct < 86400) {
            return Math.max(ct / 3600, 1) + "小时前";
        }

        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = (int) (ct / 86400);
            return (day > 1) ? day + "天前" : "昨天";
        }
        if (ct >= 2592000 && ct < 31104000) //86400 * 30
            return ct / 2592000 + "月前";
        return ct / 31104000 + "年前";
    }

    public static void main(String[] args) {
        System.out.println(convertNumToMoney(15));
        System.out.println(formatDate(new Date()));

        System.out.println(getSize(1023));
        System.out.println(getSize(1023 * 1024));
        System.out.println(getSize(1023 * 1024 * 1024));
        System.out.println(getSize(1023L * 1024 * 1024 * 1024));
        System.out.println(getSize(1023L * 1024 * 1024 * 1024 * 1024));
        System.out.println(getSize(2L * 1024 * 1024 * 1024 * 1023 * 1023));

        System.out.println(friendlyTime(new DateTime().minusMinutes(1).toDate()));
        System.out.println(friendlyTime(new DateTime().minusHours(1).toDate()));
        System.out.println(friendlyTime(new DateTime().minusDays(1).toDate()));
        System.out.println(friendlyTime(new DateTime().minusMonths(1).toDate()));
        System.out.println(friendlyTime(new DateTime().minusYears(1).toDate()));
        System.out.println(friendlyTime(new DateTime().minusYears(11).toDate()));
        System.out.println(friendlyTime(new DateTime().minusYears(112).toDate()));
        System.out.println(friendlyTime(new DateTime().minusYears(211).toDate()));
        System.out.println(friendlyTime(new DateTime().minusYears(1000).toDate()));
    }
}
