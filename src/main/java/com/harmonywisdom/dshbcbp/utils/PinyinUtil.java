package com.harmonywisdom.dshbcbp.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/11/3.
 */
public class PinyinUtil {

    /**
     * 获取所有种类的拼音
     * @param str
     * @return
     */
    public static String getAllPinYinCodes(String str){
        return getPinYinHeadCharCaps(str)+","+getPingYin(str);
    }
    // 将汉字转换为全拼
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        for (int i = 0; i < t0; i++) {
            // 判断是否为汉字字符
            if (Character.toString(t1[i]).matches(
                    "[\\u4E00-\\u9FA5]+")) {
                try {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                t4 += t2[0];
            } else
                t4 += Character.toString(t1[i]);
        }
        // System.out.println(t4);
        return t4;
    }

    // 返回中文的首字母(小写)
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    // 返回中文的首字母(大写)
    public static String getPinYinHeadCharCaps(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    // 将字符串转移为ASCII码
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPingYin("东胜区环保局"));
        System.out.println(getPinYinHeadChar("綦江县"));
        System.out.println(getCnASCII("綦江县"));
        Date startDate = new Date();
        System.out.println(startDate);
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(5,1);
        System.out.println(gc.getTime());

        Date d = MyDateUtils.parseDateFormat("2016-01-01","yyyy-MM-dd");
        System.out.println(d);
    }
}
