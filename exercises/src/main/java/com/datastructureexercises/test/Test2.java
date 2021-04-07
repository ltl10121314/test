package com.datastructureexercises.test;


import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test2 {
    public static void main(String[] args) {

    }

    public static String getCurrentMonthlastday(String n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date parse = null;
        try {
            parse = sdf.parse(n);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cale = Calendar.getInstance();
        cale.setTime(parse);
        cale.set(Calendar.DATE, cale.getActualMaximum(Calendar.DATE));
        SimpleDateFormat sdfm = new SimpleDateFormat("yyyyMMdd");
        return sdfm.format(cale.getTime());
    }

   
}
