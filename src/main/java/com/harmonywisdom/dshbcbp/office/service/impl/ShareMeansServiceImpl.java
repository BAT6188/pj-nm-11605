package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.ShareMeans;
import com.harmonywisdom.dshbcbp.office.dao.ShareMeansDAO;
import com.harmonywisdom.dshbcbp.office.service.ShareMeansService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}