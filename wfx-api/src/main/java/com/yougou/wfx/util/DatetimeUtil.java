package com.yougou.wfx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * <p>Title: DatetimeUtil</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年5月10日
 */
public class DatetimeUtil {
	public static final String LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public DatetimeUtil()
    {
    }

    public static String getCurrentDate(String pattern)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String result = formatter.format(new Date());
        return result;
    }

    public static String getFormatDatetime()
        throws Exception
    {
        GregorianCalendar gCalendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat(LONG_DATE_TIME_PATTERN);
        String strDateTime;
        try
        {
            strDateTime = formatter.format(gCalendar.getTime());
        }
        catch(Exception ex)
        {
            System.out.println("Error Message:".concat(String.valueOf(String.valueOf(ex.toString()))));
            String s = null;
            return s;
        }
        return strDateTime;
    }

    public static Date StringToDate(String s)
    {
        Date date = new Date(0L);
        try
        {
            Calendar calendar = Calendar.getInstance();
            int year = Integer.parseInt(s.substring(0, s.indexOf("-")));
            int month = Integer.parseInt(s.substring(s.indexOf("-") + 1, s.lastIndexOf("-")));
            int day = Integer.parseInt(s.substring(s.lastIndexOf("-") + 1, s.length()));
            calendar.set(year, month - 1, day);
            date.setTime(calendar.getTime().getTime());
        }
        catch(Exception e)
        {
            System.out.println(String.valueOf((new StringBuffer(String.valueOf(e))).append(",").append(s)));
        }
        return date;
    }

    public static String DateToString(Date dt, String fmtStr)
    {
        SimpleDateFormat format = new SimpleDateFormat(fmtStr);
        return format.format(dt);
    }

    public static String DateToString(Date dt)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        return format.format(dt);
    }

}
