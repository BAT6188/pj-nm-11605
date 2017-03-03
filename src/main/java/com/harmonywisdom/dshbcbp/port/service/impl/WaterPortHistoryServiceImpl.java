package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.dao.MonitorCaseDAO;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.port.bean.*;
import com.harmonywisdom.dshbcbp.port.dao.PortStatusHistoryDAO;
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

    @Autowired
    private PortStatusHistoryDAO portStatusHistoryDAO;

    @Autowired
    private MonitorCaseDAO monitorCaseDAO;


    /**
     * 超标的数据保存到 超标记录和超标预警表
     */
    public void saveChaoBiao(WaterPortHistory waterPortHistory){
        if(!"0".equals(waterPortHistory.getDataStatus())){
            PortStatusHistory p=new PortStatusHistory();
            WaterPort wp = waterPortDAO.findById(waterPortHistory.getPortId());
            p.setPortId(wp.getId());
            p.setPortNumber(wp.getNumber());
            p.setPortName(wp.getName());
            Enterprise e = enterpriseDAO.findById(wp.getEnterpriseId());
            p.setEnterpriseId(e.getId());
            p.setEnterpriseName(e.getName());
            p.setEnterpriseType(e.getRegistType());
            p.setBlockLevelId(e.getBlockLevelId());
            p.setBlockId(e.getBlockId());
            p.setTime(waterPortHistory.getMonitorTime());
            p.setIsNoTickling("2");

            MonitorCase mc=new MonitorCase();
            mc.setPortId(waterPortHistory.getPortId());
            mc.setPortHistoryId(waterPortHistory.getId());
            mc.setPortType("w");
            mc.setStatus("0");
            mc.setSelfReadStatus("0");
            mc.setPunishStatus("0");
            mc.setSource("0");
            mc.setEnterpriseId(e.getId());
            mc.setEnterpriseName(e.getName());
            mc.setBlockId(e.getBlockId());
            mc.setBlockName(e.getBlockName());
            mc.setBlockLevelId(e.getBlockLevelId());
            mc.setBlockLevelName(e.getBlockLevelName());
            mc.setSupervisor(e.getEnvPrincipal());
            mc.setSupervisorPhone(e.getEpPhone());
            mc.setPortName(wp.getName());
            mc.setPollutantType("废水");
            mc.setEventTime(waterPortHistory.getMonitorTime());

            if(!"0".equals(waterPortHistory.getPhStatus())){
                //pH值
                p.setPortStatus(waterPortHistory.getPhStatus());
                p.setPollutantName("pH值");
                p.setPollutantCode("W01001");
                portStatusHistoryDAO.save(p);
                log.info("废水--pH值超标异常数据插入到超标记录表成功");

                mc.setOverValue(waterPortHistory.getPhLiveValue().toString());//检测值
                mc.setOverObj("pH值");//监测指标
                mc.setThrValue(waterPortHistory.getPhStandardValue().toString());//标准值
                if ("1".equals(waterPortHistory.getPhStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(waterPortHistory.getPhStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废水--pH值插入超标预警记录表成功");
            }

            if(!"0".equals(waterPortHistory.getOxygenStatus())){
                //化学需氧量
                p.setPortStatus(waterPortHistory.getOxygenStatus());
                p.setPollutantName("化学需氧量");
                p.setPollutantCode("W01018");
                portStatusHistoryDAO.save(p);
                log.info("废水--化学需氧量超标异常数据插入到超标记录表成功");

                mc.setOverValue(waterPortHistory.getOxygenLiveValue().toString());//检测值
                mc.setOverObj("化学需氧量");//监测指标
                mc.setThrValue(waterPortHistory.getOxygenStandardValue().toString());//标准值
                if ("1".equals(waterPortHistory.getOxygenStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(waterPortHistory.getOxygenStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废水--化学需氧量插入超标预警记录表成功");
            }

            if(!"0".equals(waterPortHistory.getNitrogenStatus())){
                //氨氮
                p.setPortStatus(waterPortHistory.getNitrogenStatus());
                p.setPollutantName("氨氮");
                p.setPollutantCode("W21003");
                portStatusHistoryDAO.save(p);
                log.info("废水--氨氮超标异常数据插入到超标记录表成功");

                mc.setOverValue(waterPortHistory.getNitrogenLiveValue().toString());//检测值
                mc.setOverObj("氨氮");//监测指标
                mc.setThrValue(waterPortHistory.getNitrogenStandardValue().toString());//标准值
                if ("1".equals(waterPortHistory.getNitrogenStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(waterPortHistory.getNitrogenStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废水--氨氮插入超标预警记录表成功");
            }


            log.info("超标的数据保存到 超标记录和超标预警表成功");
        }
    }

    /**
     * 保存废水排口历史数据列表，附带保存超标的记录到超标记录表和超标预警表
     */
    public void saveWaterPortHistoryDataList() {
        try {
            List<WaterPort> waterPortList = waterPortDAO.findAll();
            for (WaterPort waterPort : waterPortList) {
                Date startTime=waterPort.getMonitorTime();
                if (null==startTime){
                    startTime= DateUtil.strToDate("2017-02-26 00:00:00","yyyy-MM-dd HH:mm:ss");
                }
                List<WaterPortHistory> waterPortHistoryDataList = waterPortHistoryDAO.getWaterPortHistoryDataListByDateTime(waterPort.getEnterpriseCode(), waterPort.getEnterpriseId(), waterPort.getNumber(), waterPort.getId(), DateUtil.dateToStr(startTime, "yyyy-MM-dd HH:mm:ss"));
                Date bigTime=startTime;
                for (WaterPortHistory waterPortHistory : waterPortHistoryDataList) {
                    save(waterPortHistory);

                    try {
                        //超标的数据保存到 超标记录和超标预警表
                        saveChaoBiao(waterPortHistory);
                    } catch (Exception e) {
                        log.error("超标的数据保存到 超标记录和超标预警表失败",e);
                    }

                    if (waterPortHistory.getMonitorTime().getTime()>bigTime.getTime()){
                        bigTime=waterPortHistory.getMonitorTime();
                    }
                    if(!"0".equals(waterPortHistory.getDataStatus())){
                        waterPort.setPortStatus(waterPortHistory.getDataStatus());
                    }
                }
                log.info("id为"+waterPort.getEnterpriseId()+"的企业，编号为"+waterPort.getNumber()+"的排口成功插入废水排口历史数据表"+waterPortHistoryDataList.size()+"条记录");
                waterPort.setMonitorTime(bigTime);
                waterPortDAO.save(waterPort);
            }
            log.info("定时成功插入废水排口历史数据表，定时执行时间为"+DateUtil.dateToStr(new Date(),DateUtil.format1));
        } catch (Exception e) {
            log.error("定时插入废水排口历史数据表失败",e);
        }
    }

    /*@Deprecated
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
    }*/

    /**
     * 保存当前时间段（如 15:00:00 - 15:59:59）的废水监测指标数据
     */
    /*@Deprecated
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
    }*/

    @Override
    protected BaseDAO<WaterPortHistory, String> getDAO() {
        return waterPortHistoryDAO;
    }
}