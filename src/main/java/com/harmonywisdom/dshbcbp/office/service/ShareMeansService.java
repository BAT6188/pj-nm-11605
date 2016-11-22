package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.ShareMeans;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

public interface ShareMeansService extends IBaseService<ShareMeans, String> {
    void updateShareMeans(String id);
    QueryResult<ShareMeans> find(QueryCondition var1, ShareMeans entity);

}