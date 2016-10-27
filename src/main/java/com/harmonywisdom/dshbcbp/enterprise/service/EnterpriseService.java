package com.harmonywisdom.dshbcbp.enterprise.service;

import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface EnterpriseService extends IBaseService<Enterprise, String> {

    /**
     * 获取企业排口树结构
     * @param id
     * @return
     */
    public List<DictBean> getEnterprisePortZtree(String id);
}