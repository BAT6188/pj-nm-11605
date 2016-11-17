package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.dshbcbp.composite.service.VideoService;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.port.bean.*;
import com.harmonywisdom.dshbcbp.port.dao.*;
import com.harmonywisdom.dshbcbp.utils.EntityUtil;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private PortThresholdDAO portThresholdDAO;

    @Autowired
    private VideoService videoService;



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
            dictBeans.add(covertToDictBean("gasPort","废气排口",id,serial,"common/images/ztree/department.png"));
            serial +=1;
            for(int i=0;i<gasPortList.size();i++){
                GasPort gp = gasPortList.get(i);
                dictBeans.add(covertToDictBean(gp.getId(),gp.getName(),"gasPort",serial,"common/images/ztree/activity_monitor_screen.png"));
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
            dictBeans.add(covertToDictBean("waterPort","废水排口",id,serial,"common/images/ztree/department.png"));
            serial +=1;
            for(int i=0;i<waterPortList.size();i++){
                WaterPort wp = waterPortList.get(i);
                dictBeans.add(covertToDictBean(wp.getId(),wp.getName(),"waterPort",serial,"common/images/ztree/activity_monitor_screen.png"));
                serial +=1;
            }
        }

        /**
         * 噪声源
         */
        /*NoisePort noisePort = new NoisePort();
        noisePort.setEnterpriseId(id);
        List<NoisePort> noisePortList = noisePortDAO.findBySample(noisePort);
        if(noisePortList.size()>0){
            dictBeans.add(covertToDictBean("noisePort","噪声排口",id,serial,"common/images/ztree/department.png"));
            serial +=1;
            for(int i=0;i<noisePortList.size();i++){
                NoisePort np = noisePortList.get(i);
                dictBeans.add(covertToDictBean(np.getId(),np.getName(),"noisePort",serial,"common/images/ztree/activity_monitor_screen.png"));
                serial +=1;
            }
        }*/

        return dictBeans;
    }

    @Override
    public List<DictBean> getFumesEnterprisePortZtree(String id) {
        List<DictBean> dictBeans = new ArrayList<DictBean>();

        Enterprise enterprise = enterpriseDAO.findById(id);
        dictBeans.add(covertToDictBean(enterprise.getId(),enterprise.getName(),"-1",0,"common/images/ztree/Factory_Yellow.png"));

        Integer serial = 1;
        /**
         * 油烟排口数据
         */
        FumesPort fumesPort = new FumesPort();
        fumesPort.setEnterpriseId(id);
        List<FumesPort> fumesPortList = fumesPortDAO.findBySample(fumesPort);
        if(fumesPortList.size()>0){
            dictBeans.add(covertToDictBean("fumesPort","油烟排口",id,serial,"common/images/ztree/department.png"));
            serial +=1;
            for(int i=0;i<fumesPortList.size();i++){
                FumesPort fp = fumesPortList.get(i);
                dictBeans.add(covertToDictBean(fp.getId(),fp.getName(),"fumesPort",serial,"common/images/ztree/activity_monitor_screen.png"));
                serial +=1;
            }
        }
        return dictBeans;
    }

    @Override
    public List<Map<String, String>> queryEnterpriseAlertStatus(String... ids) {
        List<Map<String, String>> result = new ArrayList<>();
        for (String id: ids) {
            Map<String, String> enterpriseAlertStatus = new HashMap<>();
            enterpriseAlertStatus.put("id", id);
            enterpriseAlertStatus.put("status", queryEnterpriseAlertStatus(id));
            result.add(enterpriseAlertStatus);
        }
        return result;
    }

    @Override
    public String updateEnterprise(Enterprise enterprise) {
        Map<String,Object> map = EntityUtil.getUpdateMap(enterprise);
        return String.valueOf(enterpriseDAO.executeJPQL(String.valueOf(map.get("upStr")),(Map<String, Object>)map.get("valMap")));
    }

    /**
     * 查询单个企业报警状态
     * @param id
     * @return status
     */
    private String queryEnterpriseAlertStatus(String id) {
        List<Object> gasPortAlertCountResult = gasPortDAO.queryNativeSQL("select count(*) from HW_DSHBCBP_GAS_PORT where enterprise_Id=?1 and port_Status=1",id);
        int gasAlertCount = Integer.valueOf(gasPortAlertCountResult.get(0).toString());
        if (gasAlertCount > 0) {
            return PortStatusHistory.STATUS_OVER;
        }
        List<Object> waterPortAlertCount = waterPortDAO.queryNativeSQL("select count(*) from HW_DSHBCBP_WATER_PORT where enterprise_Id=?1 and port_Status=1",id);
        int waterAlertCount = Integer.valueOf(waterPortAlertCount.get(0).toString());
        if (waterAlertCount > 0) {
            return PortStatusHistory.STATUS_OVER;
        }
        List<Object> fumesPortAlertCount = fumesPortDAO.queryNativeSQL("select count(*) from HW_DSHBCBP_FUMES_PORT where enterprise_Id=?1 and port_Status=1",id);
        int fumesAlertCount = Integer.valueOf(fumesPortAlertCount.get(0).toString());
        if (fumesAlertCount > 0) {
            return PortStatusHistory.STATUS_OVER;
        }
        /*List<Object> noisePortAlertCount = noisePortDAO.queryNativeSQL("select count(*) from HW_DSHBCBP_NOISE_PORT where enterprise_Id=?1 and port_Status=1",id);
        int noiseAlertCount = Integer.valueOf(noisePortAlertCount.get(0).toString());
        if (noiseAlertCount > 0) {
            return PortStatusHistory.STATUS_OVER;
        }*/
        return PortStatusHistory.STATUS_NORMAL;
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
        List<Enterprise> enterprises = getDAO().find("name like ?1 and isDel=0", searchText);
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

    @Override
    public Map<String,List> findMarkedPortsByEnterpriseId(String enterpriseId) {
        Map<String, List> portsMap = new HashMap<>();
        List<GasPort> gasPorts = gasPortDAO.find("enterpriseId=?1 and planeMapMark is not null",enterpriseId);
        if (gasPorts != null && gasPorts.size() > 0) {
            portsMap.put(GasPort.class.getSimpleName(), gasPorts);
        }

        List<WaterPort> waterPorts = waterPortDAO.find("enterpriseId=?1 and planeMapMark is not null",enterpriseId);
        if (waterPorts != null && waterPorts.size() > 0) {
            portsMap.put(WaterPort.class.getSimpleName(), waterPorts);
        }

        List<FumesPort> fumesPorts = fumesPortDAO.find("enterpriseId=?1 and planeMapMark is not null",enterpriseId);
        if (fumesPorts != null && fumesPorts.size() > 0) {
            portsMap.put(FumesPort.class.getSimpleName(), fumesPorts);
        }

        /*List<NoisePort> noisePorts = noisePortDAO.find("enterpriseId=?1 and planeMapMark is not null",enterpriseId);
        if (noisePorts != null && noisePorts.size() > 0) {
            portsMap.put(NoisePort.class.getSimpleName(), noisePorts);
        }*/
        List<Video> videos = videoService.find("unitId=?1 and planeMapMark is not null",enterpriseId);
        if (videos != null && videos.size() > 0) {
            portsMap.put(Video.class.getSimpleName(), videos);
        }
        return portsMap;
    }

    /**
     *
     * @param userName 登录名称
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> doLogin(String userName, String password) {
        Map<String,Object> map = new HashMap<String, Object>();
        Enterprise u = this.getByUserName(userName);
        if(u!=null){
            if(u.getPassword().equals(password)){
                //密码正确，登入成功后要清空登入失败次数字段
                map.put("status",1);
                map.put("session",u);
                return map;
            }else{
                //密码错误，返回当前用户登入的剩余的次数
                map.put("status",-1);
                return map;
            }
        }else{
            //企业组织机构代码错误
            map.put("status",-2);
            return map;
        }
    }

    @Override
    public Enterprise getByUserName(String userName) {
        List<Enterprise> companyList = getDAO().queryJPQL("from Enterprise t where t.userName = ?", userName);
        if (companyList != null && companyList.size() > 0) {
            return companyList.get(0);
        }

        return null;
    }

    @Override
    public String delete(String enterpriseId) {
        /**
         * 废气排口数据
         */
        GasPort gasPort = new GasPort();
        gasPort.setEnterpriseId(enterpriseId);
        List<GasPort> gasPortList = gasPortDAO.findBySample(gasPort);
        if(gasPortList.size()>0){
            for(GasPort gp:gasPortList){
                gasPortDAO.delete(gp);
            }
        }

        /**
         * 废水排口数据
         */
        WaterPort waterPort = new WaterPort();
        waterPort.setEnterpriseId(enterpriseId);
        List<WaterPort> waterPortList = waterPortDAO.findBySample(waterPort);
        if(waterPortList.size()>0){
            for(WaterPort wp:waterPortList){
                waterPortDAO.delete(wp);
            }
        }

        /**
         * 油烟排口数据
         */
        FumesPort fumesPort = new FumesPort();
        fumesPort.setEnterpriseId(enterpriseId);
        List<FumesPort> fumesPortList = fumesPortDAO.findBySample(fumesPort);
        if(fumesPortList.size()>0){
            for(FumesPort fp:fumesPortList){
                fumesPortDAO.delete(fp);
            }
        }

        /**
         * 排口数据
         */
        PortThreshold portThreshold = new PortThreshold();
        portThreshold.setEnterpriseId(enterpriseId);
        List<PortThreshold> portThresholds = portThresholdDAO.findBySample(portThreshold);
        if(portThresholds.size()>0){
            for(PortThreshold pt:portThresholds){
                portThresholdDAO.remove(pt);
            }
        }
        return enterpriseId;
    }

    @Override
    public Enterprise findById(String s) {
        Enterprise enterprise = super.findById(s);
        List<Enterprise> enterpriseList = getDAO().findAll();
        enterprise.setEnterpriseList(JSONArray.toJSONString(enterpriseList));
        return enterprise;
    }

}