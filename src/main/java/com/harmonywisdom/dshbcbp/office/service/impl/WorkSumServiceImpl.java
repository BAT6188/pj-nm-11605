package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.WorkSum;
import com.harmonywisdom.dshbcbp.office.dao.WorkSumDAO;
import com.harmonywisdom.dshbcbp.office.service.WorkSumService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("workSumService")
public class WorkSumServiceImpl extends BaseService<WorkSum, String> implements WorkSumService {
    @Autowired
    private WorkSumDAO workSumDAO;

    @Override
    protected BaseDAO<WorkSum, String> getDAO() {
        return workSumDAO;
    }

    @Override
    public QueryResult<WorkSum> find(QueryCondition var1,WorkSum entity) {
        StringBuffer sb = new StringBuffer();
        Map<String, Object> paramValues = new HashMap<>();
        sb.append(" 1=1 ");
        if (StringUtils.isNotBlank(entity.getTitle())) {
            paramValues.put("title","%"+entity.getTitle()+"%");
            sb.append("AND entity.title LIKE :title ");
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            paramValues.put("type",entity.getType());
            sb.append("AND entity.type = :type ");
        }
        if (StringUtils.isNotBlank(entity.getPubOrgName())) {
            paramValues.put("pubOrgName","%"+entity.getPubOrgName()+"%");
            sb.append("AND entity.pubOrgName LIKE :pubOrgName ");
        }
        if (StringUtils.isNotBlank(entity.getPublishStatus())) {
            paramValues.put("pubOrgId",entity.getPubOrgId());
            paramValues.put("publishStatus",entity.getPublishStatus());
            sb.append("AND ( entity.pubOrgId = :pubOrgId AND entity.publishStatus = :publishStatus ) ");
        }else{
            paramValues.put("pubOrgId",entity.getPubOrgId());
            paramValues.put("publishStatus","1");
            paramValues.put("publishStatus1","0");
            sb.append("AND ( entity.publishStatus = :publishStatus OR (entity.pubOrgId = :pubOrgId AND entity.publishStatus = :publishStatus1)) ");
        }

        if(StringUtils.isNotBlank(entity.getStartTime())){
            paramValues.put("startTime", MyDateUtils.getFullDate(entity.getStartTime(),true));
            sb.append("AND entity.pubTime > :startTime ");
        }
        if(StringUtils.isNotBlank(entity.getEndTime())){
            paramValues.put("endTime", MyDateUtils.getFullDate(entity.getEndTime(),false));
            sb.append("AND entity.pubTime < :endTime ");
        }
        sb.append("ORDER BY "+var1.getOrderBy()+" "+var1.getDirection());
        //var1.getQueryClause();
        return workSumDAO.find(sb.toString(),var1.getPaging(),paramValues);
    }
}