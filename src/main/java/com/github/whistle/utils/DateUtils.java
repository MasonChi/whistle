package com.github.whistle.utils;

import com.github.whistle.utils.enums.TimeIntervalUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            pe.getStackTrace();
        }
        return date.getTime();
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

    public static void main(String[] args) throws InterruptedException {
        System.out.println(DateUtils.getQuarter(4));
    }
}
