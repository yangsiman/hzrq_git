package com.hzvanke.report.commons;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils2 extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 获得指定日期的年月日的date对象
     */
    public static Date getDate(Date date) {
        return parseDate(formatDate(date, "yyyy-MM-dd"));
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        if (date == null) return null;
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    public static String getMonth(Date date) {
        return formatDate(date, "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    public static String getDay(Date date) {
        return formatDate(date, "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 获得日期在每周的第几天，周日是第一天
     *
     * @param date
     * @return int
     * @author 作者: 张召
     * @date 2020/10/26 11:20 版本: v1.1
     */
    public static int getWeekDay(Date date) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得月初是每周的第几天
     *
     * @param date
     * @param month
     * @return int
     * @author 作者: 张召
     * @date 2021/3/19 13:53 版本: v1.1
     */
    public static int getMonthFirst(Date date, int month) {
        try {
            if (date == null) date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (month > 0) {
                calendar.add(Calendar.MONTH, month);
            }
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return calendar.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
     * "yyyy/MM/dd HH:mm", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 计算时间的差值绝对值
     *
     * @param end   结束时间
     * @param begin 开始时间
     * @return java.lang.String
     * @author 作者: 张召
     * @date 2020/4/13 17:15 版本: v1.1
     */
    public static String getDatePoor(Date begin, Date end) {
        if (begin == null || end == null) return "";
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = Math.abs(end.getTime() - begin.getTime());
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        if (day > 0) {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (hour > 0) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (min > 0) {
            return min + "分钟" + sec + "秒";
        }
        return sec + "秒";
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获得给定时间多少秒之后的时间
     *
     * @param date
     * @param sec
     * @return java.util.Date
     * @author 作者: 张召
     * @date 2019/8/16 下午 9:43 版本: v1.1
     */
    public static Date addBySecond(Date date, int sec) {
        try {
            long currentTime = date.getTime() + sec * 1000;
            date = new Date(currentTime);
            return date;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获得给定时间多少分钟之后的时间
     *
     * @param date
     * @return java.util.Date
     * @author 作者: 张召
     * @date 2019/8/16 下午 9:43 版本: v1.1
     */
    public static Date addByMin(Date date, int min) {
        try {
            long currentTime = date.getTime() + min * 60 * 1000;
            date = new Date(currentTime);
            return date;
        } catch (Exception e) {

        }
        return new Date();
    }

    public static boolean isSameDay(Date date1, Date date2) {
        try {
            if (formatDate(date1).equals(formatDate(date2))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断当天是否是生日
     *
     * @param date1
     * @return boolean
     * @author 作者: 张召
     * @date 2020/1/1 13:19 版本: v1.1
     */
    public static boolean isBirthDay(Date date1) {
        try {
            if (formatDate(date1, "MM-dd").equals(formatDate(new Date(), "MM-dd"))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isToDay(Date date) {
        return isSameDay(date, new Date());
    }

    /**
     * 日期加减天数
     *
     * @param date
     * @param day
     * @return java.util.Date
     * @author 作者: 张召
     * @date 2020/9/10 13:55 版本: v1.1
     */
    public static Date addDay(Date date, int day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, day);
            date = calendar.getTime();
        } catch (Exception e) {
        }
        return date;
    }

    public static Date addMonth(Date date, int month) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, month);
            date = calendar.getTime();
        } catch (Exception e) {
        }
        return date;
    }

    public static Date getMonthFirst(Date date) {
        return addMonthFirst(date, 0);
    }

    /**
     * 下个月月初
     *
     * @param date
     * @param month
     * @return java.util.Date
     * @author 作者: 张召
     * @date 2020/10/26 12:20 版本: v1.1
     */
    public static Date addMonthFirst(Date date, int month) {
        try {
            if (date == null) date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (month > 0)
                calendar.add(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            date = calendar.getTime();
        } catch (Exception e) {
        }
        return date;
    }

    public static Date addYear(Date date, int year) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, year);
            date = calendar.getTime();
        } catch (Exception e) {
        }
        return date;
    }

    public static int hour(Date begin, Date end) {
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = Math.abs(end.getTime() - begin.getTime());
        // 计算差多少小时
        long hour = diff / nh;
        return (int) hour;
    }

    public static int min(Date begin, Date end) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = Math.abs(end.getTime() - begin.getTime());
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        return (int) min;
    }

    public static int sec(Date begin, Date end) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = Math.abs(end.getTime() - begin.getTime());
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return (int) sec;
    }

}
