package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.PortThreshold;
import com.harmonywisdom.dshbcbp.port.dao.PortThresholdDAO;
import com.harmonywisdom.dshbcbp.port.service.PortThresholdService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("portThresholdService")
public class PortThresholdServiceImpl extends BaseService<PortThreshold, String> implements PortThresholdService {
    @Autowired
    private PortThresholdDAO portThresholdDAO;

    @Override
    protected BaseDAO<PortThreshold, String> getDAO() {
        return portThresholdDAO;
    }

    @Override
    public void deleteByEnterprise(String enterpriseId) {
        PortThreshold portThreshold = new PortThreshold();
        portThreshold.setEnterpriseId(enterpriseId);
        List<PortThreshold> portThresholds = portThresholdDAO.findBySample(portThreshold);
        if(portThresholds.size()>0){
            for(PortThreshold pt:portThresholds){
                portThresholdDAO.remove(pt);
            }
        }
    }
}