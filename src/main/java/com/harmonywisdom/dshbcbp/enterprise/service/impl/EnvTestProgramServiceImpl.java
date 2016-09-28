package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.EnvTestProgram;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnvTestProgramDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.EnvTestProgramService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("envTestProgramService")
public class EnvTestProgramServiceImpl extends BaseService<EnvTestProgram, String> implements EnvTestProgramService {
    @Autowired
    private EnvTestProgramDAO envTestProgramDAO;

    @Override
    protected BaseDAO<EnvTestProgram, String> getDAO() {
        return envTestProgramDAO;
    }
}