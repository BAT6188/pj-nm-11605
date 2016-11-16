package com.harmonywisdom.dshbcbp.officetemp.service.impl;

import com.harmonywisdom.dshbcbp.officetemp.bean.OfficeTemp;
import com.harmonywisdom.dshbcbp.officetemp.dao.OfficeTempDAO;
import com.harmonywisdom.dshbcbp.officetemp.service.OfficeTempService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunzuoquan on 15-7-20.
 */
@Service("officeTempService")
public class OfficeTempServiceImpl extends BaseService<OfficeTemp, String> implements OfficeTempService {
    @Autowired
    private OfficeTempDAO officeTempDAO;

    @Override
    protected BaseDAO<OfficeTemp, String> getDAO() {
        return officeTempDAO;
    }
}
