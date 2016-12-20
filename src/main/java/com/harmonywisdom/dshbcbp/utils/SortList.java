package com.harmonywisdom.dshbcbp.utils;

import org.apache.commons.lang.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2016/12/16.
 */
public class SortList<E>{
    public void Sort(List<E> list, final String method, final String sort){
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try{
                    Map<String,Object> mapA = transBean2Map(a);
                    Map<String,Object> mapB = transBean2Map(b);
                    //Method m1 = ((E)a).getClass().getMethod(method, null);
                    //Method m2 = ((E)b).getClass().getMethod(method, null);
                    String m1Value = mapA.get(method).toString();
                    if (StringUtils.isEmpty(m1Value)){
                        m1Value="0";
                    }
                    String m2Value = mapB.get(method).toString();
                    if (StringUtils.isEmpty(m2Value)){
                        m2Value="0";
                    }
                    Double m1=Double.valueOf(m1Value);
                    Double m2=Double.valueOf(m2Value);

                    if(sort != null && "desc".equals(sort))//倒序
                        ret = m2.compareTo(m1);
                    else//正序
                        ret = m1.compareTo(m2);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return ret;
            }
        });
    }
    public Map<String, Object> transBean2Map(Object obj) {

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
                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
}

