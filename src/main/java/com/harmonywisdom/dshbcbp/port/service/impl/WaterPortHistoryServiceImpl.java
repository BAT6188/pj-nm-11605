package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.WaterPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("waterPortHistoryService")
public class WaterPortHistoryServiceImpl extends BaseService<WaterPortHistory, String> implements WaterPortHistoryService {
    @Autowired
    private WaterPortHistoryDAO waterPortHistoryDAO;

    @Autowired
    private WaterPortDAO waterPortDAO;

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    public void saveTestWaterPortHistoryData(String startTime,String endTime){
        try {
            List<WaterPort> waterPortList = waterPortDAO.findAll();
            for (WaterPort waterPort : waterPortList) {
                WaterPortHistory w = waterPortHistoryDAO.getWaterPortHistoryData(waterPort.getEnterpriseCode(), waterPort.getEnterpriseId(), waterPort.getNumber(),waterPort.getId(),startTime,endTime);
                if (w!=null){
                    save(w);
                }
            }
            log.info("加载废水实时数据成功");
        } catch (Exception e) {
            log.error("加载废水实时数据失败",e);
        }
    }

    /**
     * 保存当前时间段（如 15:00:00 - 15:59:59）的废水监测指标数据
     */
    public void saveWaterPortHistoryData(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String startTime=df.format(calendar.getTime());
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        String endTime=df.format(calendar.getTime());
        try {
            List<WaterPort> waterPortList = waterPortDAO.findAll();
            for (WaterPort waterPort : waterPortList) {
                WaterPortHistory w = waterPortHistoryDAO.getWaterPortHistoryData(waterPort.getEnterpriseCode(), waterPort.getEnterpriseId(), waterPort.getNumber(),waterPort.getId(),startTime,endTime);
                if (w!=null){
                    save(w);
                }
            }
            log.info("加载废水实时数据成功");
        } catch (Exception e) {
            log.error("加载废水实时数据失败",e);
        }
    }

    @Override
    protected BaseDAO<WaterPortHistory, String> getDAO() {
        return waterPortHistoryDAO;
    }
}