package com.harmonywisdom.dshbcbp.production.service.impl;

import com.harmonywisdom.dshbcbp.production.bean.MainProduct;
import com.harmonywisdom.dshbcbp.production.dao.MainProductDAO;
import com.harmonywisdom.dshbcbp.production.service.MainProductService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainProductService")
public class MainProductServiceImpl extends BaseService<MainProduct, String> implements MainProductService {
    @Autowired
    private MainProductDAO mainProductDAO;

    @Override
    protected BaseDAO<MainProduct, String> getDAO() {
        return mainProductDAO;
    }
}