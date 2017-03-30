package com.harmonywisdom.dshbcbp.attachment.service.impl;

import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.exception.ServiceException;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.utils.UUIDGenerator;
import com.harmonywisdom.dshbcbp.attachment.bean.Attachment;
import com.harmonywisdom.dshbcbp.attachment.dao.AttachmentDAO;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service("attachmentService")
public class AttachmentServiceImpl extends BaseService<Attachment, String> implements AttachmentService {
	
	
   private AttachmentConfigManager configManager = AttachmentConfigManager.getInstance();
	
    @Autowired
    private AttachmentDAO attachmentDAO;

    @Override
    protected BaseDAO<Attachment, String> getDAO() {
        return attachmentDAO;
    }

    @Override
    public byte[] download(String id) throws ServiceException {
        Attachment entity = attachmentDAO.findById(id);
        return entity.getData();
    }

    @Override
    public List<Attachment> getByBusinessId(String bussinessId) throws ServiceException {
        List<Attachment> attachments = attachmentDAO.queryNativeSQL("select ID,ATT_TYPE,BUSINESS_ID,FILE_NAME,FILE_EXT,FILE_PATH,FILE_PATH as DATA,FILE_SIZE from HW_ATTACHMENT where BUSINESS_ID = ?1 ",Attachment.class,bussinessId);
        return attachments;// attachmentDAO.find("entity.businessId = ?1", bussinessId);
    }

    @Override
    public List<Attachment> getByBusinessIdAndType(String bussinessId, String attachmentType) throws ServiceException {
        List<Attachment> attachments = attachmentDAO.queryNativeSQL("select ID,ATT_TYPE,BUSINESS_ID,FILE_NAME,FILE_EXT,FILE_PATH,FILE_PATH as DATA,FILE_SIZE from HW_ATTACHMENT where BUSINESS_ID = ?1 and ATT_TYPE = ?2",Attachment.class,bussinessId, attachmentType);
        return attachments;//attachmentDAO.find("entity.businessId = ?1 and entity.attachmentType = ?2", bussinessId, attachmentType);
    }
    
    @Override
    public void updateBusinessId(String businessId, String... ids) throws ServiceException {
        attachmentDAO.executeJPQL("update Attachment entity set entity.businessId = ?1 where entity.id in ?2", businessId, Arrays.asList(ids));
    }

    @Override
    public void removeByBusinessIds(String... businessIds) {
        for (String businessId : businessIds) {
            List<Attachment> attachments = getByBusinessId(businessId);

            for (Attachment attachment : attachments) {
                if(StringUtils.isNotBlank(attachment.getPath())){
                    File f = new File(attachment.getPath());    // 输入要删除的文件位置
                    if (f.exists()) {
                        f.delete();        //如果存在删除
                    }
                }
            }
        }
        attachmentDAO.executeJPQL("delete from Attachment entity where entity.businessId in ?1", Arrays.asList(businessIds));
    }
    
    @Override
    public void removeByIds(String... ids) {
        for (String id : ids) {
        	Attachment attachment = getById(id);
        	if(attachment != null && StringUtils.isNotBlank(attachment.getPath())){
                File f = new File(attachment.getPath());    // 输入要删除的文件位置
                if (f.exists()) {
                    f.delete();        //如果存在删除
                }
        	}
        }

        attachmentDAO.remove(ids);
    }
    
    @Override
    public void copy(String oldbusinessIds , String newbusinessIds){ 
		try {
			List<Attachment> attachments = getByBusinessId(oldbusinessIds);
	    	for(Attachment attachment : attachments){
	    		 
	    		 File oldFile = new File(attachment.getPath());
	    		 File cacheFile = new File(oldFile.getParentFile().getAbsolutePath(), UUIDGenerator.generateUUID());
                 FileUtils.copyInputStreamToFile(new FileInputStream(oldFile), cacheFile);
                 
				 Attachment act = new Attachment();
				 act.setBusinessId(newbusinessIds);
				 act.setPath(cacheFile.getAbsolutePath());
				 act.setData(attachment.getData());
				 act.setName(attachment.getName());
				 act.setExt(attachment.getExt());
				 act.setSize(attachment.getSize());
				 act.setAttachmentType(attachment.getAttachmentType());
				 attachmentDAO.save(act);
	    	}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 根据ID获取不带data字段的文件对象
     * @param Id
     * @return
     * @throws ServiceException
     */
    public Attachment getById(String Id) throws ServiceException {
        List<Attachment> attachments = attachmentDAO.queryNativeSQL("select ID,ATT_TYPE,BUSINESS_ID,FILE_NAME,FILE_EXT,FILE_PATH,FILE_PATH as DATA,FILE_SIZE from HW_ATTACHMENT where ID = ?1 ",Attachment.class,Id);
        if(attachments.size()>0){
            return attachments.get(0);
        }else{
            return null;
        }
    }
}