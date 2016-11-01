package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.exelaw.bean.TrustMonitor;
import com.harmonywisdom.dshbcbp.exelaw.service.TrustMonitorService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class TrustMonitorAction extends BaseAction<TrustMonitor, TrustMonitorService> {
    @AutoService
    private TrustMonitorService trustMonitorService;

    @Override
    protected TrustMonitorService getService() {
        return trustMonitorService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String start_monitorTime = request.getParameter("start_monitorTime");
        String end_monitorTime = request.getParameter("end_monitorTime");

        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }

        if (StringUtils.isNotBlank(start_monitorTime)) {
            params.andParam(new QueryParam("monitorTime", QueryOperator.GE, DateUtil.strToDate(start_monitorTime,"yyyy-MM-dd HH:mm")));
        }

        if (StringUtils.isNotBlank(end_monitorTime)) {
            params.andParam(new QueryParam("monitorTime", QueryOperator.LE,DateUtil.strToDate(end_monitorTime,"yyyy-MM-dd HH:mm")));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("monitorTime", Direction.DESC);
        return condition;
    }
}