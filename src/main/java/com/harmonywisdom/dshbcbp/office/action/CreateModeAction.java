package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.office.bean.CreateMode;
import com.harmonywisdom.dshbcbp.office.bean.CreateModeDetail;
import com.harmonywisdom.dshbcbp.office.service.CreateModeDetailService;
import com.harmonywisdom.dshbcbp.office.service.CreateModeService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.activeio.xnet.hba.ExactIPAddressPermission;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class CreateModeAction extends BaseAction<CreateMode, CreateModeService> {
    @AutoService
    private CreateModeService createModeService;

    @AutoService
    private CreateModeDetailService createModeDetailService;

    @Override
    protected CreateModeService getService() {
        return createModeService;
    }

    public void delete(){
        String[] deletedIds = this.getParamValues("deletedId");
        for (String deletedId : deletedIds) {
            List<CreateModeDetail> createModeDetails = createModeDetailService.find("createModeId=?", deletedId);
            if (createModeDetails.size()>0){
                this.write(Boolean.FALSE, "该条记录下有关联的具体任务，不能够删除。请先删除关联的具体任务");
                return;
            }
        }
        super.delete();
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        String start_publishTime = request.getParameter("start_publishTime");
        String end_publishTime = request.getParameter("end_publishTime");

        if (StringUtils.isNotBlank(entity.getName())) {
            params.andParam(new QueryParam("name", QueryOperator.LIKE,"%"+entity.getName()+"%"));
        }
        if (StringUtils.isNotBlank(start_publishTime)) {
            params.andParam(new QueryParam("publishTime", QueryOperator.GE, DateUtil.strToDate(start_publishTime,DateUtil.format1)));
        }
        if (StringUtils.isNotBlank(end_publishTime)) {
            params.andParam(new QueryParam("publishTime", QueryOperator.LE, DateUtil.strToDate(end_publishTime,DateUtil.format1)));
        }
        if (StringUtils.isNotBlank(entity.getPublishOrgId())) {
            params.andParam(new QueryParam("publishOrgId", QueryOperator.EQ, entity.getPublishOrgId()));
        }
        if (StringUtils.isNotBlank(entity.getStatus())) {
            params.andParam(new QueryParam("status", QueryOperator.EQ, entity.getStatus()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("publishTime", Direction.DESC);
        return condition;
    }


}