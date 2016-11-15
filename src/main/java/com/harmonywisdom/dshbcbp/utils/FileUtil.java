package com.harmonywisdom.dshbcbp.utils;

import java.io.*;

/**
 * 操作文件及流的工具包
 * Created by sunzuoquan on 2015/7/21.
 */
public class FileUtil {
    /**
     * 读取文本文件
     * @param filePath 文件路径
     * @return
     */
    public static String readFile(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return null;
        }
        FileInputStream inputStream = null;
        InputStreamReader read = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(file);
            read = new InputStreamReader(inputStream,"UTF-8");
            reader = new BufferedReader(read);
            StringBuilder sb = new StringBuilder();
            String text = "";
            while ((text = reader.readLine())!= null){
                sb.append(text);
            }
            return  sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(read != null){
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径
     * @param newPath String 复制后路径
     * @return boolean
     */
    public static void copyFileNotOverwrite(String oldPath, String newPath) {
        File newFile = new File(newPath);
        // 如果文件已经存在则不覆盖
        if(newFile.exists()){
            return;
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            int readCount = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                is = new FileInputStream(oldfile);
                os = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ( (readCount = is.read(buffer)) != -1) {
                    os.write(buffer, 0, readCount);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
