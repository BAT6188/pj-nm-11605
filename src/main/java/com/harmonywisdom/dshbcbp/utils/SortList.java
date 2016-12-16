package com.harmonywisdom.dshbcbp.utils;

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
                    String m2Value = mapB.get(method).toString();
                    if(!(m1Value==null || m2Value==null)){
                        if(sort != null && "desc".equals(sort))//倒序
                            ret = m2Value.compareTo(m1Value);
                        else//正序
                            ret = m1Value.compareTo(m2Value);
                    }
                }catch(Exception e){
                    System.out.println(e);
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

