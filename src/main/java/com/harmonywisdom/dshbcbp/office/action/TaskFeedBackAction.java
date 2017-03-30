package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.dshbcbp.office.bean.TaskFeedBack;
import com.harmonywisdom.dshbcbp.office.service.TaskFeedBackService;
import com.harmonywisdom.dshbcbp.office.service.TaskService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class TaskFeedBackAction extends BaseAction<TaskFeedBack, TaskFeedBackService> {
    @AutoService
    private TaskFeedBackService taskFeedBackService;
    @AutoService
    private TaskService taskService;
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
        IPerson iPerson = ApportalUtil.getPerson(request);
        entity.setFeedbackerId(iPerson.getUserId());
        entity.setFeedbacker(iPerson.getUserName());
        entity.setFeedbackTime(new Date());
        super.save();
        if(StringUtils.isNotBlank(entity.getReviewStatus())){
            if(StringUtils.isNotBlank(entity.getTaskId())){
                Task task = taskService.findById(entity.getTaskId());
                task.setReviewStatus(entity.getReviewStatus());
                if(TaskFeedBack.REVIEW_STATUS_PASS.equals(entity.getReviewStatus())){//审核通过
                    task.setTaskStatus(Task.TASK_STATUS_DONE);
                }
                if(TaskFeedBack.REVIEW_STATUS_NOPASS.equals(entity.getReviewStatus())){//审核不通过
                    task.setTaskStatus(Task.TASK_STATUS_NODONE);
                }
                taskService.update(task);
            }
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
        super.delete();
        if(StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
    }
}