package com.jamasoftware.services.restclient.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static DateFormat dateFormatFull;
    private static DateFormat dateFormatTerse;
    private static DateFormat monthDayYearFormat;
    public static Date parseDate(String date) throws ParseException {
        if(date == null || date.length() == 0) return null;
        if(dateFormatFull == null) {
            dateFormatFull = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        }
        try {
            return dateFormatFull.parse(date);
        } catch (ParseException e) {
            return parseDateTerse(date);
        }
    }

    private static Date parseDateTerse(String date) throws ParseException {
        if(dateFormatTerse == null) {
            dateFormatTerse = new SimpleDateFormat("yyyy-MM-dd");
        }
        return dateFormatTerse.parse(date);
    }

    public static String monthDayYear(Date date) {
        if(monthDayYearFormat == null) {
            monthDayYearFormat = new SimpleDateFormat("MM/dd/yyyy");
        }
        return monthDayYearFormat.format(date);
    }
}
