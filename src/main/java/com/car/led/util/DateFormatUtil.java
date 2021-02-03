package com.car.led.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.util.StdDateFormat;

public class DateFormatUtil extends StdDateFormat {

    private static final long serialVersionUID = -3201781773655300201L;

    public static final DateFormatUtil instance = new DateFormatUtil();

    @Override
    /**
     * @ClassName: CustomDateFormat
     * 这个方法可不写，jckson主要使用的是parse(String)这个方法用来转换日期格式的，
     * 只要覆盖parse(String)这个方法即可
     * @date 2018年01月23日 下午4:28:57
     */
    public Date parse(String dateStr, ParsePosition pos) {
        SimpleDateFormat sdf  = null;
        if(StringUtils.isEmpty(dateStr)){
            return null;
        }
        if (dateStr.length() == 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 16) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 19) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 23) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.parse(dateStr, pos);
        }
        return super.parse(dateStr, pos);
    }

    @Override
    public Date parse(String dateStr) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf  = null;
        if(StringUtils.isEmpty(dateStr)){
            return null;
        }
        if (dateStr.length() == 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 16) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 19) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr, pos);
        }
        if (dateStr.length() == 23) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.parse(dateStr, pos);
        }
        return super.parse(dateStr, pos);
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date, toAppendTo, fieldPosition);
    }

    public DateFormatUtil clone() {
        return new DateFormatUtil();
    }

    public static  String   getYearMonthDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
         int year= calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH) + 1;
       int  day =calendar.get(Calendar.DAY_OF_MONTH);
         return year+"-"+month+"-"+day;
    }



    public static String   dateToString(Date dt){
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dFormat.format(dt);
        return format;
    }
}
