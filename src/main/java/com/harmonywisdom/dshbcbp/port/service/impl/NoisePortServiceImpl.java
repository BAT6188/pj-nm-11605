package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortDAO;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.NoisePortService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}