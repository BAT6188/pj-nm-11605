package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.port.bean.PortThreshold;
import com.harmonywisdom.dshbcbp.port.service.PortThresholdService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.dshbcbp.utils.CommonUtil;
import com.harmonywisdom.dshbcbp.utils.Constants;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class PortThresholdAction extends BaseAction<PortThreshold, PortThresholdService> {
    @AutoService
    private PortThresholdService portThresholdService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected PortThresholdService getService() {
        return portThresholdService;
    }

    @Override
    public void save() {
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        if(entity.getCreateTime()==null){
            entity.setCreateTime(new Date());
        }

        String entityId = entity.getId();
        super.save();
        IPerson iPerson = ApportalUtil.getPerson(request);
        String info = "阀值信息";
        if(StringUtils.isNotBlank(entity.getType())){
            switch (entity.getType()){
                case "ww":
                    info="废水阀值";
                    break;
                case "wg":
                    info="废气阀值";
                    break;
                case "fg":
                    info="油烟阀值";
                    break;
                default:
                    info=info;
            }
        }
        if(StringUtils.isNotBlank(entityId)){
            CommonUtil.insertBaseOpLog(iPerson.getPersonId(), Constants.OPTYPE_UPDATE,"阀值管理",info,entity.getId());
        }else{
            CommonUtil.insertBaseOpLog(iPerson.getPersonId(), Constants.OPTYPE_ADD,"阀值管理",info,entity.getId());
        }

        if(StringUtils.isNotBlank(entity.getAttachmentId())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentId().split(","));
        }
    }

}