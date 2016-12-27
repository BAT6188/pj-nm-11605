package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.PubInfoRelTable;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

public interface PubInfoRelTableService extends IBaseService<PubInfoRelTable, String> {

    QueryResult<PubInfoRelTable> find(QueryCondition var1);

    QueryResult<PubInfoRelTable> findBySample(PubInfoRelTable var1, Paging var2, String var3, Direction var4);
}