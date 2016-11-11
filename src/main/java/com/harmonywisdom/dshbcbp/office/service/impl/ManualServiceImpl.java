package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.Manual;
import com.harmonywisdom.dshbcbp.office.dao.ManualCatalogDAO;
import com.harmonywisdom.dshbcbp.office.dao.ManualDAO;
import com.harmonywisdom.dshbcbp.office.service.ManualService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("manualService")
public class ManualServiceImpl extends BaseService<Manual, String> implements ManualService {
    @Autowired
    private ManualDAO manualDAO;
    @Autowired
    private ManualCatalogDAO catalogDAO;
    @Override
    protected BaseDAO<Manual, String> getDAO() {
        return manualDAO;
    }

    @Override
    public void deleteCatalog(String id) {
        catalogDAO.executeJPQL("delete from ManualCatalog entity where entity.id in ?1",id);

    }

    @Override
    public List<Manual> findByZtreeId(String ztreeId) {
        List<Manual> list=catalogDAO.queryJPQL("from Manual entity where pid=?",ztreeId);
        return list;
    }
}