package com.harmonywisdom.dshbcbp.production.service.impl;

import com.harmonywisdom.dshbcbp.production.bean.OtherEquipment;
import com.harmonywisdom.dshbcbp.production.dao.OtherEquipmentDAO;
import com.harmonywisdom.dshbcbp.production.service.OtherEquipmentService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("otherEquipmentService")
public class OtherEquipmentServiceImpl extends BaseService<OtherEquipment, String> implements OtherEquipmentService {
    @Autowired
    private OtherEquipmentDAO otherEquipmentDAO;

    @Override
    protected BaseDAO<OtherEquipment, String> getDAO() {
        return otherEquipmentDAO;
    }
}