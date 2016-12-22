package com.harmonywisdom.dshbcbp.dispatch.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.person.IPerson;
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
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class DispatchTaskAction extends BaseAction<DispatchTask, DispatchTaskService> {

    public static final String monitor_master="monitor_master";
    public static final String env_pro_sta="env_pro_sta";

    @AutoService
    private MessageService messageService;

    @AutoService
    private BlockService blockService;

    @AutoService
    private BlockLevelService blockLevelService;

    @AutoService
    private DispatchTaskService dispatchTaskService;

    @AutoService
    private MonitorCaseService monitorCaseService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected DispatchTaskService getService() {
        return dispatchTaskService;
    }

    /**
     * 保存 现场监察监测报告
     */
    public  void saveMonitorReport(){
        String id = entity.getId();
        DispatchTask d = dispatchTaskService.findById(id);
        d.setMonitorReportStatus("0");
        d.setSubmitPerson(entity.getSubmitPerson());
        d.setSubmitPersonPhone(entity.getSubmitPersonPhone());
        d.setMonitorReportRemark(entity.getMonitorReportRemark());

        d.setContent(entity.getContent());
        d.setSendRemark(entity.getSendRemark());
        d.setUpdateTime(new Date());
        dispatchTaskService.update(d);

        if (org.apache.commons.lang.StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(id,entity.getAttachmentIds().split(","));
        }
        write(id);
    }

    /**
     * 监察大队查看状态
     */
    public void updateMonitorMasterSelfReadStatus(){
        String id = request.getParameter("id");
        String selfReadStatus = request.getParameter("selfReadStatus");
        DispatchTask d = dispatchTaskService.findById(id);
        d.setMonitorMasterSelfReadStatus(selfReadStatus);
        dispatchTaskService.update(d);
        write("ok");
    }

    /**
     * 环保站查看状态
     */
    public void updateHuanBaoZhanSelfReadStatus(){
        String id = request.getParameter("id");
        String selfReadStatus = request.getParameter("selfReadStatus");
        DispatchTask d = dispatchTaskService.findById(id);
        d.setHuanBaoZhanSelfReadStatus(selfReadStatus);
        dispatchTaskService.update(d);
        write("ok");
    }


    /**
     * 保存现场监察
     */
    public void saveXianChangJianChaAjax(){
        entity.setSource("4");
        entity.setHuanBaoZhanSelfReadStatus("1");

        IPerson person = ApportalUtil.getPerson(request);
        String[] ids={person.getPersonId()};
        entity.setEnvProStaPersonList(arrayToString(ids,true));

        String blockLevelId = entity.getBlockLevelId();
        String blockId = entity.getBlockId();
        if (StringUtils.isNotEmpty(blockLevelId)){
            BlockLevel blockLevel = blockLevelService.findById(blockLevelId);
            entity.setBlockLevelName(blockLevel.getName());
        }
        if (StringUtils.isNotEmpty(blockId)){
            Block block = blockService.findById(blockId);
            entity.setBlockName(block.getOrgName());
        }
        entity.setUpdateTime(new Date());
        super.save();
    }


    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();

        /**
         * 两种角色：
         * 1. monitor_master  监察大队领导
         * 2. env_pro_sta  环保站
         */
        String role = request.getParameter("role");
        IPerson person = ApportalUtil.getPerson(request);
        if (StringUtils.isNotEmpty(role)){
            if (monitor_master.equals(role)){
//                params=new QueryParam("monitorMasterPersonList", QueryOperator.LIKE, "%\""+person.getPersonId()+"\"%");
            }else if (env_pro_sta.equals(role)){
                params= new QueryParam("envProStaPersonList", QueryOperator.LIKE, "%\""+person.getPersonId()+"\"%");
            }

        }
        if(StringUtils.isNotBlank(entity.getMonitorReportStatus())){
            params.andParam(new QueryParam("monitorReportStatus", QueryOperator.EQ, entity.getMonitorReportStatus()));
        }
        String startEventTime = request.getParameter("startEventTime");
        String endEventTime = request.getParameter("endEventTime");
        String isPunish = request.getParameter("isPunish");

        String enterpriseName = entity.getEnterpriseName();
        String source = entity.getSource();
        String status = entity.getStatus();
        String blockLevelId = entity.getBlockLevelId();
        String blockId = entity.getBlockId();


        if (org.apache.commons.lang.StringUtils.isNotBlank(startEventTime)) {
            params.andParam(new QueryParam("eventTime", QueryOperator.GE, DateUtil.strToDate(startEventTime,"yyyy-MM-dd HH:mm")));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(endEventTime)) {
            params.andParam(new QueryParam("eventTime", QueryOperator.LE, DateUtil.strToDate(endEventTime,"yyyy-MM-dd HH:mm")));
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(enterpriseName)) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE, "%"+enterpriseName+"%"));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(source)) {
            params.andParam(new QueryParam("source", QueryOperator.EQ, source));
        }
        if ("1".equals(isPunish)){
            params.andParam(new QueryParam("status", QueryOperator.NE, "5"));
        }else {
            if (org.apache.commons.lang.StringUtils.isNotBlank(status)) {
                params.andParam(new QueryParam("status", QueryOperator.EQ, status));
            }
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(blockLevelId)) {
            params.andParam(new QueryParam("blockLevelId", QueryOperator.EQ, blockLevelId));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(blockId)) {
            params.andParam(new QueryParam("blockId", QueryOperator.EQ, blockId));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(entity.getId())) {
            params.andParam(new QueryParam("id", QueryOperator.EQ, entity.getId()));
        }
        String firstTime = request.getParameter("firstTime");
        String lastTime = request.getParameter("lastTime");
        if (org.apache.commons.lang.StringUtils.isNotBlank(firstTime)) {
            params.andParam(new QueryParam("eventTime", QueryOperator.GE, DateUtil.strToDate(firstTime,"yyyy-MM-dd")));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(lastTime)) {
            params.andParam(new QueryParam("eventTime", QueryOperator.LE, DateUtil.strToDate(lastTime,"yyyy-MM-dd")));
        }
        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("updateTime", Direction.DESC);
        return condition;
    }

    /**
     * 执法同期对比查询列表
     */
    public void listgridLawRatio(){

        Map<String,String> params = new HashMap<>();


        String firstTime = request.getParameter("firstTime");
        if(org.apache.commons.lang.StringUtils.isNotBlank(firstTime)){
            params.put("firstTime",firstTime);
        }
        String lastTime = request.getParameter("lastTime");
        if(org.apache.commons.lang.StringUtils.isNotBlank(lastTime)){
            params.put("lastTime",lastTime);
        }
        String lastStartTime = request.getParameter("lastStartTime");
        if(org.apache.commons.lang.StringUtils.isNotBlank(lastStartTime)){
            params.put("lastStartTime",lastStartTime);

        }
        String lastEndTime = request.getParameter("lastEndTime");
        if(org.apache.commons.lang.StringUtils.isNotBlank(lastEndTime)){
            params.put("lastEndTime",lastEndTime);
        }

        QueryResult<DispatchTask>  result = null;
        result = dispatchTaskService.lawRatiogrid(params, getPaging());

        write(result);



    }

    /**
     * 办结管理
     */
    public void overStatus(){
        DispatchTask dt = dispatchTaskService.findById(entity.getId());
        dt.setOverSuggestion(entity.getOverSuggestion());
        dt.setStatus("5");
        dt.setOverTime(new Date());
        dispatchTaskService.update(dt);

    }

    /**
     * 点处置按钮
     */
    public void dispathTaskBtnSave(){
        String id = entity.getId();
        DispatchTask dispatchTask = dispatchTaskService.findById(id);
//        dispatchTask.setContent(entity.getContent());
//        dispatchTask.setSendRemark(entity.getSendRemark());
        dispatchTask.setDispatchPersonName(entity.getDispatchPersonName());
        dispatchTask.setDispatchTime(entity.getDispatchTime());
        dispatchTask.setDispatchContent(entity.getDispatchContent());

        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(org.apache.commons.lang.StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(dispatchTask.getMonitorCaseId(),entity.getAttachmentIds().split(","));
        }

        dispatchTaskService.update(dispatchTask);

        write(dispatchTask.getId());
    }

    public void saveToMonitorMasterPersonNameList(){
        String dispathTaskId = request.getParameter("sourceId");
        DispatchTask dispatchTask = dispatchTaskService.findById(dispathTaskId);

        String[] ids = this.getParamValues("ids");
        dispatchTask.setMonitorMasterPersonList(JSON.toJSONString(ids));

        String[] names = getParamValues("names");
        dispatchTask.setMonitorMasterPersonNameList(arrayToString(names,false));
        dispatchTask.setUpdateTime(new Date());
        dispatchTaskService.update(dispatchTask);
    }

    /**
     * 选择环保站或污控室人员，点发送按钮
     */
    public void saveToEnvProStaPersonList(){
        String dispathTaskId = request.getParameter("sourceId");
        DispatchTask dispatchTask = dispatchTaskService.findById(dispathTaskId);

        String[] ids = this.getParamValues("ids");
        String envProStaPersonList = dispatchTask.getEnvProStaPersonList();
        if (null!=envProStaPersonList){
            envProStaPersonList+=","+DispatchTaskAction.arrayToString(ids,true);
        }else {
            envProStaPersonList=DispatchTaskAction.arrayToString(ids,true);
        }
        dispatchTask.setEnvProStaPersonList(envProStaPersonList);

        String[] names = getParamValues("names");
        String envProStaPersonNameList = dispatchTask.getEnvProStaPersonNameList();
        if (null!=envProStaPersonNameList){
            envProStaPersonNameList+=","+DispatchTaskAction.arrayToString(names,false);
        }else {
            envProStaPersonNameList=DispatchTaskAction.arrayToString(names,false);
        }
        dispatchTask.setEnvProStaPersonNameList(envProStaPersonNameList);

        dispatchTask.setStatus("2");
        dispatchTask.setSendTime(new Date());
        dispatchTask.setUpdateTime(new Date());

        String pk = this.getService().saveOrUpdate(dispatchTask);
        write(pk);
    }

    public static String  arrayToString(String[] arr,boolean isEscape){
        String ret="";
        if (null!=arr){
            if (arr.length>0){
                for (String s : arr) {
                    if (isEscape){
                        ret+="\""+s+"\"，";
                    }else {
                        ret+=s+"，";
                    }

                }
                ret=ret.substring(0,ret.length()-1);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
    }

    /**
     * 监控中心，监控办公室的调度单选择人员，点发送按钮
     */
    @Override
    public void save() {
        String monitorCaseId = request.getParameter("sourceId");
        MonitorCase mc = monitorCaseService.findByObjectId(monitorCaseId);

        String[] ids = this.getParamValues("ids");
        String jsonIds = JSON.toJSONString(ids);
        entity.setMonitorMasterPersonList(jsonIds);

        entity.setMonitorCaseId(monitorCaseId);
        entity.setEnterpriseId(mc.getEnterpriseId());
        entity.setEnterpriseName(mc.getEnterpriseName());
        entity.setSource(mc.getSource());
        entity.setEventTime(mc.getEventTime());
        entity.setBlockLevelId(mc.getBlockLevelId());
        entity.setBlockLevelName(mc.getBlockLevelName());
        entity.setBlockId(mc.getBlockId());
        entity.setBlockName(mc.getBlockName());
        entity.setAnswer(mc.getAnswer());
        entity.setSupervisor(mc.getSupervisor());
        entity.setSupervisorPhone(mc.getSupervisorPhone());
        entity.setReason(mc.getReason());
        entity.setOverValue(mc.getOverValue());
        entity.setThrValue(mc.getThrValue());
        entity.setContent(mc.getContent());
        entity.setSenderId(mc.getSenderId());
        entity.setSenderName(mc.getSenderName());
        entity.setSendTime(mc.getSendTime());
        entity.setSendPhone(mc.getSendPhone());
        entity.setSendRemark(mc.getSendRemark());
        entity.setStatus(DispatchTask.status_1);
        entity.setUpdateTime(new Date());
        super.save();


        mc.setMonitorOfficePersonId(jsonIds);
        String[] names = this.getParamValues("names");
        mc.setMonitorOfficePersonName(arrayToString(names,false));
        mc.setDispatchId(entity.getId());
        mc.setStatus(MonitorCase.status_1);
        mc.setReceiveStatus("0");

        sendSystemMessage("1","监察大队办公室消息",mc.getContent(),"1",ids,names,messageService);

        monitorCaseService.update(mc);
    }

    /**
     * 发送系统消息
     * @param msgType
     * @param title
     * @param content
     * @param businessId
     * @param ids
     * @param names
     * @param messageService
     */
    public static void sendSystemMessage(String msgType,String title,String content,String businessId,String[] ids,String[] names,MessageService messageService){
        Message message=new Message();
        message.setMsgType(msgType);
        message.setTitle(title);
        message.setContent(content);
        message.setBusinessId(businessId);
        MessageTrace receiver=new MessageTrace();
        for (int i = 0; i < ids.length; i++) {
            receiver.setReceiverId(ids[i]);
            receiver.setReceiverName(names[i]);
        }

        List<MessageTrace> receivers=new ArrayList<>();
        receivers.add(receiver);
        messageService.sendMessage(message,receivers);
    }

    /**
     * 执法统计highchart获取后台数据
     */
    public void getColumnHighChart() {
        Map<String,Object> result = new HashMap<String,Object>();
        String name = request.getParameter("name");
        String lawType = request.getParameter("lawType");
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        List<Object[]> list = dispatchTaskService.getByColumnData(name,lawType,startYdate,lastYdate);
        if(list !=null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];

            for(int i = 0;i<list.size();i++){
                Object[] arr = list.get(i);
                xlist[i] = String.valueOf(arr[0]);
                ylist[i] = String.valueOf(arr[1]);
            }
            result.put("x",xlist);
            result.put("y",ylist);
        }
        write(result);
    }

    /**
     * 执法同期对比分析获取后台数据
     */
    public void getColumnRatio(){
        String name = request.getParameter("name");
        String lawType = request.getParameter("lawType");

        String startSdate = request.getParameter("startSdate");
        String lastSdate = request.getParameter("lastSdate");

        Map<String,Object> result = new HashMap<>();

        List<Object[]> list = dispatchTaskService.findByColumnRatio(startSdate,lastSdate,name,lawType);
        if (list != null && list.size() > 0) {
            Object[] xlist = new Object[list.size()];
            Object[] y1list = new Object[list.size()];
            Object[] y21list = new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                y1list[i] = String.valueOf(oo[1]);
                y21list[i] = String.valueOf(oo[2]);

            }
            result.put("x", xlist);
            result.put("y1", y1list);
            result.put("y2", y21list);
        }
        write(result);
    }

    /**
     * 根据 组织机构和人员角色 获得 环保站组织机构和环保站站长角色的人员
     */
    /*public void getStreetLeaderList(){
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId("402883b3577422f00157f9d874d103e9");
        for (IOrg org : orgs) {
            List<IPerson> persons = PersonServiceUtil.getPersonByOrgId(org.getOrgId());
            for (IPerson person : persons) {
                List<IRole> roles = RoleServiceUtil.getRoleByUserId(person.getUserId());
                for (IRole role : roles) {
                    if ("streetLeader".equals(role.getRoleCode())){

                    }
                }
            }
        }
    }*/

    public void updateEntity(){
        write(String.format("{\"success\": true, \"id\": \"%s\"}", dispatchTaskService.updateDispatchTask(entity)));
    }
}





