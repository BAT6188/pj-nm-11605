package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.TaskFeedBack;
import com.harmonywisdom.dshbcbp.office.service.TaskFeedBackService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class TaskFeedBackAction extends BaseAction<TaskFeedBack, TaskFeedBackService> {
    @AutoService
    private TaskFeedBackService taskFeedBackService;
    @AutoService
    private AttachmentService attachmentService;
    @AutoService
    private MessageService messageService;

    @Override
    protected TaskFeedBackService getService() {
        return taskFeedBackService;
    }

    @Override
    public void save() {

        String removeIds = request.getParameter("removeId");
        if(StringUtils.isNotBlank(removeIds)){
            attachmentService.removeByIds(removeIds.split(","));
        }
        entity.setFeedbackTime(new Date());
        super.save();
        if(StringUtils.isNotBlank(entity.getReviewStatus()) && "1".equals(entity.getReviewStatus())){

        }
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
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
}