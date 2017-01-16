package com.harmonywisdom.dshbcbp.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by liuyifan on 2016/8/19.
 */
public class DocUtil {
    public static final String editAble="1";
    public static final String readOnly="2";

    private static Logger log = LoggerFactory.getLogger(DocUtil.class);

    public static void main(String[] args) throws Exception {
        LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
        map.put("year","1996");
        map.put("month","05");
        map.put("day","05");
        map.put("hour","10");
        map.put("minute","10");
        map.put("district","大青山");
        map.put("num","1");
        map.put("km","7");

        ////////////////////
        LinkedList<LinkedList<LinkedList<LinkedList<String>>>> tablesData=new LinkedList<LinkedList<LinkedList<LinkedList<String>>>>();
        ////////////////////
        {
            //第1张表
            LinkedList<LinkedList<LinkedList<String>>> table =new LinkedList<LinkedList<LinkedList<String>>>();
            LinkedList<LinkedList<String>> rows1=new LinkedList<LinkedList<String>>();
            LinkedList<String> cells1=new LinkedList<String>();
            cells1.add("a");
            cells1.add("b");
            cells1.add("c");
            rows1.add(cells1);

            LinkedList<LinkedList<String>> rows2=new LinkedList<LinkedList<String>>();
            LinkedList<String> cells2=new LinkedList<String>();
            cells2.add("1");
            cells2.add("2");
            cells2.add("3");
            rows2.add(cells2);

            table.add(rows1);
            table.add(rows2);

            tablesData.add(table);
        }
        ///////////////////////
        {
            //第2张表
            LinkedList<LinkedList<LinkedList<String>>> table =new LinkedList<LinkedList<LinkedList<String>>>();
            LinkedList<LinkedList<String>> rows1=new LinkedList<LinkedList<String>>();
            LinkedList<String> cells1=new LinkedList<String>();
            cells1.add("aa");
            cells1.add("bb");
            rows1.add(cells1);

            LinkedList<LinkedList<String>> rows2=new LinkedList<LinkedList<String>>();
            LinkedList<String> cells2=new LinkedList<String>();
            cells2.add("11");
            cells2.add("22");
            rows2.add(cells2);

            table.add(rows1);
            table.add(rows2);

//            tablesData.add(table);
        }
        ///////////////////////
        {
            //第3张表
            LinkedList<LinkedList<LinkedList<String>>> table =new LinkedList<LinkedList<LinkedList<String>>>();
            LinkedList<LinkedList<String>> rows1=new LinkedList<LinkedList<String>>();
            LinkedList<String> cells1=new LinkedList<String>();
            cells1.add("1111111");
            cells1.add("222222");
            cells1.add("3333333");
            cells1.add("44444");
            rows1.add(cells1);

            LinkedList<LinkedList<String>> rows2=new LinkedList<LinkedList<String>>();
            LinkedList<String> cells2=new LinkedList<String>();
            cells2.add("AAAAA");
            cells2.add("BBBBBBB");
            cells2.add("CCCCCCC");
            cells2.add("DDDDD");
            rows2.add(cells2);

            table.add(rows1);
            table.add(rows2);

//            tablesData.add(table);
        }

        WriteDocByPoi("D:\\test\\overManageDownload.docx","D:\\test\\wwss.docx",map,tablesData);
    }


    /**
     * 通过poi写字符串到doc文件。同时支持word 2003和2007+ 版本
     * @param templatePath 源doc文件路径
     * @param outputPath 输出doc文件路径
     * @param map 要替换doc文件中字符标记的 键值对。如doc文件中占位标记为 name，则要传的map的key为name，value为"张三"
     * @param tablesData
     * @throws Exception
     */
    public static boolean WriteDocByPoi(String templatePath, String outputPath, LinkedHashMap<String,String> map,LinkedList<LinkedList<LinkedList<LinkedList<String>>>> tablesData) throws Exception {
        String[] sp = templatePath.split("\\.");
        String[] dp = outputPath.split("\\.");
        if ((sp.length > 0) && (dp.length > 0)) { // 判断文件有无扩展名
            // 比较文件扩展名
            if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
                try {
                    XWPFDocument document = new XWPFDocument(
                            POIXMLDocument.openPackage(templatePath));
                    // 替换段落中的指定文字
                    Iterator<XWPFParagraph> itPara = document
                            .getParagraphsIterator();
                    while (itPara.hasNext()) {
                        XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (int i = 0; i < runs.size(); i++) {
                            String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
                            if (oneparaString!=null){
                                for (Map.Entry<String, String> entry : map.entrySet()) {
                                    if (entry.getValue()!=null){
                                        oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                                    }
                                }
                                runs.get(i).setText(oneparaString, 0);
                            }
                        }
                    }

                    // 替换表格中的指定文字
                    Iterator<XWPFTable> itTable = document.getTablesIterator();
                    while (itTable.hasNext()) {
                        XWPFTable table = (XWPFTable) itTable.next();
                        int rcount = table.getNumberOfRows();
                        for (int i = 0; i < rcount; i++) {
                            XWPFTableRow row = table.getRow(i);
                            List<XWPFTableCell> cells = row.getTableCells();
                            for (XWPFTableCell cell : cells) {
                                String cellTextString = cell.getText();
                                for (Map.Entry<String, String> e : map.entrySet()) {
                                    if (cellTextString.contains(e.getKey()))
                                        cellTextString = cellTextString
                                                .replace(e.getKey(),
                                                        e.getValue());
                                }
                                cell.removeParagraph(0);
                                cell.setText(cellTextString);
                            }
                        }
                    }


                    //获取文档中的所有表格，并根据函数传入的参数生成对应的行列数据
                    List<XWPFTable> xwpfTables = document.getTables();
                    for (int i = 0; i < tablesData.size(); i++) {
                        LinkedList<LinkedList<LinkedList<String>>> tableData = tablesData.get(i);
                        XWPFTable xwpfTable = xwpfTables.get(i);

                        for (int j = 0; j < tableData.size(); j++) {
                            LinkedList<LinkedList<String>> rowsData = tableData.get(j);//rowsData 一张表中所有行的集合。一张表有多个行

                            for (LinkedList<String> rowData : rowsData) { //rowData代表一行数据
                                XWPFTableRow xwpfRow = xwpfTable.createRow();
                                for (int k = 0; k < rowData.size(); k++) {
                                    if (xwpfRow.getCell(k)!=null){
                                        xwpfRow.getCell(k).setText(rowData.get(k));//rowData.get(k)代表行中的某一个cell
                                    }

                                }
                            }

                        }
                    }


                    FileOutputStream outStream = null;
                    outStream = new FileOutputStream(outputPath);
                    document.write(outStream);
                    outStream.close();
                    return true;
                } catch (Exception e) {
                    log.error("发生异常",e);
                    return false;
                }

            } else
                // doc只能生成doc，如果生成docx会出错
                if ((sp[sp.length - 1].equalsIgnoreCase("doc"))
                        && (dp[dp.length - 1].equalsIgnoreCase("doc"))) {
                        HWPFDocument document = null;
                    try {
                        document = new HWPFDocument(new FileInputStream(templatePath));
                        Range range = document.getRange();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            range.replaceText(entry.getKey(), entry.getValue());
                        }
                        FileOutputStream outStream = null;
                        outStream = new FileOutputStream(outputPath);
                        document.write(outStream);
                        outStream.close();
                        return true;
                    } catch (FileNotFoundException e) {
                        log.error("发生异常",e);
                        return false;
                    } catch (IOException e) {
                        log.error("发生异常",e);
                        return false;
                    }
                } else {
                    return false;
                }
        } else {
            return false;
        }
    }

    /**
     * 通过poi写字符串到doc文件。同时支持word 2003和2007+ 版本
     * @param templatePath 源doc文件路径
     * @param outputPath 输出doc文件路径
     * @param map 要替换doc文件中字符标记的 键值对。如doc文件中占位标记为 name，则要传的map的key为name，value为"张三"
     * @throws Exception
     */
    @Deprecated
    public static boolean WriteDocByPoi(String templatePath, String outputPath, LinkedHashMap<String,String> map) throws Exception {
        String[] sp = templatePath.split("\\.");
        String[] dp = outputPath.split("\\.");
        if ((sp.length > 0) && (dp.length > 0)) { // 判断文件有无扩展名
            // 比较文件扩展名
            if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
                try {
                    XWPFDocument document = new XWPFDocument(
                            POIXMLDocument.openPackage(templatePath));
                    // 替换段落中的指定文字
                    Iterator<XWPFParagraph> itPara = document
                            .getParagraphsIterator();
                    while (itPara.hasNext()) {
                        XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (int i = 0; i < runs.size(); i++) {
                            String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
                            if (oneparaString!=null){
                                for (Map.Entry<String, String> entry : map.entrySet()) {
                                    oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                                }
                                runs.get(i).setText(oneparaString, 0);
                            }
                        }
                    }

                    // 替换表格中的指定文字
                    Iterator<XWPFTable> itTable = document.getTablesIterator();
                    while (itTable.hasNext()) {
                        XWPFTable table = (XWPFTable) itTable.next();
                        int rcount = table.getNumberOfRows();
                        for (int i = 0; i < rcount; i++) {
                            XWPFTableRow row = table.getRow(i);
                            List<XWPFTableCell> cells = row.getTableCells();
                            for (XWPFTableCell cell : cells) {
                                String cellTextString = cell.getText();
                                for (Map.Entry<String, String> e : map.entrySet()) {
                                    if (cellTextString.contains(e.getKey()))
                                        cellTextString = cellTextString
                                                .replace(e.getKey(),
                                                        e.getValue());
                                }
                                cell.removeParagraph(0);
                                cell.setText(cellTextString);
                            }
                        }
                    }
                    FileOutputStream outStream = null;
                    outStream = new FileOutputStream(outputPath);
                    document.write(outStream);
                    outStream.close();
                    return true;
                } catch (Exception e) {
                    log.error("发生异常",e);
                    return false;
                }

            } else
                // doc只能生成doc，如果生成docx会出错
                if ((sp[sp.length - 1].equalsIgnoreCase("doc"))
                        && (dp[dp.length - 1].equalsIgnoreCase("doc"))) {
                    HWPFDocument document = null;
                    try {
                        document = new HWPFDocument(new FileInputStream(templatePath));
                        Range range = document.getRange();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            range.replaceText(entry.getKey(), entry.getValue());
                        }
                        FileOutputStream outStream = null;
                        outStream = new FileOutputStream(outputPath);
                        document.write(outStream);
                        outStream.close();
                        return true;
                    } catch (FileNotFoundException e) {
                        log.error("发生异常",e);
                        return false;
                    } catch (IOException e) {
                        log.error("发生异常",e);
                        return false;
                    }
                } else {
                    return false;
                }
        } else {
            return false;
        }
    }
}
