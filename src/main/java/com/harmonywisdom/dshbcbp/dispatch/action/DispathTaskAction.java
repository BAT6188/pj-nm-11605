package com.harmonywisdom.dshbcbp.dispatch.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.apportal.sdk.role.IRole;
import com.harmonywisdom.apportal.sdk.role.RoleServiceUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.DispathTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispathTaskAction extends BaseAction<DispathTask, DispathTaskService> {
    @AutoService
    private DispathTaskService dispathTaskService;

    @AutoService
    private MonitorCaseService monitorCaseService;

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


    @Override
    protected QueryCondition getQueryCondition() {

        QueryParam params = new QueryParam();

        //根据登录人员角色和userId 筛选 只 执法管理领导 能够看到的数据
        IPerson person = ApportalUtil.getPerson(request);
        String userId = person.getUserId();
        List<IRole> role = ApportalUtil.getRoleByPersonId(request);
        for (IRole r : role) {
            String roleCode = r.getRoleCode();
            if ("exelawLeader".equals(roleCode)){
                params.andParam(new QueryParam("selectPeopleIds", QueryOperator.LIKE,"%"+userId+"%"));
                log.debug("根据登录人员角色和userId 筛选 只有 执法管理领导 能够看到的数据: 登录人员的userId为"+userId);
            }
        }


        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        return condition;
    }

    @Override
    protected DispathTaskService getService() {
        return dispathTaskService;
    }

    public void overStatus(){
        String[] ids = this.getParamValues("ids");
        for (String id : ids) {
            DispathTask dt = dispathTaskService.findById(id);
            dt.setStatus("5");
            dispathTaskService.update(dt);
        }

    }

    /**
     * 调度单点调度按钮
     */
    public void dispathTaskBtnSave(){
        DispathTask dispathTask = dispathTaskService.findById(entity.getId());
        entity.setSelectPeopleIds(dispathTask.getSelectPeopleIds());
        if (StringUtils.isEmpty(entity.getEnterpriseName())){
            entity.setEnterpriseName(dispathTask.getEnterpriseName());
        }
        if (StringUtils.isEmpty(entity.getBlockLevelName())){
            entity.setBlockLevelName(dispathTask.getBlockLevelName());
        }
        if (StringUtils.isEmpty(entity.getBlockName())){
            entity.setBlockName(dispathTask.getBlockName());
        }
        if(StringUtils.isEmpty(entity.getMonitorCaseId())){
            entity.setBlockName(dispathTask.getMonitorCaseId());
        }
        super.save();
    }

    /**
     * 执法管理列表的调度单选择人员，点发送按钮
     */
    public void updateFromSendToBtn(){
        String dispathTaskId = request.getParameter("dispathTaskId");
        DispathTask dispathTask = dispathTaskService.findById(dispathTaskId);

        String[] ids = this.getParamValues("ids");
        String jsonIds = JSON.toJSONString(ids);
        dispathTask.setEnvironmentalProtectionStationStaffIds(jsonIds);

        dispathTask.setStatus("2");

        String pk = this.getService().saveOrUpdate(dispathTask);
        write(pk);
    }

    /**
     * 监控中心，监控办公室的调度单选择人员，点发送按钮
     */
    @Override
    public void save() {
        String monitorCaseId = request.getParameter("monitorCaseId");
        MonitorCase mc = monitorCaseService.findByObjectId(monitorCaseId);

        mc.setStatus(1);
        monitorCaseService.update(mc);

        String[] ids = this.getParamValues("ids");
        String jsonIds = JSON.toJSONString(ids);
        entity.setSelectPeopleIds(jsonIds);

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

        List<Object[]> list = dispathTaskService.getByColumnData(name,lawType,startYdate,lastYdate);
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

        List<Object[]> list = dispathTaskService.findByColumnRatio(startXdate,lastXdate,startSdate,lastSdate,name,lawType);
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





