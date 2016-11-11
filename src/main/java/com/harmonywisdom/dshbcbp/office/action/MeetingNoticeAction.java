package com.harmonywisdom.dshbcbp.office.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.office.bean.MeetingNotice;
import com.harmonywisdom.dshbcbp.office.service.MeetingNoticeService;
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
    private AttachmentService attachmentService;

    @Override
    protected MeetingNoticeService getService() {
        return meetingNoticeService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();
        if (StringUtils.isNotBlank(entity.getTitle())) {
            param.andParam(new QueryParam("title", QueryOperator.LIKE,entity.getTitle()));
        }
        String pubTime = request.getParameter("time");
        if (StringUtils.isNotBlank(pubTime)) {
            param.andParam(new QueryParam("time", QueryOperator.EQ, DateUtil.strToDate(pubTime,"yyyy-MM-dd")));
        }
        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("time", Direction.DESC);
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
        String id=request.getParameter("id");
        if(id!=null){
            MeetingNotice mn = meetingNoticeService.findByObjectId(id);
            String[] ids = request.getParameterValues("ids");
            String[] names= request.getParameterValues("names");
            String jsonIds = JSON.toJSONString(ids);
            String jsonNames = JSON.toJSONString(names);
            entity.setId(id);
            entity.setAddress(mn.getAddress());
            entity.setContent(mn.getContent());
            entity.setCreateTime(mn.getCreateTime());
            entity.setIsSms(mn.getIsSms());
            entity.setLinkMan(mn.getLinkMan());
            entity.setLinkPhone(mn.getLinkPhone());
            entity.setPubOrgId(mn.getPubOrgId());
            entity.setPubOrgName(entity.getPubOrgName());
            entity.setPersonIds(jsonIds);
            entity.setPersonNames(jsonNames);
        }
        super.save();
        if (org.apache.commons.lang.StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
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