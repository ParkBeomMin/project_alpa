package com.example.park.management;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by jisung on 2017. 2. 19..
 */

public class timerAlpa {

    public long Counter(String target){
        long a = doDiffOfDate(target,doCurrentDate());
        long b = doDiffOfDate(doCurrentDate(),target);
        if(a>b)
            return a;
        return b;
    }

    public long doDiffOfDate(String start, String end){
        //String start = "2015-04-01"
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = formatter.parse(start);
            Date endDate = formatter.parse(end);

            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            return diffDays;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String doCurrentDate(){
        int nYear;
        int nMonth;
        int nDay;
        int nTime;
        int nMin;
        int nSec;
        // 현재 날짜 구하기
        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        nYear = calendar.get(Calendar.YEAR);
        nMonth = calendar.get(Calendar.MONTH) + 1;
        nDay = calendar.get(Calendar.DAY_OF_MONTH);
        nTime = calendar.get(Calendar.HOUR_OF_DAY);
        nMin = calendar.get(Calendar.MINUTE);
        nSec = calendar.get(Calendar.SECOND);
        Log.d("reser",""+nTime);
        String strD =nYear + "-"+ nMonth + "-" + nDay+" "+nTime+":"+nMin+":"+nSec;
        return strD;
    }

}

