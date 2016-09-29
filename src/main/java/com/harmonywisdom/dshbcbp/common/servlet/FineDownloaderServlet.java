package com.harmonywisdom.dshbcbp.common.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by harmonywisdom on 2016/9/23.
 */
public class FineDownloaderServlet extends HttpServlet {
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
        String bussinessId = req.getParameter("bussinessId");
        String qquuid = req.getParameter("qquuid");
        File file = new File("C:\\temp\\upload\\"+bussinessId);

        File[] fileDirs = file.listFiles();
        File downFile = null;
        for(File tempFile : fileDirs){
            if(tempFile.isDirectory()){
                continue;
            }
            if(tempFile.getName().indexOf(qquuid)==0){
                downFile = tempFile;
                break;
            }
        }
        if(downFile == null) {
            return;
        }

        resp.setContentLength((int) downFile.length());
        ServletContext context = getServletContext();

        String mimeType = context.getMimeType(downFile.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
        resp.setContentType(mimeType);

        resp.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode( downFile.getName(),"UTF-8"));
        OutputStream outputStream = resp.getOutputStream();
        FileInputStream inputStream = new FileInputStream(downFile);
        try{
            byte[] buffer = new byte[512];
            int count = 0;
            while ((count = inputStream.read(buffer))>0){
                outputStream.write(buffer,0,count);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
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
        this.doPost(req, resp);
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
