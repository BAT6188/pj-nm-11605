package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.bean.PubInfoRelTable;
import com.harmonywisdom.dshbcbp.office.dao.PubInfoDAO;
import com.harmonywisdom.dshbcbp.office.dao.PubInfoRelTableDAO;
import com.harmonywisdom.dshbcbp.office.service.PubInfoService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pubInfoService")
public class PubInfoServiceImpl extends BaseService<PubInfo, String> implements PubInfoService {
    @Autowired
    private PubInfoDAO pubInfoDAO;
    @Autowired
    private PubInfoRelTableDAO pubInfoRelTableDAO;

    @Override
    protected BaseDAO<PubInfo, String> getDAO() {
        return pubInfoDAO;
    }

    @Override
    public void updatePubInfo(String id) {
        pubInfoDAO.executeJPQL("update  PubInfo entity set entity.status=1 where entity.id=?",id);
    }

    /**
     * 企业端查看信息公告
     * @return
     */
    @Override
    public List<PubInfo> companyByPower() {
        List<PubInfo> pubInfos = getDAO().queryJPQL("from PubInfo where grade LIKE '%company%' and status = '1' order by pubTime DESC,id DESC");
        return pubInfos;
    }

    @Override
    public QueryResult<PubInfo> find(QueryCondition var1, PubInfo entity) {
        StringBuffer sb = new StringBuffer();
        Map<String, Object> paramValues = new HashMap<>();
        sb.append(" 1=1 ");
        if (StringUtils.isNotBlank(entity.getStatus())) {
            paramValues.put("status","1");
            sb.append("AND entity.status = :status ");
        }
        if (StringUtils.isNotBlank(entity.getGrade())) {
            paramValues.put("grade","%"+"company"+"%");
            sb.append("AND entity.grade LIKE :grade ");
        }
        if (StringUtils.isNotBlank(entity.getTitle())) {
            paramValues.put("title","%"+entity.getTitle()+"%");
            sb.append("AND entity.title LIKE :title ");
        }
        if (StringUtils.isNotBlank(entity.getPubOrgName())) {
            paramValues.put("pubOrgName","%"+entity.getPubOrgName()+"%");
            sb.append("AND entity.pubOrgName LIKE :pubOrgName ");
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            paramValues.put("type",entity.getType());
            sb.append("AND entity.type = :type ");
        }

        if (StringUtils.isNotBlank(entity.getStatus())) {
            paramValues.put("pubOrgId",entity.getPubOrgId());
            paramValues.put("status",entity.getStatus());
            sb.append("AND ( entity.pubOrgId = :pubOrgId AND entity.status = :status ) ");
        }else{
            paramValues.put("pubOrgId",entity.getPubOrgId());
            paramValues.put("status","1");
            paramValues.put("status1","0");
            sb.append("AND ( entity.status = :status OR (entity.pubOrgId = :pubOrgId AND entity.status = :status1)) ");
        }

        if(StringUtils.isNotBlank(entity.getStartTime())){
            paramValues.put("startTime", MyDateUtils.getFullDate(entity.getStartTime(),true));
            sb.append("AND entity.pubTime >= :startTime ");
        }
        if(StringUtils.isNotBlank(entity.getEndTime())){
            paramValues.put("endTime", MyDateUtils.getFullDate(entity.getEndTime(),false));
            sb.append("AND entity.pubTime <= :endTime ");
        }
        sb.append("ORDER BY "+var1.getOrderBy()+" "+var1.getDirection());
        return pubInfoDAO.find(sb.toString(),var1.getPaging(),paramValues);
    }

    @Override
    public void savePubInfoRelTable(PubInfo pubInfo) {
        pubInfoRelTableDAO.executeJPQL("delete from PubInfoRelTable where pubInfoId=?",pubInfo.getId());

        PubInfoRelTable pirt = new PubInfoRelTable(pubInfo.getPubOrgId(),pubInfo.getPubOrgName(),pubInfo.getId(),pubInfo.getPubTime(),pubInfo.getType(),pubInfo.getTitle(),pubInfo.getStatus());
        pubInfoRelTableDAO.save(pirt);

        if(StringUtils.isNotBlank(pubInfo.getStatus()) && pubInfo.getStatus().equals("1")){
            String orgIdString = pubInfo.getGrade();
            if(orgIdString!=null){
                String[] orgIds = orgIdString.split(",");
                for(String orgId:orgIds){
                    saveRealTableByOrgCode(orgId,pubInfo);
                }
            }
        }
    }

    public void saveRealTableByOrgCode(String parentOrgId,PubInfo pubInfo){
        PubInfoRelTable ppirt = new PubInfoRelTable(parentOrgId,pubInfo.getPubOrgName(),pubInfo.getId(),pubInfo.getPubTime(),pubInfo.getType(),pubInfo.getTitle(),pubInfo.getStatus());
        pubInfoRelTableDAO.save(ppirt);
        List<IOrg> iOrgs = ApportalUtil.getAllChidrenOrgByOrgId(parentOrgId);
        if(iOrgs!=null){
            for(IOrg iOrg:iOrgs){
                if(iOrg.getOrgId().equals(pubInfo.getPubOrgId()))continue;
                PubInfoRelTable pirt = new PubInfoRelTable(iOrg.getOrgId(),pubInfo.getPubOrgName(),pubInfo.getId(),pubInfo.getPubTime(),pubInfo.getType(),pubInfo.getTitle(),pubInfo.getStatus());
                pubInfoRelTableDAO.save(pirt);
            }
        }
    }

    /*public QueryResult<PubInfo> find(QueryCondition var1) {
        QueryResult<PubInfo> result = new QueryResult<>();
        QueryResult<PubInfoRelTable> pubInfoRelTableQueryResult = pubInfoRelTableDAO.find(var1);
        if(pubInfoRelTableQueryResult.getTotal()>0){
            List<PubInfo> infos = new ArrayList<>();
            for(PubInfoRelTable pirt:pubInfoRelTableQueryResult.getRows()){
                PubInfo thisInfo = pubInfoDAO.findById(pirt.getPubInfoId());
                infos.add(thisInfo);
            }
            result.setRows(infos);
            result.setTotal(pubInfoRelTableQueryResult.getTotal());
        }
        return result;
    }*/

}