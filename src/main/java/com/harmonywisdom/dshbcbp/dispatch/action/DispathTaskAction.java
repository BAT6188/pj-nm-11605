package com.harmonywisdom.dshbcbp.dispatch.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.DispathTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.beanutils.BeanUtils;

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

    @Override
    protected DispathTaskService getService() {
        return dispathTaskService;
    }

    @Override
    public void save() {
        String[] ids = this.getParamValues("ids");
        String jsonIds = JSON.toJSONString(ids);
        entity.setSelectPeopleIds(jsonIds);

        String monitorCaseId = request.getParameter("monitorCaseId");
        entity.setMonitorCaseId(monitorCaseId);
        MonitorCase mc = monitorCaseService.findByObjectId(monitorCaseId);

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

        super.save();
    }

    /**
     * 柱状图数据
     */
    public void getColumnHighChart() throws ParseException {
        Map<String,Object> result = new HashMap<String,Object>();
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstTime = sdf.parse(startYdate);
        Date lastTime = sdf.parse(lastYdate);

        List<Object[]> list = dispathTaskService.getByColumnData(firstTime,lastTime);
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



}