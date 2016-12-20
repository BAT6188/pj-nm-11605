package com.harmonywisdom.dshbcbp.detect.action;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.detect.bean.Schedule;
import com.harmonywisdom.dshbcbp.detect.service.ScheduleService;
import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAction extends BaseAction<Schedule, ScheduleService> {
    @AutoService
    private ScheduleService scheduleService;

    @AutoService
    private AttachmentService attachmentService;

    @AutoService
    private SmsRecordService smsRecordService;

    @Override
    protected ScheduleService getService() {
        return scheduleService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String start_alertTime = request.getParameter("start_alertTime");
        String end_alertTime = request.getParameter("end_alertTime");

        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getTitle())) {
            params.andParam(new QueryParam("title", QueryOperator.LIKE,entity.getTitle()));
        }
        if (StringUtils.isNotBlank(start_alertTime)) {
            params.andParam(new QueryParam("alertTime", QueryOperator.GE, DateUtil.strToDate(start_alertTime,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotBlank(end_alertTime)) {
            params.andParam(new QueryParam("alertTime", QueryOperator.LE,DateUtil.strToDate(end_alertTime,"yyyy-MM-dd HH:mm")));
        }


        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("alertTime", Direction.DESC);
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
        super.save();
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }

        SmsRecord smsRsecord=new SmsRecord();
        smsRsecord.setSendTime(entity.getAlertTime());
        smsRsecord.setContent(entity.getRemark());
        IPerson person = ApportalUtil.getPerson(request);
        smsRsecord.setSenderId(person.getUserId());
        smsRsecord.setSenderName(person.getUserName());

        List<SmsSendStatus > receivers=new ArrayList<>();
        SmsSendStatus r=new SmsSendStatus();
        r.setReceiverId(entity.getSeterId());
        r.setReceiverName(entity.getSeter());
        r.setReceiverPhone(entity.getLinkPhone());
        r.setReceiverSource("3");
        receivers.add(r);
        try {
            smsRecordService.sendSms(smsRsecord,receivers);
        } catch (Exception e) {
            log.error("短信发送失败:"+e.getMessage(),e);
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