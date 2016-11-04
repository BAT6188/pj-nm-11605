package com.harmonywisdom.dshbcbp.utils;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/4.
 */
public class EntityUtil {

    /**
     * 将实体转换成map
     * @param obj
     * @return
     */
    public static Map<String, Object> transBeanToMap(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(value!=null)map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    /**
     * 根据实体内容获取更新语句
     * @param obj
     * @return
     */
    public static Map<String, Object> getUpdateMap(Object obj){

        Map<String, Object> objMap = transBeanToMap(obj);
        StringBuffer updateString = new StringBuffer();
        updateString.append("update "+obj.getClass().getSimpleName()+" set ");
        StringBuffer values = new StringBuffer();

        Map<String, Object> valuesMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : objMap.entrySet()) {
            if(!entry.getKey().equals("id")){
                if(values.toString().equals("")){
                    values.append(entry.getKey()+"=:"+entry.getKey());
                }else{
                    values.append(","+entry.getKey()+"=:"+entry.getKey());
                }
            }
            valuesMap.put(entry.getKey(),entry.getValue());

        }
        updateString.append(values);
        updateString.append(" where id=:id");
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("upStr",updateString.toString());
        returnMap.put("valMap",valuesMap);
        return returnMap;
    }

    public static void main(String[] args) {
        Enterprise enterprise = new Enterprise();
        enterprise.setDelerName("sdfsfsdf");
        enterprise.setDelTime(new Date());
        Map<String, Object> map= transBeanToMap(enterprise);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
             System.out.println("key= " + entry.getKey() + " and value= "
                             + entry.getValue());
        }
    }

}
