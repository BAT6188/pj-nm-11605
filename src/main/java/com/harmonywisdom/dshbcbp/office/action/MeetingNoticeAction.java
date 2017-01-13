package com.harmonywisdom.dshbcbp.office.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.office.bean.MeetingNotice;
import com.harmonywisdom.dshbcbp.office.service.MeetingNoticeService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class MeetingNoticeAction extends BaseAction<MeetingNotice, MeetingNoticeService> {
    @AutoService
    private MeetingNoticeService meetingNoticeService;
    @AutoService
    private MessageService messageService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected MeetingNoticeService getService() {
        return meetingNoticeService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();

        String mobileOperType = request.getParameter("mobileOperType");

        if (StringUtils.isNotBlank(entity.getTitle())) {
            param.andParam(new QueryParam("title", QueryOperator.LIKE,entity.getTitle()));
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if (StringUtils.isNotEmpty(startTime)){
            param.andParam(new QueryParam("time", QueryOperator.GE, DateUtil.strToDate(startTime,DateUtil.format1)));
        }
        if (StringUtils.isNotEmpty(endTime)){
            param.andParam(new QueryParam("time", QueryOperator.LE, DateUtil.strToDate(endTime,DateUtil.format1)));
        }

        IPerson person = ApportalUtil.getPerson(request);
        if (null!=person){
            param.andParam(new QueryParam("personIds", QueryOperator.LIKE, "%\\\""+person.getPersonId()+"\\\"%"));
        }else {
            String personId = request.getParameter("personId");
            param.andParam(new QueryParam("personIds", QueryOperator.LIKE, "%\\\""+personId+"\\\"%"));
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
        condition.setOrderBy("time", Direction.DESC);
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }
    @Override
    public void save() {
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(org.apache.commons.lang.StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        super.save();
        if (org.apache.commons.lang.StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
        write(true);
    }
    //更新会议人员ids和names
    public void updateMeeting(){
        String id=request.getParameter("id");
        String[] ids = request.getParameterValues("ids");
        String[] names= request.getParameterValues("names");
        if(id!=null && ids!=null&& names!=null){
            String jsonIds = JSON.toJSONString(ids);
            String jsonNames = JSON.toJSONString(names);
            meetingNoticeService.updateMeeting(jsonIds,jsonNames,id);
        }
        write(true);
    }
    //更新短信发送状态
    public void updateMeetingIsSms(){
        String id=request.getParameter("sourceId");
        if(id!=null){
            meetingNoticeService.updateMeetingIsSms(id);
        }
        write(true);
    }


    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(org.apache.commons.lang.StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }
}