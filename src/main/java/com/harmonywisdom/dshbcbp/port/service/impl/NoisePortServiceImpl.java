package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortDAO;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.NoisePortService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("noisePortService")
public class NoisePortServiceImpl extends BaseService<NoisePort, String> implements NoisePortService {
    @Autowired
    private NoisePortDAO noisePortDAO;
    @Autowired
    private NoisePortHistoryDAO noisePortHistoryDAO;

    @Override
    protected BaseDAO<NoisePort, String> getDAO() {
        return noisePortDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<NoisePort> noisePorts =getDAO().find("name like ?1 and enterpriseId is null", searchText);
        if (noisePorts != null && noisePorts.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (NoisePort port : noisePorts) {
                ZNodeDTO node = new ZNodeDTO(port.getId(), port.getName(), NoisePort.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<NoisePort> findByIds(String ...ids) {
        List<NoisePort> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }

    @Override
    public void delete(String portId) {
        NoisePortHistory noisePortHistory = new NoisePortHistory();
        noisePortHistory.setPortId(portId);
        List<NoisePortHistory> waterPortHistories = noisePortHistoryDAO.findBySample(noisePortHistory);
        if(waterPortHistories.size()>0){
            for(NoisePortHistory wph:waterPortHistories){
                noisePortHistoryDAO.remove(wph);
            }
        }
        noisePortDAO.remove(portId);
    }

    /**
     * 一张图圈选噪声监测
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<NoisePort> circleQueryNoise(String radius, String longitude, String latitude) {
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
            List<NoisePort> list = getDAO().queryJPQL("from NoisePort t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
                    "t.latitude < ?", minLon, maxLon, minLat, maxLat);
            if (list != null && list.size() > 0) {
                return list;
            }
        }
        return null;
    }
}