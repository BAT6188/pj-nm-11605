package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface PubInfoService extends IBaseService<PubInfo, String> {

    void updatePubInfo(String id);
    /**
     * 企业查看信息公告
     * @return
     */
    List<PubInfo> companyByPower();
    QueryResult<PubInfo> find(QueryCondition var1, PubInfo entity);
}