package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.bean.PubInfoRelTable;
import com.harmonywisdom.dshbcbp.office.dao.PubInfoDAO;
import com.harmonywisdom.dshbcbp.office.dao.PubInfoRelTableDAO;
import com.harmonywisdom.dshbcbp.office.service.PubInfoRelTableService;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pubInfoRelTableService")
public class PubInfoRelTableServiceImpl extends BaseService<PubInfoRelTable, String> implements PubInfoRelTableService {
    @Autowired
    private PubInfoRelTableDAO pubInfoRelTableDAO;
    @Autowired
    private PubInfoDAO pubInfoDAO;

    @Override
    protected BaseDAO<PubInfoRelTable, String> getDAO() {
        return pubInfoRelTableDAO;
    }

    public QueryResult<PubInfoRelTable> find(QueryCondition var1) {
        QueryResult<PubInfoRelTable> pubInfoRelTableQueryResult = this.getDAO().find(var1);
        if(pubInfoRelTableQueryResult.getTotal()>0){
            for(PubInfoRelTable pirt:pubInfoRelTableQueryResult.getRows()){
                PubInfo thisInfo = pubInfoDAO.findById(pirt.getPubInfoId());
                pirt.setPubInfo(thisInfo);
            }
        }
        return pubInfoRelTableQueryResult;
    }

    public QueryResult<PubInfoRelTable> findBySample(PubInfoRelTable var1, Paging var2, String var3, Direction var4) {
        QueryResult<PubInfoRelTable> pubInfoRelTableQueryResult = this.getDAO().findBySample(var1, var2, var3, var4);
        if(pubInfoRelTableQueryResult.getTotal()>0){
            for(PubInfoRelTable pirt:pubInfoRelTableQueryResult.getRows()){
                PubInfo thisInfo = pubInfoDAO.findById(pirt.getPubInfoId());
                pirt.setPubInfo(thisInfo);
            }
        }
        return pubInfoRelTableQueryResult;
    }
}