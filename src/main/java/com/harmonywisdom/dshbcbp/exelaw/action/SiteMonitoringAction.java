package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.apportal.sdk.person.domain.Person;
import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.DispatchTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.exelaw.bean.SiteMonitoring;
import com.harmonywisdom.dshbcbp.exelaw.service.SiteMonitoringService;
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

public class SiteMonitoringAction extends BaseAction<SiteMonitoring, SiteMonitoringService> {
    @AutoService
    private SiteMonitoringService siteMonitoringService;

    @AutoService
    private DispatchTaskService dispatchTaskService;

    @AutoService
    private MonitorCaseService monitorCaseService;

    @AutoService
    private MessageService messageService;


    @AutoService
    private AttachmentService attachmentService;

    @AutoService
    private BlockLevelService blockLevelService;

    @AutoService
    private BlockService blockService;

    @AutoService
    private EnterpriseService enterpriseService;


    @Override
    protected SiteMonitoringService getService() {
        return siteMonitoringService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String mobileOperType = request.getParameter("mobileOperType");
        QueryParam params = new QueryParam();
        if(StringUtils.isNotBlank(entity.getDispatchId())){
            params.andParam(new QueryParam("dispatchId", QueryOperator.EQ,entity.getDispatchId()));
        }
        if(StringUtils.isNotBlank(entity.getEnterpriseId())){
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }
        if(StringUtils.isNotBlank(entity.getCheckPeople())){
            params.andParam(new QueryParam("checkPeople",QueryOperator.LIKE,entity.getCheckPeople()));
        }

        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                params.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                params.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }

        // TODO 筛选数据
//        IPerson p = PersonServiceUtil.getPersonByUserId(entity.getUserId());
//        String orgId = p.getOrgId();
//        List<Person>  ps = PersonServiceUtil.getPersonByOrgId(orgId);
//        List<Person>  persons =PersonServiceUtil.getPersonByOrgId("402883b358849ce10158f6ae2a2f04e2");//大队领导
//        ps.addAll(persons);



        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("monitoringTime", Direction.DESC);
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }



    @Override
    public void save() {
        String enterpriseId = entity.getEnterpriseId();
        if (StringUtils.isNotEmpty(enterpriseId)){
            Enterprise e = enterpriseService.findById(enterpriseId);
            entity.setEnterpriseName(e.getName());
            entity.setBlockLevelId(e.getBlockLevelId());
            entity.setBlockId(e.getBlockId());
        }


        String dispatchId = entity.getDispatchId();
        if (StringUtils.isNotEmpty(dispatchId)){
            DispatchTask dispatchTask = dispatchTaskService.findById(dispatchId);
            dispatchTask.setMonitorReportStatus("1");
            dispatchTaskService.update(dispatchTask);

            MonitorCase m=new MonitorCase();
            m.setDispatchId(dispatchId);
            List<MonitorCase> bySample = monitorCaseService.findBySample(m);
            if (bySample.size()>0){
                MonitorCase monitorCase = bySample.get(0);
                monitorCase.setMonitorReportStatus("1");
                monitorCaseService.update(monitorCase);
            }
        }

        if (StringUtils.isNotEmpty(entity.getXuBao())){
            entity.setIs_over("1");
        }else {
            entity.setIs_over("0");
        }

        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }

        String blockLevelId = entity.getBlockLevelId();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(blockLevelId)){
            BlockLevel bl = blockLevelService.findById(blockLevelId);
            entity.setBlockLevelName(bl.getName());
        }

        String blockId = entity.getBlockId();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(blockId)){
            Block b = blockService.findById(blockId);
            entity.setBlockName(b.getOrgName());
        }



        super.save();
        if(StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));

        }

        //发送系统消息
        Message message=new Message();
        message.setMsgType("14");//反馈给环保站人员
        message.setTitle("现场监察信息");
        message.setContent(entity.getSendRemark());
        message.setBusinessId(entity.getId());
        String userId = entity.getUserId();
        if(StringUtils.isEmpty(userId)){
            IPerson person = ApportalUtil.getPerson(request);
            userId=person.getUserId();
        }
        message.setSenderId(userId);
        message.setSenderName(entity.getCheckPeople());

        IPerson p = PersonServiceUtil.getPersonByUserId(userId);
        String orgId = p.getOrgId();
        List<Person>  ps = PersonServiceUtil.getPersonByOrgId(orgId);
        List<Person>  persons =PersonServiceUtil.getPersonByOrgId("402883b358849ce10158f6ae2a2f04e2");//大队领导
        ps.addAll(persons);
        message.setDetailsUrl("container/gov/exelaw/siteMonitoring.jsp");
        List<MessageTrace> receivers=new ArrayList<>();
        for (Person pe : ps) {
            MessageTrace re=new MessageTrace();
            re.setReceiverId(pe.getUserId());
            re.setReceiverName(pe.getUserName());
            receivers.add(re);
        }
        messageService.sendMessage(message,receivers);
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