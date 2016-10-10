package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.WaterControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.WaterControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class WaterControlFacilityAction extends BaseAction<WaterControlFacility, WaterControlFacilityService> {
    @AutoService
    private WaterControlFacilityService waterControlFacilityService;

    @Override
    protected WaterControlFacilityService getService() {
        return waterControlFacilityService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getName())) {
            params.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }

        if (entity.getStatus() != null) {
            params.andParam(new QueryParam("status", QueryOperator.LIKE,entity.getStatus()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        return condition;
    }
}