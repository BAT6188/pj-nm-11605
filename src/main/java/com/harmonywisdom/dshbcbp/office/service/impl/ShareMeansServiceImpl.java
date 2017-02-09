package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.ShareMeans;
import com.harmonywisdom.dshbcbp.office.dao.ShareMeansDAO;
import com.harmonywisdom.dshbcbp.office.service.ShareMeansService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("shareMeansService")
public class ShareMeansServiceImpl extends BaseService<ShareMeans, String> implements ShareMeansService {
    @Autowired
    private ShareMeansDAO shareMeansDAO;

    @Override
    protected BaseDAO<ShareMeans, String> getDAO() {
        return shareMeansDAO;
    }

    @Override
    public void updateShareMeans(String id) {
        shareMeansDAO.executeJPQL("update  ShareMeans entity set entity.status=1 where entity.id=?",id);
    }

    @Override
    public QueryResult<ShareMeans> find(QueryCondition var1, ShareMeans entity) {
        StringBuffer bf = new StringBuffer();
        Map<String, Object> paramValues = new HashMap<>();
        bf.append(" 1=1 ");
        if (StringUtils.isNotBlank(entity.getTitle())) {
            paramValues.put("title","%"+entity.getTitle()+"%");
            bf.append("AND entity.title LIKE :title ");
        }
        if (StringUtils.isNotBlank(entity.getPubOrgName())) {
            paramValues.put("pubOrgName","%"+entity.getPubOrgName()+"%");
            bf.append("AND entity.pubOrgName LIKE :pubOrgName ");
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            paramValues.put("type",entity.getType());
            bf.append("AND entity.type = :type ");
        }
        if (StringUtils.isNotBlank(entity.getStatus())) {
            paramValues.put("pubOrgId",entity.getPubOrgId());
            paramValues.put("status",entity.getStatus());
            bf.append("AND ( entity.pubOrgId = :pubOrgId AND entity.status = :status ) ");
        }else{
            paramValues.put("pubOrgId",entity.getPubOrgId());
            paramValues.put("status","1");
            paramValues.put("status1","0");
            bf.append("AND ( entity.status = :status OR (entity.pubOrgId = :pubOrgId AND entity.status = :status1)) ");
        }

        if(StringUtils.isNotBlank(entity.getStartTime())){
            paramValues.put("startTime", MyDateUtils.getFullDate(entity.getStartTime(),true));
            bf.append("AND entity.pubTime >= :startTime ");
        }
        if(StringUtils.isNotBlank(entity.getEndTime())){
            paramValues.put("endTime", MyDateUtils.getFullDate(entity.getEndTime(),false));
            bf.append("AND entity.pubTime <= :endTime ");
        }
        bf.append("ORDER BY "+var1.getOrderBy()+" "+var1.getDirection());
        return shareMeansDAO.find(bf.toString(),var1.getPaging(),paramValues);
    }
}