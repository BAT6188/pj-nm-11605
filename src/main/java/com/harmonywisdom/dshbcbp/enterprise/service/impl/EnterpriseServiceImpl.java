package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.apportal.sdk.dictdata.domain.DictData;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.GasPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortDAO;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("enterpriseService")
public class EnterpriseServiceImpl extends BaseService<Enterprise, String> implements EnterpriseService {
    @Autowired
    private EnterpriseDAO enterpriseDAO;
    @Autowired
    private GasPortDAO gasPortDAO;
    @Autowired
    private WaterPortDAO waterPortDAO;
    @Autowired
    private FumesPortDAO fumesPortDAO;
    @Autowired
    private NoisePortDAO noisePortDAO;


    @Override
    protected BaseDAO<Enterprise, String> getDAO() {
        return enterpriseDAO;
    }

    @Override
    public List<DictBean> getEnterprisePortZtree(String id) {
        List<DictBean> dictBeans = new ArrayList<DictBean>();
        GasPort gasPort = new GasPort();
        gasPort.setEnterpriseId(id);
        List<GasPort> gasPortList = gasPortDAO.findBySample(gasPort);
        for(int i=0;i<gasPortList.size();i++){
            dictBeans.add(covertToGasPort(gasPortList.get(i),i));
        }
        return dictBeans;
    }

    /**
     * 转换
     * @param dictData
     * @return
     */
    private DictBean covertToDictBean(DictData dictData) {
        DictBean bean = new DictBean();
        bean.setCode(dictData.getDictdataCode());
        bean.setName(dictData.getDictdataName());
        bean.setParentCode(dictData.getDictdataText());
        bean.setSerial(dictData.getSerialIndex());
        return bean;
    }
    private DictBean covertToGasPort(GasPort gasPort,int index) {
        DictBean bean = new DictBean();
        bean.setCode(gasPort.getId());
        bean.setName(gasPort.getName());
        bean.setParentCode(gasPort.getEnterpriseId());
        bean.setSerial(index);
        return bean;
    }
}