package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipmentHistory;
import com.harmonywisdom.dshbcbp.port.dao.AirEquipmentDAO;
import com.harmonywisdom.dshbcbp.port.dao.AirEquipmentHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentHistoryService;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("airEquipmentHistoryService")
public class AirEquipmentHistoryServiceImpl extends BaseService<AirEquipmentHistory, String> implements AirEquipmentHistoryService {
    @Autowired
    private AirEquipmentHistoryDAO airEquipmentHistoryDAO;

    @Override
    protected BaseDAO<AirEquipmentHistory, String> getDAO() {
        return airEquipmentHistoryDAO;
    }




}