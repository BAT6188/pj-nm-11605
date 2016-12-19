package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.dao.AirEquipmentDAO;
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

    /**
     * 一张图圈选空气质量到地图
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<AirEquipment> circleQueryAirs(String radius, String longitude, String latitude) {
        if(StringUtils.isNotBlank(radius)) {
            Double i = Double.parseDouble(radius);
            Double x = Double.parseDouble(longitude);
            Double y = Double.parseDouble(latitude);
            Double x1 = x - i;
            Double y1 = y - i;
            Double x2 = x + i;
            Double y2 = y + i;
            double minLon = x1 < x2 ? x1 : x2;
            double maxLon = x1 < x2 ? x2 : x1;
            double minLat = y1 < y2 ? y1 : y2;
            double maxLat = y1 < y2 ? y2 : y1;
            List<AirEquipment> list = getDAO().queryJPQL("from AirEquipment t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
                    "t.latitude < ?", minLon, maxLon, minLat, maxLat);
            if (list != null && list.size() > 0) {
                return list;
            }
        }
        return null;
    }
}