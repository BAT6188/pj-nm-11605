package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.WorkSum;
import com.harmonywisdom.dshbcbp.office.dao.WorkSumDAO;
import com.harmonywisdom.dshbcbp.office.service.WorkSumService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String var2 = "entity.type = :type AND (entity.publishStatus = :publishStatus OR (entity.pubOrgId = :pubOrgId AND entity.publishStatus = :publishStatus1)) ORDER BY entity.pubTime DESC";
        if (StringUtils.isNotBlank(entity.getPublishStatus())) {
            var2 = "entity.type = :type AND (entity.pubOrgId = :pubOrgId AND entity.publishStatus = :publishStatus) ORDER BY entity.pubTime DESC";
        }
        /*String limitString = " limit :startIndex,:pageSize";
        var2 +=limitString;*/
        var1.getQueryClause();
        /*Map<String, Object> paramVMap = var1.getParam().getParamValues();
        paramVMap.put("startIndex",var1.getPaging().getStartIndex());
        paramVMap.put("pageSize",var1.getPaging().getPageSize());*/
        //List<WorkSum> list = workSumDAO.queryJPQL(var2,var1.getPaging(),var1.getParam().getParamValues());
        /*QueryResult<WorkSum> queryResult = new QueryResult<>();
        queryResult.setRows(list);*/
        return workSumDAO.find(var2,var1.getPaging(),var1.getParam().getParamValues());
    }
}