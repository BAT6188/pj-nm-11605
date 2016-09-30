package com.harmonywisdom.dshbcbp.demo.action;

import com.harmonywisdom.dshbcbp.demo.bean.Demo;
import com.harmonywisdom.dshbcbp.demo.service.DemoService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

/**
 * Created by hotleave on 14-9-16.
 */
public class DemoAction extends BaseAction<Demo, DemoService> {
    @AutoService
    private DemoService demoService;

    @Override
    protected DemoService getService() {
        return demoService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getName())) {
            params.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }

        if (entity.getAge() != null) {
            params.andParam(new QueryParam("age", QueryOperator.LIKE,entity.getAge()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("age", Direction.DESC);
        return condition;
    }
}
