package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.port.bean.FumesPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.GasPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortDAO;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

        Enterprise enterprise = enterpriseDAO.findById(id);
        dictBeans.add(covertToDictBean(enterprise.getId(),enterprise.getName(),"-1",0,"common/images/ztree/Factory_Yellow.png"));

        Integer serial = 1;
        /**
         * 废气排口数据
         */
        GasPort gasPort = new GasPort();
        gasPort.setEnterpriseId(id);
        List<GasPort> gasPortList = gasPortDAO.findBySample(gasPort);
        if(gasPortList.size()>0){
            dictBeans.add(covertToDictBean("gasPort","废气排口",id,serial,"common/images/ztree/pictograms_aem.png"));
            serial +=1;
            for(int i=0;i<gasPortList.size();i++){
                GasPort gp = gasPortList.get(i);
                dictBeans.add(covertToDictBean(gp.getId(),gp.getName(),"gasPort",serial,"common/images/ztree/preferences_alt.png"));
                serial +=1;
            }
        }

        /**
         * 废水排口数据
         */
        WaterPort waterPort = new WaterPort();
        waterPort.setEnterpriseId(id);
        List<WaterPort> waterPortList = waterPortDAO.findBySample(waterPort);
        if(waterPortList.size()>0){
            dictBeans.add(covertToDictBean("waterPort","废水排口",id,serial,"common/images/ztree/Water_Steam.png"));
            serial +=1;
            for(int i=0;i<waterPortList.size();i++){
                WaterPort wp = waterPortList.get(i);
                dictBeans.add(covertToDictBean(wp.getId(),wp.getName(),"waterPort",serial,"common/images/ztree/preferences_alt.png"));
                serial +=1;
            }
        }

        /**
         * 噪声源
         */
        NoisePort noisePort = new NoisePort();
        noisePort.setEnterpriseId(id);
        List<NoisePort> noisePortList = noisePortDAO.findBySample(noisePort);
        if(noisePortList.size()>0){
            dictBeans.add(covertToDictBean("noisePort","噪声排口",id,serial,"common/images/ztree/speaker_delete.png"));
            serial +=1;
            for(int i=0;i<noisePortList.size();i++){
                NoisePort np = noisePortList.get(i);
                dictBeans.add(covertToDictBean(np.getId(),np.getName(),"noisePort",serial,"common/images/ztree/preferences_alt.png"));
                serial +=1;
            }
        }

        /**
         * 油烟排口数据
         */
        FumesPort fumesPort = new FumesPort();
        fumesPort.setEnterpriseId(id);
        List<FumesPort> fumesPortList = fumesPortDAO.findBySample(fumesPort);
        if(fumesPortList.size()>0){
            dictBeans.add(covertToDictBean("fumesPort","油烟排口",id,serial,"common/images/ztree/cooker_hood.png"));
            serial +=1;
            for(int i=0;i<fumesPortList.size();i++){
                FumesPort fp = fumesPortList.get(i);
                dictBeans.add(covertToDictBean(fp.getId(),fp.getName(),"fumesPort",serial,"common/images/ztree/preferences_alt.png"));
                serial +=1;
            }
        }

        return dictBeans;
    }

    /**
     * 转换格式
     * @param code
     * @param name
     * @param parentCode
     * @param serial
     * @return
     */
    private DictBean covertToDictBean(String code,String name,String parentCode,Integer serial,String icon) {
        DictBean bean = new DictBean();
        bean.setCode(code);
        bean.setName(name);
        bean.setParentCode(parentCode);
        bean.setIcon(icon);
        bean.setSerial(serial);
        return bean;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<Enterprise> enterprises = getDAO().find("name like ?1", searchText);
        if (enterprises != null && enterprises.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (Enterprise enterprise : enterprises) {
                ZNodeDTO node = new ZNodeDTO(enterprise.getId(), enterprise.getName(), Enterprise.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<Enterprise> findByIds(String ...ids) {
        List<Enterprise> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }
}