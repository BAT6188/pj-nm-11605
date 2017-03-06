package com.harmonywisdom.dshbcbp.appclient.action;

import com.alibaba.fastjson.JSONObject;
import com.harmonywisdom.dshbcbp.appclient.bean.AppClient;
import com.harmonywisdom.dshbcbp.appclient.service.AppClientService;
import com.harmonywisdom.dshbcbp.attachment.bean.Attachment;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

public class AppClientAction extends BaseAction<AppClient, AppClientService> {
    @AutoService
    private AppClientService appClientService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected AppClientService getService() {
        return appClientService;
    }

    @Override
    public void save() {
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        entity.setUpTime(new Date());
        entity.setUploadStatus("0");

        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            Attachment attachment = attachmentService.findById(entity.getAttachmentIds());
            if(attachment!=null){
                //entity.setApkBytes(attachment.getData());
                entity.setAttachmentId(entity.getAttachmentIds());
                entity.setApkSize(attachment.getSize());
                entity.setUploadStatus("1");
                entity.setApkUrl("/action/S_attachment_Attachment_download.action?id="+entity.getAttachmentIds());
                entity.setFilePath(attachment.getPath());
            }
            attachmentService.updateBusinessId(String.valueOf(entity.getApkVersionNum()),entity.getAttachmentIds().split(","));
        }else{
            List<Attachment> attachmentList = attachmentService.getByBusinessId(String.valueOf(entity.getApkVersionNum()));
            if(attachmentList.size()>0){
                entity.setUploadStatus("1");
            }
        }
        super.save();
    }

    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }

    public void checkApkVersion(){
        String varsionNumber = request.getParameter("apkVersionNum");
        JSONObject returnObj = new JSONObject();
        if(StringUtils.isNotBlank(varsionNumber)){
            AppClient apk = appClientService.findNewestApk();
            Integer vNumber = apk.getApkVersionNum();
            if(vNumber>Integer.parseInt(varsionNumber)){
                returnObj.put("flag",true);
                returnObj.put("msg","有新版本,请及时更新！");
                returnObj.put("apkData",apk);
            }else{
                returnObj.put("flag",false);
                returnObj.put("msg","当前版本为最新版本！");
            }
        }else{
            returnObj.put("flag",false);
            returnObj.put("msg","未接收到apkVersionNum(版本号)");
        }
        write(returnObj);
    }
}