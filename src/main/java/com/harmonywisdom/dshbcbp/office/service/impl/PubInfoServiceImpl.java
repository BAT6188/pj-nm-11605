package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.dao.PubInfoDAO;
import com.harmonywisdom.dshbcbp.office.service.PubInfoService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pubInfoService")
public class PubInfoServiceImpl extends BaseService<PubInfo, String> implements PubInfoService {
    @Autowired
    private PubInfoDAO pubInfoDAO;

    @Override
    protected BaseDAO<PubInfo, String> getDAO() {
        return pubInfoDAO;
    }

    /**
     * 企业端查看信息公告
     * @return
     */
    @Override
    public List<PubInfo> companyByPower() {
        List<PubInfo> pubInfos = getDAO().queryJPQL("from PubInfo where grade='5' order by pubTime DESC,id DESC");
        return pubInfos;
    }
}