package com.redballgolf.golfSG.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class GetDate {
    public static String todaysDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        String today = formatter.format(date);
        return today;
    }

    public static String todaysDateInSimpleFormat(){
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        String todayInSimpleFormat = formatter2.format(date);
        return todayInSimpleFormat;
    }
}//class
