package com.harmonywisdom.dshbcbp.attachment.action;

import com.harmonywisdom.dshbcbp.appclient.bean.AppClient;
import com.harmonywisdom.dshbcbp.appclient.service.AppClientService;
import com.harmonywisdom.framework.action.DownloadableAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import com.harmonywisdom.dshbcbp.attachment.bean.Attachment;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.attachment.service.impl.AttachmentConfigManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AttachmentAction extends DownloadableAction<Attachment, AttachmentService> {
	
    @AutoService
    private AttachmentService attachmentService;
    @AutoService
    private AppClientService appClientService;
    @Override
    protected AttachmentService getService() {
        return attachmentService;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("请使用AttachmentServlet进行上传操作");
    }

    public void download() {
        if(StringUtils.isNotBlank(entity.getAttachmentType()) && "apkDownload".equals(entity.getAttachmentType())){
            AppClient apk = appClientService.findNewestApk();
            if(apk!=null){
                entity.setId(apk.getAttachmentId());
            }
        }
        AttachmentConfigManager manager = AttachmentConfigManager.getInstance();
        Attachment attachment = attachmentService.findById(entity.getId());
        if (attachment != null) {
            boolean needCacheFile = false;

            File file = null;
            if(StringUtils.isNotEmpty(attachment.getPath())){
                file = new File(attachment.getPath());
            }

            if (file!=null) {// 如果是以前保存的附件，则还是通过以前的方式文件进行下载
                if (file.exists()) {
                    response.setContentType("application/octet-stream");
                    InputStream is = null;
                    try {
                        response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(attachment.getName(), "UTF-8"));
                        is = new FileInputStream( new File(attachment.getPath()));
                        response.setContentLength(is.available());
                        OutputStream os = response.getOutputStream();
                        byte[] buffer = new byte[1024];
                        int readCount = 0;
                        while((readCount=is.read(buffer))>0){
                            os.write(buffer,0,readCount);
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        if(is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    response.setStatus(500);
//                    write(new File(attachment.getPath()), attachment.getName());
                    return;
                } else if (manager.isSaveToDB()) {
                    needCacheFile = true;
                } else {
                    response.setStatus(500);
                    write("未找到相关文件");
                    return;
                }
            }

            byte[] data = attachment.getData();
            if (data != null && data.length > 0) {
                write(data, attachment.getName());

                if (needCacheFile) {
                    try {
                        File dir = file.getParentFile();
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(data), file);
                    } catch (IOException e) {
                        log.error("保存缓存时发生异常", e);
                    }
                }
            } else {
                response.setStatus(500);
                write("未找到相关文件");
            }
        }
    }

    public void listAttachment() {
        String businessId = entity.getBusinessId();
        String type = entity.getAttachmentType();
        List<Attachment> list = new ArrayList<Attachment>();
        if (StringUtils.isNotBlank(businessId) && StringUtils.isNotBlank(type)) {
            list = attachmentService.getByBusinessIdAndType(businessId, type);
        } else if (StringUtils.isNotBlank(businessId)) {
            list = attachmentService.getByBusinessId(businessId);
        }
        if (list.size() == 0) {
            write("[]");
        }else{
            //转换为fine uploader 需要的格式
            for (Attachment attr: list) {
                attr.setUuid(attr.getId());
            }
            write(list);
        }
    }
    
    public void delete(){
    	String id = request.getParameter("qquuid");
        Attachment attachment = attachmentService.findById(id);
        if(StringUtils.isNotBlank(attachment.getBusinessId())){
            write(true, "id", id);
        }else{
            attachmentService.removeByIds(id);
            write(true, "id", "");
        }
    }

    public void listAttachmentByID() {
        String id = entity.getId();
        List<Attachment> list = new ArrayList<Attachment>();
        if (StringUtils.isNotBlank(id)) {
            Attachment attachment = attachmentService.findById(id);
            list.add(attachment);
        }
        if (list.size() == 0) {
            write("[]");
        }else{
            //转换为fine uploader 需要的格式
            for (Attachment attr: list) {
                attr.setUuid(attr.getId());
            }
            write(list);
        }
    }
}