package com.harmonywisdom.dshbcbp.common.dict.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 标题: 日期时间处理工具类
 * 日期: 2016-08-10 上午9:00
 */
public class DateUtil {

	/**
     * 将字符串转化为日期
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date strToDate(String dateStr, String pattern) {
    	
    	Date date = null;
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	
    	try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return date;
    }
	
    /**
     * 将日期转化为字符串
     * @param date
     * @param format
     * @return
     */
	public static String dateToStr(Date date, String format) {
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
        	return sdf.format(date.getTime());
        } else {
        	return sdf.format(new Date().getTime());
        }
    }
	
	/**
	 * 将当前日期加减num天/月/年/小时/分/秒.
	 * @param date 当前时间
	 * @param num	对当前时间作增量加减的值
	 * @param dateType	日期类型：year代表年份, month代表月份, day代表天数,......依此类推
	 * @return
	 */
    public static Date dateAdd(Date date, int num, String dateType) {
    	// 日期处理模块 (将日期加上某些天或减去天数)返回字符串
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        
        if ("year".equals(dateType)) {
        	canlendar.add(Calendar.YEAR, num); // 对年份加减
        } else if ("month".equals(dateType)) {
        	canlendar.add(Calendar.MONTH, num); // 对月份加减 如果不够加减会将年变动
        } else if ("day".equals(dateType)) {
        	canlendar.add(Calendar.DATE, num); // 对日期加减 如果不够加减会将月变动
        } else if ("hour".equals(dateType)) {
        	canlendar.add(Calendar.HOUR, num); // 对小时加减 如果不够加减会将日期变动
        } else if ("minute".equals(dateType)) {
        	canlendar.add(Calendar.MINUTE, num); // 对分钟加减 如果不够加减会将小时变动
        } else if ("second".equals(dateType)) {
        	canlendar.add(Calendar.SECOND, num); // 对秒加减 如果不够加减会将分钟变动
        }
        return canlendar.getTime();
    }
}
