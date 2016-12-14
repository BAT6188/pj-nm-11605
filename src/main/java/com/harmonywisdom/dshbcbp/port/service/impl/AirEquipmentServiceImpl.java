package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.dao.AirEquipmentDAO;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("airEquipmentService")
public class AirEquipmentServiceImpl extends BaseService<AirEquipment, String> implements AirEquipmentService {
    @Autowired
    private AirEquipmentDAO airEquipmentDAO;

    @Override
    protected BaseDAO<AirEquipment, String> getDAO() {
        return airEquipmentDAO;
    }


    /**
     * 根据文本区查询空气质量
     * @param searchText
     * @return
     */
    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<AirEquipment> equipments = getDAO().find("airMonitoringName like ?1",searchText);
        if (equipments != null && equipments.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (AirEquipment equipment : equipments) {
                ZNodeDTO node = new ZNodeDTO(equipment.getId(), equipment.getAirMonitoringName(),AirEquipment.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;

        }
        return null;
    }

    @Override
    public List<AirEquipment> findByIds(String...ids) {
        List<AirEquipment> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }
}