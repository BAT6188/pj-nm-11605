package com.harmonywisdom.dshbcbp.production.action;

import com.harmonywisdom.dshbcbp.production.bean.OtherEquipment;
import com.harmonywisdom.dshbcbp.production.service.OtherEquipmentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class OtherEquipmentAction extends BaseAction<OtherEquipment, OtherEquipmentService> {
    @AutoService
    private OtherEquipmentService otherEquipmentService;

    @Override
    protected OtherEquipmentService getService() {
        return otherEquipmentService;
    }
}