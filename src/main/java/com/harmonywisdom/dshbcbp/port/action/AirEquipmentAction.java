package com.harmonywisdom.dshbcbp.port.action;


import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.List;


public class AirEquipmentAction extends BaseAction<AirEquipment, AirEquipmentService> {
    @AutoService
    private AirEquipmentService airEquipmentService;

    @Override
    protected AirEquipmentService getService() {
        return airEquipmentService;
    }

    public void findByIds(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<AirEquipment> list = getService().findByIds(ids);
            write(list);
        }
    }

}