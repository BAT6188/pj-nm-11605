package com.harmonywisdom.dshbcbp.utils;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            /**创建封装数据的bean**/
            T bean = beanClass.newInstance();
            Map map = request.getParameterMap();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    /**
     * 方法入口，得到Dto
     *@param request
     *@param dtoClass 传入的实体类
     *@return
     */
    public static Object getDTO(HttpServletRequest request, Class dtoClass) {
        Object dtoObj = null;
        if ((dtoClass == null) || (request == null))
            return dtoObj;
        try {
            //实例化对象
            dtoObj = dtoClass.newInstance();
            setDTOValue(request, dtoObj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dtoObj;
    }
    /**
     * 保存数据
     *@param request
     *@param dto
     *@throws Exception
     */
    public static void setDTOValue(HttpServletRequest request, Object dto) throws Exception {
        if ((dto == null) || (request == null))
            return;
        //得到类中所有的方法 基本上都是set和get方法
        Method[] methods = dto.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            try {
                //方法名
                String methodName = methods[i].getName();
                //方法参数的类型
                Class[] type = methods[i].getParameterTypes();
                //当时set方法时，判断依据：setXxxx类型
                if ((methodName.length() > 3) && (methodName.startsWith("set")) && (type.length == 1)) {
                    //将set后面的大写字母转成小写并截取出来
                    String name = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    Object objValue = getBindValue(request, name, type[0]);
                    if (objValue != null) {
                        Object[] value = { objValue };
                        invokeMothod(dto, methodName, type, value);
                    }
                }
            } catch (Exception ex) {
                throw ex;
            }
        }
    }
    /**
     * 通过request得到相应的值
     *@param request HttpServletRequest
     *@param bindName 属性名
     *@param bindType 属性的类型
     *@return
     */
    public static Object getBindValue(HttpServletRequest request, String bindName, Class bindType) {
        //得到request中的值
        String value = request.getParameter(bindName);
        if (value != null) {
            value = value.trim();
        }
        return getBindValue(value, bindType);
    }
    /**
     * 通过调用方法名（setXxxx）将值设置到属性中
     *@param classObject 实体类对象
     *@param strMethodName 方法名(一般都是setXxxx)
     *@param argsType 属性类型数组
     *@param args 属性值数组
     *@return
     *@throws NoSuchMethodException
     *@throws SecurityException
     *@throws IllegalAccessException
     *@throws IllegalArgumentException
     *@throws InvocationTargetException
     */
    public static Object invokeMothod(Object classObject, String strMethodName, Class[] argsType, Object[] args)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        //得到classObject这个类的方法
        Method concatMethod = classObject.getClass().getMethod(strMethodName, argsType);
        //调用方法将classObject赋值到相应的属性
        return concatMethod.invoke(classObject, args);
    }
    /**
     * 根据bindType类型的不同转成相应的类型值
     *@param value String类型的值，要根据bindType类型的不同转成相应的类型值
     *@param bindType 属性的类型
     *@return
     */
    public static Object getBindValue(String value, Class bindType) {
        if ((value == null) || (value.trim().length() == 0))
            return null;
        String typeName = bindType.getName();
        //依次判断各种类型并转换相应的值
        if (typeName.equals("java.lang.String"))
            return value;
        if (typeName.equals("int"))
            return new Integer(value);
        if (typeName.equals("long"))
            return new Long(value);
        if (typeName.equals("boolean"))
            return new Boolean(value);
        if (typeName.equals("float"))
            return new Float(value);
        if (typeName.equals("double"))
            return new Double(value);
        if (typeName.equals("java.math.BigDecimal")) {
            if ("NaN.00".equals(value))
                return new BigDecimal("0");
            return new BigDecimal(value.trim());
        }
        if (typeName.equals("java.util.Date"))
            //参考DateUtil.parseDateDayFormat方法，value如果是时间类型，必须是yyyy-MM-dd格式才能被识别
            //请参考我的另一篇博客http://blog.csdn.net/bq1073100909/article/details/49472615
            return MyDateUtils.parseDateDayFormat(value);
        if (typeName.equals("java.lang.Integer"))
            return new Integer(value);
        if (typeName.equals("java.lang.Long")) {
            return new Long(value);
        }
        if (typeName.equals("java.lang.Boolean")) {
            return new Boolean(value);
        }
        return value;
    }

    /**
     * 转换hightcharts对象
     * @param list
     * @return
     */
    public static Map<String,Object[]> transHightchartsMapObj(List<Object[]> list,Boolean isColumn){
        Map<String,Object[]> result = new HashMap<>();
        if(list != null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] y1list = new Object[list.size()];
            Object[] y21list = new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object[] oo = list.get(i);
                if(isColumn){
                    xlist[i] = oo[0];
                    y1list[i] = oo[1];
                    y21list[i] = oo[2];
                }else{
                    xlist[i] = oo[0];
                    y1list[i] = oo[1];
                }
            }
            if(isColumn){
                result.put("x", xlist);
                result.put("y1", y1list);
                result.put("y2", y21list);
            }else{
                result.put("x", xlist);
                result.put("y", y1list);
            }
        }
        return result;
    }
}
