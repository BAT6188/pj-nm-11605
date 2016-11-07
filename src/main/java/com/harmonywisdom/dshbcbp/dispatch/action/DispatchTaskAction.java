package com.harmonywisdom.dshbcbp.dispatch.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.apportal.sdk.role.IRole;
import com.harmonywisdom.apportal.sdk.role.RoleServiceUtil;
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
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class DispatchTaskAction extends BaseAction<DispatchTask, DispatchTaskService> {

    public static final String monitor_master="monitor_master";
    public static final String env_pro_sta="env_pro_sta";

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
     * 根据 组织机构和人员角色 获得 环保站组织机构和环保站站长角色的人员
     */
    public void getStreetLeaderList(){
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
    }

    /**
     * 保存现场监察
     */
    public void saveXianChangJianChaAjax(){
        entity.setSource("4");
        entity.setAllPerson("1");

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

        super.save();
    }


    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();

        /**
         * 三种角色：
         * 1. monitor_master
         * 2. env_pro_sta
         * 3. allPerson
         */
        String role = request.getParameter("role");
        IPerson person = ApportalUtil.getPerson(request);
        if (StringUtils.isNotEmpty(role)){
            if (monitor_master.equals(role)){
                params=new QueryParam("monitorMastorPersonList", QueryOperator.LIKE, "%"+person.getPersonId()+"%");
            }else if (env_pro_sta.equals(role)){
                params= new QueryParam("envProStaPersonList", QueryOperator.LIKE, "%"+person.getPersonId()+"%");
            }

            //TODO 所有人可查看的数据
            params.orParam(new QueryParam("allPerson",QueryOperator.EQ,"1"));
        }

        String startEventTime = request.getParameter("startEventTime");
        String endEventTime = request.getParameter("endEventTime");
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
        if (org.apache.commons.lang.StringUtils.isNotBlank(status)) {
            params.andParam(new QueryParam("status", QueryOperator.EQ, status));
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

        //根据登录人员角色和userId 筛选 只 执法管理领导 能够看到的数据
//        IPerson person = ApportalUtil.getPerson(request);
//        String userId = person.getUserId();
//        List<IRole> role = ApportalUtil.getRoleByPersonId(request);
//        for (IRole r : role) {
//            String roleCode = r.getRoleCode();
//            if ("exelawLeader".equals(roleCode)){
//                params.andParam(new QueryParam("selectPeopleIds", QueryOperator.LIKE,"%"+userId+"%"));
//                log.debug("根据登录人员角色和userId 筛选 只有 执法管理领导 能够看到的数据: 登录人员的userId为"+userId);
//            }
//        }
        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("eventTime", Direction.DESC);
        return condition;
    }

    /**
     * 办结管理
     */
    public void overStatus(){
        String[] ids = this.getParamValues("ids");
        for (String id : ids) {
            DispatchTask dt = dispatchTaskService.findById(id);
            dt.setStatus("5");
            dispatchTaskService.update(dt);
        }

    }

    /**
     * 点处置按钮
     */
    public void dispathTaskBtnSave(){
        String id = entity.getId();
        DispatchTask dispatchTask = dispatchTaskService.findById(id);
        dispatchTask.setContent(entity.getContent());
        dispatchTask.setSendRemark(entity.getSendRemark());

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

    /**
     * 选择环保站人员，点发送按钮
     */
    public void saveToEnvProStaPersonList(){
        String dispathTaskId = request.getParameter("sourceId");
        DispatchTask dispatchTask = dispatchTaskService.findById(dispathTaskId);

        String[] ids = this.getParamValues("ids");
        String jsonIds = JSON.toJSONString(ids);
        dispatchTask.setEnvProStaPersonList(jsonIds);

        dispatchTask.setStatus("2");
        dispatchTask.setSendTime(new Date());

        String pk = this.getService().saveOrUpdate(dispatchTask);
        write(pk);
    }

    /**
     * 监控中心，监控办公室的调度单选择人员，点发送按钮
     */
    @Override
    public void save() {
        String monitorCaseId = request.getParameter("sourceId");
        MonitorCase mc = monitorCaseService.findByObjectId(monitorCaseId);

        mc.setStatus(1);
        monitorCaseService.update(mc);

        String[] ids = this.getParamValues("ids");
        String jsonIds = JSON.toJSONString(ids);
        entity.setMonitorMastorPersonList(jsonIds);

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

        entity.setStatus("1");

        super.save();
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
        String startXdate = request.getParameter("startXdate");
        String lastXdate = request.getParameter("lastXdate");
        String startSdate = request.getParameter("startSdate");
        String lastSdate = request.getParameter("lastSdate");

        Map<String,Object> result = new HashMap<>();

        List<Object[]> list = dispatchTaskService.findByColumnRatio(startXdate,lastXdate,startSdate,lastSdate,name,lawType);
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

}





