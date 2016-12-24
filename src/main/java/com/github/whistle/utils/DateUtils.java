package com.github.whistle.utils;

import com.github.whistle.utils.enums.DateLang;
import com.github.whistle.utils.enums.Portion;
import com.github.whistle.utils.enums.TimeIntervalUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by child.
 * Date: 2016/12/13.
 * Description: 日期时间转化
 */
public class DateUtils {

    /**
     * 获取当前毫秒值
     **/
    public static long NOW_DATE_MIS = System.currentTimeMillis();

    /**
     * 把一个字符串时间转换为毫秒值
     * 如 2016-12-13 00:00:00 转换为 1406086513619
     *
     * @param strDate 日期/时间字符串
     * @return 毫秒值
     */
    public static long str2DateTimeMis(String strDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            pe.getStackTrace();
        }
        return date.getTime();
    }

    /**
     * date -> yyyy-MM-dd
     *
     * @param date 日期
     * @return yyyy-MM-dd格式日期
     */
    public static String date2DayStr(final Date date) {
        return date2Str(date, "yyyy-MM-dd");
    }

    /**
     * date -> yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return yyyy-MM-dd格式日期
     */
    public static String date2DateStr(final Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * date -> format
     *
     * @param date   日期
     * @param format 日期格式
     * @return format格式日期
     */
    public static String date2Str(final Date date, final String format) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * date:(yyyy-MM-dd HH:mm:ss) ->  date:(yyyy-MM-dd 00:00:00)
     *
     * @param date 日期时间
     * @return 日期
     */
    public static Date date2Day(final Date date) {
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateFormat.format(date));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string -> date:(yyyy-MM-dd 00:00:00)
     *
     * @param date 日期,格式yyyy-MM-dd HH:mm:ss
     * @return 日期
     */
    public static Date str2Day(final String date) {
        return str2Date(date, "yyyy-MM-dd");
    }

    /**
     * string -> date:(yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期,格式yyyy-MM-dd HH:mm:ss
     * @return 日期
     */
    public static Date str2Date(final String date) {
        return str2Date(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * String -> format
     *
     * @param strDate 字符串日期
     * @param format  格式化
     * @return 日期
     */
    public static Date str2Date(final String strDate, String format) {
        if (null == strDate) {
            return null;
        }
        if (null == format) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(strDate);
        } catch (ParseException pe) {
            pe.getStackTrace();
        }
        return null;
    }

    /**
     * 获取日期的最早时刻
     *
     * @param date Date
     * @return 日期的最早时刻
     */
    public static Date getDateFirstTime(final Date date) {
        return getDateFirstTime(date2DateStr(date));
    }

    /**
     * 获取日期的最早时刻
     *
     * @param date String
     * @return 日期的最早时刻
     */
    public static Date getDateFirstTime(final String date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(date + " 00:00:00");
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取日期的最后时刻
     *
     * @param date Date
     * @return 日期的最后时刻
     */
    public static Date getDateLastTime(final Date date) {
        return getDateLastTime(date2DateStr(date));
    }

    /**
     * 获取日期的最后时刻
     *
     * @param date String
     * @return 日期的最后时刻
     */
    public static Date getDateLastTime(final String date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(date + " 23:59:59");
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取{interval}间隔之前/之后的时间
     * 间隔之前:interval < 0
     * 间隔之后:interval > 0
     *
     * @param date     时间
     * @param interval 间隔
     * @param unit     间隔单位<br>
     *                 DAY:天
     *                 MONTH:月
     *                 QUARTER:季度
     *                 YEAR:年
     * @return 日期 时间
     */
    public static String getIntervalDateTime(Date date, int interval, TimeIntervalUnit unit) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (unit) {
            case DAY:
                calendar.add(Calendar.DATE, interval);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, interval);
                break;
            case QUARTER:
                calendar.add(Calendar.MONTH, interval * 3);
                break;
            case YEAR:
                calendar.add(Calendar.YEAR, interval);
                break;
            default:
                calendar.add(Calendar.DATE, interval);
                break;
        }
        final Date time = calendar.getTime();

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    /**
     * 获取当前日期的部分
     *
     * @param date Date
     * @return 当前日期的年 或 月 或 日
     */
    public static int getPortion(final Date date, Portion portion) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int d;
        switch (portion) {
            case YEAR:
                d = calendar.get(Calendar.YEAR);
                break;
            case MONTH:
                d = calendar.get(Calendar.MONTH) + 1;
                break;
            case DAY:
                d = calendar.get(Calendar.DATE);
                break;
            default:
                d = calendar.get(Calendar.DATE);
                break;
        }
        return d;
    }

    /**
     * 根据月份计算季度
     *
     * @param month 月份
     * @return 季度
     */
    public static int getQuarter(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month is invalid.");
        }
        return (month - 1) / 3 + 1;
    }

    /**
     * 获取当前星期几
     *
     * @param date String
     * @return int 星期几
     */
    public static int getWeek(String date) {
        Date d = str2Date(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取当前日期星期几
     *
     * @param date Date
     * @param lang 语言
     * @return String 星期几
     */
    public static String getWeek(Date date, DateLang lang) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        String[] s;
        if (null == lang || DateLang.EN == lang) {
            s = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday",
                    "Thursday", "Friday", "Saturday"};
        } else {
            s = new String[]{"星期日", "星期一", "星期二", "星期三",
                    "星期四", "星期五", "星期六"};
        }

        return s[c.get(Calendar.DAY_OF_WEEK) - 1];
    }
}
