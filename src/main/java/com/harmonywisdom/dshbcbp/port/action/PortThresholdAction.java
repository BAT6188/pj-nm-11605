package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.PortThreshold;
import com.harmonywisdom.dshbcbp.port.service.PortThresholdService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.Date;

public class PortThresholdAction extends BaseAction<PortThreshold, PortThresholdService> {
    @AutoService
    private PortThresholdService portThresholdService;

    @Override
    protected PortThresholdService getService() {
        return portThresholdService;
    }

    @Override
    public void save() {
        String length = request.getParameter("length");
        String[] overValue = request.getParameterValues("overValue");
        String[] minValue = request.getParameterValues("minValue");
        String[] maxValue = request.getParameterValues("maxValue");
        for(int i=0;i<Integer.parseInt(length);i++){
            PortThreshold portThreshold = new PortThreshold();
            portThreshold.setType(entity.getType());
            portThreshold.setPollutantCode(entity.getPollutantCode());
            portThreshold.setEnterpriseId(entity.getEnterpriseId());
            portThreshold.setCreateTime(entity.getCreateTime()==null?new Date():entity.getCreateTime());


        }
    }
}