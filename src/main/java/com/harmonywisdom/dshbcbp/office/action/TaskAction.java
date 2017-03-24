package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.dshbcbp.office.service.TaskService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.LinkedHashMap;

public class TaskAction extends BaseAction<Task, TaskService> {
    @AutoService
    private TaskService taskService;
    @AutoService
    private AttachmentService attachmentService;
    @Override
    protected TaskService getService() {
        return taskService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();

        String mobileOperType = request.getParameter("mobileOperType");

        if(StringUtils.isNotBlank(entity.getTaskType())){
            param.andParam(new QueryParam("taskType", QueryOperator.EQ,entity.getTaskType()));
        }
        if(StringUtils.isNotBlank(entity.getParentTaskId())){
            param.andParam(new QueryParam("parentTaskId", QueryOperator.EQ,entity.getParentTaskId()));
        }
        if(StringUtils.isNotBlank(entity.getParentTaskName())){
            param.andParam(new QueryParam("parentTaskName", QueryOperator.LIKE,"%"+entity.getParentTaskName()+"%"));
        }
        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }

        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("sort", Direction.DESC);
        LinkedHashMap<String, Direction> orders = new LinkedHashMap<>();
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
            //orders.put("mobileTimestamp",Direction.DESC);
        }
        return condition;
    }

    @Override
    public void save() {
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        if(StringUtils.isBlank(entity.getId())){
            IPerson iPerson = ApportalUtil.getPerson(request);
            entity.setTaskCreatorId(iPerson.getUserId());
            entity.setTaskCreator(iPerson.getUserName());
            entity.setTaskCreateTime(new Date());
        }
        super.save();
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