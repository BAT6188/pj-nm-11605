package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.dao.MonitorCaseDAO;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.dao.GasPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.GasPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.dao.PortStatusHistoryDAO;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.dshbcbp.port.service.GasPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("gasPortHistoryService")
public class GasPortHistoryServiceImpl extends BaseService<GasPortHistory, String> implements GasPortHistoryService {
    @Autowired
    private GasPortHistoryDAO gasPortHistoryDAO;

    @Autowired
    private GasPortDAO gasPortDAO;

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private PortStatusHistoryDAO portStatusHistoryDAO;

    @Autowired
    private MonitorCaseDAO monitorCaseDAO;

    /**
     * 超标的数据保存到 超标记录和超标预警表
     */
    public void saveChaoBiao(GasPortHistory gasPortHistory){
        if(!"0".equals(gasPortHistory.getDataStatus())){
            PortStatusHistory p=new PortStatusHistory();
            GasPort gp = gasPortDAO.findById(gasPortHistory.getPortId());
            p.setPortId(gp.getId());
            p.setPortNumber(gp.getNumber());
            p.setPortName(gp.getName());
            Enterprise e = enterpriseDAO.findById(gp.getEnterpriseId());
            p.setEnterpriseId(e.getId());
            p.setEnterpriseName(e.getName());
            p.setEnterpriseType(e.getRegistType());
            p.setBlockLevelId(e.getBlockLevelId());
            p.setBlockId(e.getBlockId());
            p.setTime(gasPortHistory.getMonitorTime());
            p.setIsNoTickling("2");

            MonitorCase mc=new MonitorCase();
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
            mc.setPortName(gp.getName());
            mc.setPollutantType("废水");
            mc.setEventTime(gasPortHistory.getMonitorTime());

            if(!"0".equals(gasPortHistory.getNitrogenStatus())){
                //氮氧化物
                p.setPortStatus(gasPortHistory.getNitrogenStatus());
                p.setPollutantName("氮氧化物");
                p.setPollutantCode("A21002");
                portStatusHistoryDAO.save(p);
                log.info("废气--氮氧化物超标异常数据插入到超标记录表成功");

                mc.setOverValue(gasPortHistory.getNitrogenLiveValue().toString());//检测值
                mc.setOverObj("氮氧化物");//监测指标
                mc.setThrValue(gasPortHistory.getNitrogenStandardValue().toString());//标准值
                if ("1".equals(gasPortHistory.getNitrogenStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(gasPortHistory.getNitrogenStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废气--氮氧化物插入超标预警记录表成功");
            }

            if(!"0".equals(gasPortHistory.getSulfurStatus())){
                //二氧化硫
                p.setPortStatus(gasPortHistory.getSulfurStatus());
                p.setPollutantName("二氧化硫");
                p.setPollutantCode("A21026");
                portStatusHistoryDAO.save(p);
                log.info("废气--二氧化硫超标异常数据插入到超标记录表成功");

                mc.setOverValue(gasPortHistory.getSulfurLiveValue().toString());//检测值
                mc.setOverObj("二氧化硫");//监测指标
                mc.setThrValue(gasPortHistory.getSulfurStandardValue().toString());//标准值
                if ("1".equals(gasPortHistory.getSulfurStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(gasPortHistory.getSulfurStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废气--二氧化硫插入超标预警记录表成功");
            }

            if(!"0".equals(gasPortHistory.getDustStatus())){
                //烟尘
                p.setPortStatus(gasPortHistory.getDustStatus());
                p.setPollutantName("烟尘");
                p.setPollutantCode("A21026");
                portStatusHistoryDAO.save(p);
                log.info("废气--烟尘超标异常数据插入到超标记录表成功");

                mc.setOverValue(gasPortHistory.getDustLiveValue().toString());//检测值
                mc.setOverObj("烟尘");//监测指标
                mc.setThrValue(gasPortHistory.getDustStandardValue().toString());//标准值
                if ("1".equals(gasPortHistory.getDustStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(gasPortHistory.getDustStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废气--烟尘插入超标预警记录表成功");
            }

            if(!"0".equals(gasPortHistory.getOxygenStatus())){
                //氧含量
                p.setPortStatus(gasPortHistory.getOxygenStatus());
                p.setPollutantName("氧含量");
                p.setPollutantCode("A21026");
                portStatusHistoryDAO.save(p);
                log.info("废气--烟尘超标异常数据插入到超标记录表成功");

                mc.setOverValue(gasPortHistory.getOxygenLiveValue().toString());//检测值
                mc.setOverObj("氧含量");//监测指标
                mc.setThrValue(gasPortHistory.getOxygenStandardValue().toString());//标准值
                if ("1".equals(gasPortHistory.getOxygenStatus())){//超标
                    mc.setReason("2");
                }else if ("2".equals(gasPortHistory.getOxygenStatus())){//异常
                    mc.setReason("1");
                }
                monitorCaseDAO.save(mc);
                log.info("废气--氧含量插入超标预警记录表成功");
            }
            log.info("超标的数据保存到 超标记录和超标预警表成功");
        }
    }

    /**
     * 保存废气排口历史数据列表，附带保存超标的记录到超标记录表和超标预警表
     */
    public void saveGasPortHistoryDataList() {
        try {
            List<GasPort> gasPortList = gasPortDAO.findAll();
            for (GasPort gasPort : gasPortList) {
                Date startTime=gasPort.getMonitorTime();
                if (null==startTime){
                    startTime=DateUtil.strToDate("2017-02-26 00:00:00","yyyy-MM-dd HH:mm:ss");
                }
                List<GasPortHistory> gasPortHistoryDataList = gasPortHistoryDAO.getGasPortHistoryDataListByDateTime(gasPort.getEnterpriseCode(), gasPort.getEnterpriseId(), gasPort.getNumber(), gasPort.getId(), DateUtil.dateToStr(startTime, "yyyy-MM-dd HH:mm:ss"));
                Date bigTime=startTime;
                for (GasPortHistory gasPortHistory : gasPortHistoryDataList) {
                    save(gasPortHistory);

                    try {
                        //超标的数据保存到 超标记录和超标预警表
                        saveChaoBiao(gasPortHistory);
                    } catch (Exception e) {
                        log.error("超标的数据保存到 超标记录和超标预警表失败",e);
                    }

                    if (gasPortHistory.getMonitorTime().getTime()>bigTime.getTime()){
                        bigTime=gasPortHistory.getMonitorTime();
                    }
                    if(!"0".equals(gasPortHistory.getDataStatus())){
                        gasPort.setPortStatus(gasPortHistory.getDataStatus());
                    }
                }
                log.info("id为"+gasPort.getEnterpriseId()+"的企业，编号为"+gasPort.getNumber()+"的排口成功插入废气排口历史数据表"+gasPortHistoryDataList.size()+"条记录");
                gasPort.setMonitorTime(bigTime);
                gasPortDAO.save(gasPort);
            }
            log.info("定时成功插入废气排口历史数据表，定时执行时间为"+DateUtil.dateToStr(new Date(),DateUtil.format1));
        } catch (Exception e) {
            log.error("定时插入废气排口历史数据表失败",e);
        }
    }


    @Override
    protected BaseDAO<GasPortHistory, String> getDAO() {
        return gasPortHistoryDAO;
    }

    @Deprecated
    public void saveTestGasPortHistoryData(String startTime,String endTime) {
        List<GasPort> gasPortList = gasPortDAO.findAll();
        for (GasPort gasPort : gasPortList) {
            GasPortHistory gasPortHistoryData = gasPortHistoryDAO.getGasPortHistoryData(gasPort.getEnterpriseCode(),
                    gasPort.getEnterpriseId(), gasPort.getNumber(),gasPort.getId(),startTime,endTime);
            if (gasPortHistoryData!=null){
                save(gasPortHistoryData);
            }
        }
    }

    /**
     * 保存当前时间段（如 15:00:00 - 15:59:59）的废气监测指标数据
     */
    @Deprecated
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