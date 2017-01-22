package com.harmonywisdom.dshbcbp.attachment.action;

import Decoder.BASE64Decoder;
import com.alibaba.fastjson.JSONObject;
import com.harmonywisdom.dshbcbp.attachment.bean.Attachment;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.attachment.service.impl.AttachmentConfigManager;
import com.harmonywisdom.framework.service.SpringUtil;
import com.harmonywisdom.framework.utils.UUIDGenerator;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/1/5.
 */
public class ImageUploadServlet extends HttpServlet {
    public static final String ATT_TYPE = "type";
    public static final String BUSINESS_ID = "businessId";
    private static final String ENCODING = "UTF-8";

    private DiskFileItemFactory factory;
    private AttachmentConfigManager configManager = AttachmentConfigManager.getInstance();
    private Logger logger = LoggerFactory.getLogger(getClass());
    private File cacheDir;
    private boolean saveToDisk;
    private boolean saveToDB;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        saveToDisk = configManager.isSaveToDisk();
        saveToDB = configManager.isSaveToDB();
        if (saveToDisk) {
            File saveDir = new File(configManager.getSavePath());
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            cacheDir = saveDir;
        }

        factory = new DiskFileItemFactory();

        if (configManager.isNeedChangeTempDir()) {
            File tmpDir = new File(configManager.getTempDir());
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

            factory.setRepository(tmpDir);
        }

        factory.setSizeThreshold(configManager.getThreshold());

        FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(config.getServletContext());
        factory.setFileCleaningTracker(fileCleaningTracker);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base64Str = request.getParameter("base64Str");
        if (StringUtils.isNotEmpty(base64Str)){
            byte[] b = new BASE64Decoder().decodeBuffer(base64Str);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            File cacheFile = new File(cacheDir, UUIDGenerator.generateUUID());
            OutputStream out = new FileOutputStream(cacheFile);
            out.write(b);
            out.flush();
            out.close();

            Attachment attachment = new Attachment();
            attachment.setAttachmentType(request.getParameter(ATT_TYPE));
            attachment.setBusinessId(request.getParameter(BUSINESS_ID));
            attachment.setName(FilenameUtils.getName("照片.jpg"));
            attachment.setExt(FilenameUtils.getExtension("jpg"));
            attachment.setPath(cacheFile.getAbsolutePath());
            attachment.setSize("86 KB");

            AttachmentService service = SpringUtil.getBean("attachmentService");
            service.save(attachment);
            attachment.setUuid(attachment.getId());

            Writer writer = response.getWriter();;
            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json;charset=utf-8");
            response.setContentType("text/plain;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            JSONObject jsobjcet = new JSONObject();
            jsobjcet.put("success", true);
            jsobjcet.put("id", attachment.getId());
            //jsobjcet.put("path",attachment.getPath());
            writer.write(jsobjcet.toJSONString());
        }else {
            String fn = request.getParameter("fn");
            try{
                if(ServletFileUpload.isMultipartContent(request))
                {
                    ServletFileUpload sfu = new ServletFileUpload(factory);
                    sfu.setFileSizeMax(configManager.getMaxSize()); //单个上传文件最大值
                    FileItemIterator fii = sfu.getItemIterator(request);
                    if(fii.hasNext()){
                        FileItemStream fis = fii.next();
                        if(!fis.isFormField()){

                            String fileName = fis.getName();  //获取上传的文件名

//                        Calendar calendar = Calendar.getInstance();
//                        String savename = String.valueOf(calendar.getTimeInMillis());
//                        String imgPath_s_suffix = fileName.substring(fileName.lastIndexOf(".") , fileName.length());  //获取后缀
//                        savename=savename  + imgPath_s_suffix;

                            BufferedInputStream in = new BufferedInputStream(fis.openStream());
                            File cacheFile = new File(cacheDir, UUIDGenerator.generateUUID());
                            BufferedOutputStream outs = new BufferedOutputStream(new FileOutputStream(cacheFile));
                            Streams.copy(in, outs, true);

                            Attachment attachment = new Attachment();

                            attachment.setAttachmentType(request.getParameter(ATT_TYPE));
                            attachment.setBusinessId(request.getParameter(BUSINESS_ID));
                            attachment.setName(FilenameUtils.getName(fileName));
                            attachment.setExt(FilenameUtils.getExtension(fileName));
                            attachment.setPath(cacheFile.getAbsolutePath());
                            attachment.setSize("86 KB");

                            AttachmentService service = SpringUtil.getBean("attachmentService");
                            service.save(attachment);
                            attachment.setUuid(attachment.getId());

                            Writer out = null;
                            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json;charset=utf-8");
                            response.setContentType("text/plain;charset=utf-8");
                            response.setHeader("Cache-Control", "no-cache");
                            out = response.getWriter();

                            JSONObject jsobjcet = new JSONObject();
                            jsobjcet.put("success", true);
                            jsobjcet.put("id", attachment.getId());
                            //jsobjcet.put("path",attachment.getPath());
                            out.write(jsobjcet.toJSONString());
                        }
                    }

                }
            }catch(Exception e){
                logger.error("",e);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
