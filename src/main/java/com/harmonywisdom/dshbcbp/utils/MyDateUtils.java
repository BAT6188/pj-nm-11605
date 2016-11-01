package com.harmonywisdom.dshbcbp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MyDateUtils {

    public static Date getFullDate(String time, Boolean isStart){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int formatLength=19-time.length();
        if(formatLength==9){
            if(isStart){
                time +=" 00:00:00";
            }else{
                time +=" 23:59:59";
            }
        }
        if(formatLength==3){
            if(isStart){
                time +=":00";
            }else{
                time +=":59";
            }
        }
        Date returnDate = new Date();
        try {
            returnDate = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
        return returnDate;
    }
}
