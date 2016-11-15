package com.harmonywisdom.dshbcbp.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuozhengsoft.pageoffice.PageOfficeLink;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.Table;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * office page处理工具包
 * Created by sunzuoquan on 2015/7/22.
 */
public class OfficeUtil {
    /**
     * 插入一个新行
     * @param table1 表
     * @param rowNumber 行号
     * @param colNumber 列号
     */
    public static void insertTableRow(Table table1,int rowNumber,int colNumber){
        table1.insertRowAfter(table1.openCellRC(rowNumber, colNumber));
    }

    /**
     * 向表中填充数据
     * @param table1
     * @param rowNumber
     * @param startColNumber
     * @param values
     */
    public static void fillTableRow(Table table1,int rowNumber,int startColNumber,List<String> values){
        for (int i=0; i<values.size(); i++){
        	String val = values.get(i);
        	if(val == null){
        		val = "";
        	}
            table1.openCellRC(rowNumber, startColNumber+i).setValue(val);
        }
    }

    /**
     * 填充word中的普通值
     * @param map
     * @param data
     * @param doc
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void fillSingleDataRegion(WordDocument doc,Object data,Map<String,Object> map) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Set<String> set = map.keySet();
        // 注册日期转换
        Converter converter = new BeanUtilsDateConverter("yyyy年MM月dd日","yyyy年MM月dd日");
        ConvertUtils.register(converter, Date.class);
        ConvertUtils.register(converter,String.class);
        // 处理数据项
        for(String keyString : set ){
            if("tables".equals(keyString)){
                continue;
            }
            String val = BeanUtils.getProperty(data, keyString );
            if(StringUtils.isBlank(val)){
                val = "空";
            }
            DataRegion dataRegion = doc.openDataRegion(keyString);
            dataRegion.setValue(val);
        }
    }

    /**
     * 填充word中的table
     * @param doc
     * @param object
     * @param map
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void fillTableDataRegion(WordDocument doc, Object object, Map<String, Object> map) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 处理表
        JSONArray tables = (JSONArray)map.get("tables");
        if(tables != null){
            for(int i=0; i<tables.size(); i++) {
                JSONObject tableDef = tables.getJSONObject(i);
                // 获取表
                String tableRegionName = tableDef.getString("regionName");
                int startRowNumber = tableDef.getIntValue("startRowNumber");
                int startColNumber = tableDef.getIntValue("startColNumber");
                JSONArray tableColKeys = tableDef.getJSONArray("keyNames");
                Table table1 = doc.openDataRegion("PO_" + tableRegionName).openTable(1);
                // 获取表数据
                String values = BeanUtils.getProperty(object, tableRegionName);
                if (StringUtils.isBlank(values)) {
                    continue;
                }
                // 转换表数据
                JSONArray tableValue = JSON.parseArray(values);
                if (tableValue == null) {
                    continue;
                }
                for (int rowIndex = 0; rowIndex < tableValue.size(); rowIndex++) {
                    // 插入一个新行
                    OfficeUtil.insertTableRow(table1, startRowNumber+rowIndex, tableColKeys.size());
                    // 数据填充
                    List<String> rowValues = new ArrayList<String>();
                    JSONObject rowData = tableValue.getJSONObject(rowIndex);
                    for (int colIndex = 0; colIndex < tableColKeys.size(); colIndex++) {
                        rowValues.add(rowData.getString(tableColKeys.getString(colIndex)));
                    }
                    OfficeUtil.fillTableRow(table1, startRowNumber+rowIndex,startColNumber,rowValues);
                }
            }
        }
    }

    /**
     * 通过属性名，获取bean的属性值
     * @param bean
     * @param fieldName
     * @return
     */
    private static String getProperty(Object bean,String fieldName,Converter converter){
        Class clazz = bean.getClass();
        try {
            Field field = clazz.getField(fieldName);
            Object val = field.get(bean);
            if(val instanceof Date ){
                return (String)converter.convert(String.class,val);
            }
            return String.valueOf(val);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPageOfficeLink(HttpServletRequest request, String url){
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        return PageOfficeLink.openWindow(request,basePath+url,"");
    }

//    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        License license = new License();
//        license.setSendDate(new Date());
//        ConvertUtils.register(new BeanUtilsDateConverter("yyyy-MM-dd"), Date.class);
//        ConvertUtils.register(new BeanUtilsDateConverter("yyyy-MM-dd"),String.class);
//        String val = BeanUtils.getProperty(license,"sendDate");
//        System.out.println(val);
//    }
}


