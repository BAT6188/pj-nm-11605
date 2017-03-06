package com.harmonywisdom.dshbcbp.appclient.service.impl;

import com.harmonywisdom.dshbcbp.appclient.bean.AppClient;
import com.harmonywisdom.dshbcbp.appclient.dao.AppClientDAO;
import com.harmonywisdom.dshbcbp.appclient.service.AppClientService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("appClientService")
public class AppClientServiceImpl extends BaseService<AppClient, String> implements AppClientService {
    @Autowired
    private AppClientDAO appClientDAO;

    @Override
    protected BaseDAO<AppClient, String> getDAO() {
        return appClientDAO;
    }

    @Override
    public AppClient findNewestApk() {
        List<AppClient> appClients = appClientDAO.queryJPQL("from AppClient ORDER BY apkVersionNum DESC");
        if(appClients.size()>0){
            return appClients.get(0);
        }else {
            return null;
        }
    }
}