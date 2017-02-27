package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.dao.GasPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.GasPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.dshbcbp.port.service.GasPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service("gasPortHistoryService")
public class GasPortHistoryServiceImpl extends BaseService<GasPortHistory, String> implements GasPortHistoryService {
    @Autowired
    private GasPortHistoryDAO gasPortHistoryDAO;

    @Autowired
    private GasPortDAO gasPortDAO;

    @Autowired
    private EnterpriseDAO enterpriseDAO;


    @Override
    protected BaseDAO<GasPortHistory, String> getDAO() {
        return gasPortHistoryDAO;
    }

    public void saveTestGasPortHistoryData(String startTime,String endTime) {
        List<GasPort> gasPortList = gasPortDAO.findAll();
        for (GasPort gasPort : gasPortList) {
            GasPortHistory gasPortHistoryData = gasPortHistoryDAO.getGasPortHistoryData(gasPort.getEnterpriseCode(), gasPort.getEnterpriseId(), gasPort.getNumber(),gasPort.getId(),startTime,endTime);
            if (gasPortHistoryData!=null){
                save(gasPortHistoryData);
            }
        }
    }

    /**
     * 保存当前时间段（如 15:00:00 - 15:59:59）的废气监测指标数据
     */
    public void saveGasPortHistoryData() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String startTime=df.format(calendar.getTime());
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        String endTime=df.format(calendar.getTime());
        List<GasPort> gasPortList = gasPortDAO.findAll();
        for (GasPort gasPort : gasPortList) {
            GasPortHistory gasPortHistoryData = gasPortHistoryDAO.getGasPortHistoryData(gasPort.getEnterpriseCode(), gasPort.getEnterpriseId(), gasPort.getNumber(),gasPort.getId(),startTime,endTime);
            if (gasPortHistoryData!=null){
                save(gasPortHistoryData);
            }
        }
    }
}