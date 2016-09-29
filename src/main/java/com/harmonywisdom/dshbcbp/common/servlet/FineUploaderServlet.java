package com.harmonywisdom.dshbcbp.common.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by harmonywisdom on 2016/9/23.
 */
public class FineUploaderServlet extends HttpServlet {
    /**
     * 存储位置配置项名称
     */
    public static final String UPLOAD_FILE_TEMP_DIR = "tempDir";
    // 存储位置
    private String tempDir;

    /**
     * 将文件存储到服务器上
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileStorePath = tempDir;
        File file = new File(fileStorePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        JSONObject jsonObject = new JSONObject();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(file);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = null;
        FileItem realFileItem = null;
        try {
            String preFileName ="";
            String fileRealName ="";
            fileItems = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                long size = 0;
                String filePath = fileItem.getName();
                if("bussinessId".equals( fileItem.getFieldName())){
                    fileStorePath = tempDir+"/"+fileItem.getString();
                }
                if("qquuid".equals( fileItem.getFieldName())){
                    preFileName = fileItem.getString();
                }
                if("qqfile".equals( fileItem.getFieldName())){
                    fileRealName = fileItem.getName();
                    realFileItem = fileItem;
                }
            }
            String filePath = fileStorePath +"/"+preFileName+"_"+fileRealName;
            System.out.println(filePath);
            file = new File(filePath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            // 将文件移动到指定位置
            if(realFileItem != null){
                realFileItem.write(file);
            }
            jsonObject.put("success",true);
            jsonObject.put("newUuid",preFileName);
        } catch (Exception e) {
            jsonObject.put("success",false);
            jsonObject.put("error",e.getMessage());
            e.printStackTrace();
        }
        jsonObject.put("reset",false);
        jsonObject.put("preventRetry",true);
        jsonObject.put("reset",false);
        System.out.println(jsonObject.toJSONString());
        resp.getWriter().write(jsonObject.toJSONString());
    }

    /**
     * 获取文件列表，测试使用，将存储目录下的文件列表都显示出来
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        tempDir = this.getServletConfig().getInitParameter(UPLOAD_FILE_TEMP_DIR);
        if (tempDir == null || "".equals(tempDir.trim())) {
            tempDir = getServletContext().getRealPath("/uploadTempDir");
        }
        File file = new File(tempDir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
