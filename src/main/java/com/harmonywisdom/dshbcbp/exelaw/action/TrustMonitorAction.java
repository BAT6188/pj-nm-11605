package com.harmonywisdom.dshbcbp.exelaw.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.dispatch.action.DispatchTaskAction;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.exelaw.bean.TrustMonitor;
import com.harmonywisdom.dshbcbp.exelaw.service.TrustMonitorService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class TrustMonitorAction extends BaseAction<TrustMonitor, TrustMonitorService> {
    @AutoService
    private TrustMonitorService trustMonitorService;

    @AutoService
    private AttachmentService attachmentService;

    @AutoService
    private EnterpriseService enterpriseService;

    @Override
    protected TrustMonitorService getService() {
        return trustMonitorService;
    }

    /**
     * 监察大队
     */
    public void updateSelfReadStatusForJianchadadui(){
        String id = request.getParameter("id");
        String selfReadStatus = request.getParameter("selfReadStatus");
        TrustMonitor t = trustMonitorService.findById(id);
        t.setSelfReadStatusForJianchadadui(selfReadStatus);
        trustMonitorService.update(t);
        write("ok");
    }

    /**
     * 监测站办公室
     */
    public void updateSelfReadStatusForMonitorOffice(){
        String id = request.getParameter("id");
        String selfReadStatus = request.getParameter("selfReadStatus");
        TrustMonitor t = trustMonitorService.findById(id);
        t.setSelfReadStatusForMonitorOffice(selfReadStatus);
        trustMonitorService.update(t);
        write("ok");
    }

    /**
     * 监测站站长
     */
    public void updateSelfReadStatusForMonitorMaster(){
        String id = request.getParameter("id");
        String selfReadStatus = request.getParameter("selfReadStatus");
        TrustMonitor t = trustMonitorService.findById(id);
        t.setSelfReadStatusForMonitorMaster(selfReadStatus);
        trustMonitorService.update(t);
        write("ok");
    }

    /**
     * 监测站人员
     */
    public void updateSelfReadStatusForMonitorPerson(){
        String id = request.getParameter("id");
        String selfReadStatus = request.getParameter("selfReadStatus");
        TrustMonitor t = trustMonitorService.findById(id);
        t.setSelfReadStatusForMonitorPerson(selfReadStatus);
        trustMonitorService.update(t);
        write("ok");
    }

    /**
     * 保存企业委托监测数据
     */
    public void saveEnterpriseSelfData(){
        String id = request.getParameter("trustMonitorId");
        entity.setSelfReadStatusForMonitorMaster("1");
        IPerson person = ApportalUtil.getPerson(request);
        String[] ids = {person.getPersonId()};
        String[] names = {person.getUserName()};
        entity.setMonitoringStationMasterPersonList(JSON.toJSONString(ids));
        entity.setMonitoringStationMasterPersonNameList(DispatchTaskAction.arrayToString(names));

        if (StringUtils.isNotEmpty(id)){
            entity.setId(id);
            trustMonitorService.update(entity);
        }else {
            trustMonitorService.save(entity);
        }

        write(entity);
    }

    /**
     * 保存监测站站长人员列表
     */
    public void saveToMonitoringStationMasterPersonList(){
        String sourceId = request.getParameter("sourceId");
        TrustMonitor trustMonitor = trustMonitorService.findById(sourceId);
        trustMonitor.setStatus("5");

        String[] personIds = getParamValues("ids");
        trustMonitor.setMonitoringStationMasterPersonList(JSON.toJSONString(personIds));

        String[] names = getParamValues("names");
        trustMonitor.setMonitoringStationMasterPersonNameList(DispatchTaskAction.arrayToString(names));

        String pk = trustMonitorService.saveOrUpdate(trustMonitor);
        write(pk);
    }

    /**
     * 保存监测站检测人员列表
     */
    public void saveToMonitoringStationPersonList(){
        String sourceId = request.getParameter("sourceId");
        TrustMonitor trustMonitor = trustMonitorService.findById(sourceId);
        trustMonitor.setStatus("6");

        String[] personIds = getParamValues("ids");
        trustMonitor.setMonitoringStationPersonList(JSON.toJSONString(personIds));

        String[] names = getParamValues("names");
        trustMonitor.setMonitoringStationPersonNameList(DispatchTaskAction.arrayToString(names));

        String pk = trustMonitorService.saveOrUpdate(trustMonitor);
        write(pk);
    }

    /**
     * 保存监测站办公室人员列表
     */
    public void saveToMonitorOfficeAndMasterPersonList(){
        String sourceId = request.getParameter("sourceId");
        TrustMonitor trustMonitor = trustMonitorService.findById(sourceId);
        trustMonitor.setAuditSuggestionStatus("1");
        trustMonitor.setStatus("2");
        trustMonitor.setAuditor(entity.getAuditor());

        String[] personIds = getParamValues("ids");
        trustMonitor.setMonitoringStationOfficePersonList(JSON.toJSONString(personIds));

        String[] names = getParamValues("names");
        trustMonitor.setMonitoringStationOfficePersonNameList(DispatchTaskAction.arrayToString(names));

        String pk = trustMonitorService.saveOrUpdate(trustMonitor);
        write(pk);
    }

    /**
     * 保存 监察大队领导 人员列表
     */
    public void saveToEnvironmentalProtectionStationSelectPersonList(){
        String sourceId = request.getParameter("sourceId");
        TrustMonitor trustMonitor = trustMonitorService.findById(sourceId);
        trustMonitor.setStatus("1");

        String[] personIds = getParamValues("ids");
        trustMonitor.setEnvironmentalProtectionStationSelectPersonList(JSON.toJSONString(personIds));

        String[] names = getParamValues("names");
        trustMonitor.setEnvironmentalProtectionStationSelectPersonNameList(DispatchTaskAction.arrayToString(names));

        String pk = trustMonitorService.saveOrUpdate(trustMonitor);
        write(pk);
    }

    /**
     * 保存审批不同意表单
     */
    public void saveNotAgreeForm(){
        String id = request.getParameter("trustMonitorId");
        TrustMonitor trustMonitor = trustMonitorService.findById(id);
        trustMonitor.setAuditSuggestionStatus("2");
        trustMonitor.setStatus("3");
        trustMonitor.setAuditor(entity.getAuditor());
        trustMonitor.setAuditTime(entity.getAuditTime());
        trustMonitor.setAuditPosition(entity.getAuditPosition());
        trustMonitor.setAuditorPhone(entity.getAuditorPhone());
        trustMonitor.setAuditSuggestion(entity.getAuditSuggestion());

        String pk = trustMonitorService.saveOrUpdate(trustMonitor);
        write(pk);
    }

    /**
     * 监察大队领导 接收委托监测表单 点同意按钮（只须保存附件）
     */
    public void saveAndAgreeAndSend(){
        String id = entity.getId();
        TrustMonitor t = trustMonitorService.findById(id);
        t.setAuditor(entity.getAuditor());
        t.setAuditTime(entity.getAuditTime());
        t.setAuditPosition(entity.getAuditPosition());
        t.setAuditorPhone(entity.getAuditorPhone());
        t.setAuditSuggestion(entity.getAuditSuggestion());
        trustMonitorService.update(t);

        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }

        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(id,entity.getAttachmentIds().split(","));
        }

        write(t);
    }

    /**
     * 申请委托监测 环保站人员点击申请委托监测表单上的发送按钮
     */
    @Override
    public void save() {
        String enterpriseId = entity.getEnterpriseId();
        if (StringUtils.isNotEmpty(enterpriseId)){
            Enterprise enterprise = enterpriseService.findById(enterpriseId);
            entity.setBlockLevelId(enterprise.getBlockLevelId());
            entity.setBlockLevelName(enterprise.getBlockLevelName());
            entity.setBlockId(enterprise.getBlockId());
            entity.setBlockName(enterprise.getBlockName());
        }

        String applyOrgId = entity.getApplyOrgId();
        if (StringUtils.isNotEmpty(applyOrgId)){
            IOrg o = OrgServiceUtil.getOrgByOrgId(applyOrgId);
            entity.setApplyOrg(o.getOrgName());
        }


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
    }

    /**
     * 加载环保站列表
     */
    public void getEnvironmentalProtectionStationList(){
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId("402883b3577422f00157f9d874d103e9");
        write(orgs);
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String module = request.getParameter("module");
        String enterpriseSelf = request.getParameter("enterpriseSelf");
        if (StringUtils.isEmpty(enterpriseSelf)){
            enterpriseSelf="0";
        }

        String start_monitorTime = request.getParameter("start_monitorTime");
        String end_monitorTime = request.getParameter("end_monitorTime");
        IPerson person = ApportalUtil.getPerson(request);

        QueryParam params = new QueryParam();
        //TODO 以下注释的代码调试的时候用，正式测试、部署需要放开注释
        if ("receiveTrustMonitor".equals(module)){//监察大队接收委托监测页面
            params.andParam(new QueryParam("environmentalProtectionStationSelectPersonList", QueryOperator.LIKE,"%"+person.getPersonId()+"%"));
        }else if ("monitoring_station_office".equals(module)){//监测站办公室页面
            params.andParam(new QueryParam("monitoringStationOfficePersonList", QueryOperator.LIKE,"%"+person.getPersonId()+"%"));
        }else if ("monitoring_station_master".equals(module)){//监测站站长页面
            params.andParam(new QueryParam("monitoringStationMasterPersonList", QueryOperator.LIKE,"%"+person.getPersonId()+"%"));
        }else if ("monitoring_station_person".equals(module)){//监测站检测员页面
            params.andParam(new QueryParam("monitoringStationPersonList", QueryOperator.LIKE,"%"+person.getPersonId()+"%"));
        }


        params.andParam(new QueryParam("enterpriseSelf", QueryOperator.EQ,enterpriseSelf));
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }

        if (StringUtils.isNotBlank(start_monitorTime)) {
            params.andParam(new QueryParam("monitorTime", QueryOperator.GE, DateUtil.strToDate(start_monitorTime,"yyyy-MM-dd HH:mm")));
        }

        if (StringUtils.isNotBlank(end_monitorTime)) {
            params.andParam(new QueryParam("monitorTime", QueryOperator.LE,DateUtil.strToDate(end_monitorTime,"yyyy-MM-dd HH:mm")));
        }

        if (StringUtils.isNotBlank(entity.getApplyOrgId())) {
            params.andParam(new QueryParam("applyOrgId", QueryOperator.EQ,entity.getApplyOrgId()));
        }

        if (StringUtils.isNotBlank(entity.getBlockLevelId())) {
            params.andParam(new QueryParam("blockLevelId", QueryOperator.EQ,entity.getBlockLevelId()));
        }

        if (StringUtils.isNotBlank(entity.getBlockId())) {
            params.andParam(new QueryParam("blockId", QueryOperator.EQ,entity.getBlockId()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("monitorTime", Direction.DESC);
        return condition;
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

    public void saveFeedback(){
        TrustMonitor t = trustMonitorService.findById(entity.getId());
        t.setMonitor(entity.getMonitor());
        t.setMonitorPhone(entity.getMonitorPhone());
        t.setFeedbackContent(entity.getFeedbackContent());
        t.setStatus("7");
        trustMonitorService.update(t);

        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("trustMonitorRemoveId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }

        write(entity.getId());
    }


}