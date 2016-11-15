package com.harmonywisdom.dshbcbp.utils;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * beanutil转换类
 * Created by sunzuoquan on 2015/7/22.
 */
public class BeanUtilsDateConverter implements Converter {
    private String patten = "yyyy-MM-dd";
    private String timestampPatten = "yyyy-MM-dd HH:mm:ss";
    public BeanUtilsDateConverter(String patten, String timestampPatten){
        this.patten = patten;
        this.timestampPatten = timestampPatten;
    }
    public Object convert(Class type, Object value){
        if(value == null){
            return null;
        }else if(type == Timestamp.class){
            return convertToDate(type, value, timestampPatten);
        }else if(type == Date.class){
            return convertToDate(type, value, patten);
        }else if(type == String.class){
            return convertToString(type, value);
        }
        throw new ConversionException("不能转换 " + value.getClass().getName() + " 为 " + type.getName());
    }

    protected Object convertToDate(Class type, Object value, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if(value instanceof String){
            try{
                if(StringUtils.isBlank(value.toString())){
                    return null;
                }
                Date date = sdf.parse((String) value);
                if(type.equals(Timestamp.class)){
                    return new Timestamp(date.getTime());
                }
                return date;
            }catch(Exception pe){
                return null;
            }
        }else if(value instanceof Date){
            return value;
        }

        throw new ConversionException("不能转换 " + value.getClass().getName() + " 为 " + type.getName());
    }

    protected Object convertToString(Class type, Object value) {
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(patten);

            if (value instanceof Timestamp) {
                sdf = new SimpleDateFormat(timestampPatten);
            }

            try{
                return sdf.format(value);
            }catch(Exception e){
                throw new ConversionException("日期转换为字符串时出错！");
            }
        }else{
            return value.toString();
        }
    }
}
