package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.DustPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.DustPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.DustPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("dustPortService")
public class DustPortServiceImpl extends BaseService<DustPort, String> implements DustPortService {
    @Autowired
    private DustPortDAO dustPortDAO;
    @Autowired
    private DustPortHistoryDAO dustPortHistoryDAO;

    @Override
    protected BaseDAO<DustPort, String> getDAO() {
        return dustPortDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<DustPort> ports = getDAO().find("name like ?1", searchText);
        if (ports != null && ports.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (DustPort port : ports) {
                ZNodeDTO node = new ZNodeDTO(port.getId(), port.getName(),DustPort.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<DustPort> findByIds(String ...ids) {
        List<DustPort> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }

    @Override
    public void delete(String portId) {
        DustPortHistory dustPortHistory = new DustPortHistory();
        dustPortHistory.setPortId(portId);
        List<DustPortHistory> dustPortHistories = dustPortHistoryDAO.findBySample(dustPortHistory);
        if(dustPortHistories.size()>0){
            for(DustPortHistory dph:dustPortHistories){
                dustPortHistoryDAO.remove(dph);
            }
        }
        dustPortDAO.remove(portId);
    }

    /**
     * 一张图圈选沙尘暴
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<DustPort> circleQueryDusts(String radius, String longitude, String latitude) {
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
            List<DustPort> list = getDAO().queryJPQL("from DustPort t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
                    "t.latitude < ?", minLon, maxLon, minLat, maxLat);
            if (list != null && list.size() > 0) {
                return list;
            }
        }
        return null;
    }
}