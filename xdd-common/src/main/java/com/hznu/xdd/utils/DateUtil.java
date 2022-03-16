package com.hznu.xdd.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Misaki
 */
public class DateUtil {

    private static String FORMAT = "yyyy-MM-dd";

    private static String FORMAT2 = "yyyyMMdd";


    private static ThreadLocal<SimpleDateFormat> threadLocal1 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(FORMAT);
        }
    };

    private static ThreadLocal<SimpleDateFormat> threadLocal2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(FORMAT2);
        }
    };

    public static Date getStartTime(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTime();

    }

    public static Date getStartWeek() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }

    public static Date getEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static String getDateStr(Date date) {

        SimpleDateFormat sdf = threadLocal1.get();
        return sdf.format(date);
    }

    public static String getDateStr2(Date date) {

        SimpleDateFormat sdf = threadLocal2.get();
        return sdf.format(date);
    }

    /**
     * 判断两个日期中间的天数
     * @param beginDate
     * @param endDate
     */
    public static Integer getDays(Date beginDate, Date endDate) {

        int days = (int) ((endDate.getTime() - beginDate.getTime()) / (1000*3600*24));

        return days;
    }

    public static Integer getMinutes(Date beginDate, Date endDate) {
        int minutes = (int) ((endDate.getTime() - beginDate.getTime()) / (1000*60));

        return minutes;
    }



    public static Date afterDate(Date date, Integer after) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + after);
        Date newDate = calendar.getTime();
        return newDate;
    }

    public static BigDecimal dateDiff(Date date1,Date date2) {

        Long time1 = date1.getTime();
        Long time2 = date2.getTime();
        Long diff = time1 >time2 ? time1 -time2 : time2 -time1;
        return new BigDecimal(diff).divide(new BigDecimal(1000 * 60 * 60),2, RoundingMode.HALF_UP);
    }

    public static Date addDate(int i, Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }

    public static Date addSecond(int i,Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, i);
        return calendar.getTime();
    }

}
