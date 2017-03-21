package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.dao.MonitorCaseDAO;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class WaterPortHistoryDAO extends DefaultDAO<WaterPortHistory, String> {
    @Autowired
    private JdbcTemplate portJdbcTemplate;


    @Autowired
    private WaterPortDAO waterPortDAO;

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private PortStatusHistoryDAO portStatusHistoryDAO;

    @Autowired
    private MonitorCaseDAO monitorCaseDAO;

    /**
     * 通过时间查询最新的废水排口历史数据列表
     * @param enterpriseCode
     * @param enterpriseId
     * @param pointCode
     * @param portId
     * @param startTime
     * @return
     */
    public List<WaterPortHistory> getWaterPortHistoryDataListByDateTime(String enterpriseCode, String enterpriseId
            , String pointCode, String portId, String startTime){
        String sql="SELECT  \n" +
                "  w.PSCode,  \n" +
                "  w.PointCode,  \n" +
                "  w.Datatime,  \n" +
                "  w.Code_Pollute,  \n" +
                "  w.standardValue,  \n" +
                "  w.pZtdAvg,  \n" +
                "  w.standardMinValue,  \n" +
                "  w.exceptionMaxValue,  \n" +
                "  w.exceptionMinValue,  \n" +
                "  w.StandardMaxValue,  \n" +
                "  w.pCountValue,  \n" +
                "  w.PAvgValue  \n" +
                "FROM  \n" +
                "  T_AutoMoni_HourData_Water w  \n" +
                "WHERE  \n" +
                "  w.Code_Pollute IN (  \n" +
                "    'W01001',  \n" +
                "    'W99082',  \n" +
                "    'W01018',  \n" +
                "    'W21003'  \n" +
                "  )  \n" +
                "AND w.PSCode = '"+enterpriseCode+"'  \n" +
                "AND w.PointCode = '"+pointCode+"'  \n" +
                "AND w.Datatime > '"+startTime+"'";

        log.info("废水实时数据sql: \n"+sql);
        List<Map<String, Object>> list = portJdbcTemplate.queryForList(sql);
        List<WaterPortHistory> historyList=new ArrayList<>();
        for (Map<String, Object> map : list) {
            WaterPortHistory waterPortHistory = getWaterPortHistory((Date) map.get("Datatime"),historyList);
            if(waterPortHistory == null){
                waterPortHistory = new WaterPortHistory();
                waterPortHistory.setEnterpriseId(enterpriseId);
                waterPortHistory.setEnterpriseCode(enterpriseCode);
                waterPortHistory.setPortCode(pointCode);
                waterPortHistory.setPortId(portId);
                waterPortHistory.setMonitorTime((Date)map.get("Datatime"));
                waterPortHistory.setDataStatus("0");
                waterPortHistory.setNitrogenStatus("0");
                waterPortHistory.setOxygenStatus("0");
                waterPortHistory.setFlowStatus("0");
                waterPortHistory.setPhStatus("0");
                waterPortHistory.setOxygenStatus("0");
                historyList.add(waterPortHistory);
            }

            String code_pollute = map.get("Code_Pollute").toString();
            Double LiveValue =0D;
            Double standardValue =0D;
            if (map.get("pZtdAvg")!=null){
                LiveValue = Double.valueOf(map.get("pZtdAvg").toString());
            }else {
                if (map.get("PAvgValue")!=null){
                    LiveValue = Double.valueOf(map.get("PAvgValue").toString());
                }
            }
            if (map.get("StandardValue")!=null){
                standardValue = Double.valueOf(map.get("StandardValue").toString());
            }

            if ("W01001".equals(code_pollute)){
                //pH值
                waterPortHistory.setPhLiveValue(LiveValue);
                waterPortHistory.setPhStandardValue(standardValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        waterPortHistory.setPhStatus("1");//超标
                    }
                }
            }else if ("W01018".equals(code_pollute)){
                //化学需氧量
                waterPortHistory.setOxygenLiveValue(LiveValue);
                waterPortHistory.setOxygenStandardValue(standardValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        waterPortHistory.setOxygenStatus("1");//超标
                    }
                }
            }else if ("W99082".equals(code_pollute)){
                //污水流量
                //污水流量不用判断超标
                if (map.get("pCountValue")!=null){
                    LiveValue = Double.valueOf(map.get("pCountValue").toString());
                }
                waterPortHistory.setFlowLiveValue(LiveValue);
                waterPortHistory.setFlowStandardValue(standardValue);
                waterPortHistory.setFlowStatus("0");
            }else if ("W21003".equals(code_pollute)){
                //氨氮
                waterPortHistory.setNitrogenLiveValue(LiveValue);
                waterPortHistory.setNitrogenStandardValue(standardValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        waterPortHistory.setNitrogenStatus("1");//超标
                    }
                }
            }

            if (!"W99082".equals(code_pollute)){//废气流量不用判断超标
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        waterPortHistory.setDataStatus("1");//超标
                    }
                }
                //异常规则先注掉
//                if (!(exceptionMaxValue>LiveValue && LiveValue>exceptionMinValue)){
//                    waterPortHistory.setDataStatus("2");//异常
//                }
            }
        }
        return historyList;
    }

    private WaterPortHistory getWaterPortHistory(Date dateTime,List<WaterPortHistory>  historyList){
        for(WaterPortHistory obj : historyList){
            if(dateTime.equals(obj.getMonitorTime()) ){
                return obj;
            }
        }
        return null;
    }

    /**
     * 得到当前时间段（如 15:00:00 - 15:59:59）的废水监测指标数据
     * @param enterpriseCode
     * @param pointCode
     * @return
     */
    /*@Deprecated
    public WaterPortHistory getWaterPortHistoryData(String enterpriseCode,String enterpriseId,String pointCode,String portId,String startTime,String endTime)throws Exception{
        String sql="SELECT  \n" +
                "  w.PSCode,  \n" +
                "  w.PointCode,  \n" +
                "  w.Datatime,  \n" +
                "  w.Code_Pollute,  \n" +
                "  w.standardValue,  \n" +
                "  w.pZtdAvg,  \n" +
                "  w.standardMinValue,  \n" +
                "  w.pCountValue,  \n" +
                "  w.exceptionMaxValue,  \n" +
                "  w.exceptionMinValue,  \n" +
                "  w.StandardMaxValue,  \n" +
                "  w.PAvgValue  \n" +
                "FROM  \n" +
                "  T_AutoMoni_HourData_Water w  \n" +
                "WHERE  \n" +
                "  w.Code_Pollute IN (  \n" +
                "    'W01001',  \n" +
                "    'W99082',  \n" +
                "    'W01018',  \n" +
                "    'W21003'  \n" +
                "  )  \n" +
                "AND w.PSCode = '"+enterpriseCode+"'  \n" +
                "AND w.PointCode = '"+pointCode+"'  \n" +
                "AND w.Datatime >= '"+startTime+"'  \n" +
                "AND w.Datatime <= '"+endTime+"'";
        log.info("废水实时数据sql: \n"+sql);
        List<Map<String, Object>> list = portJdbcTemplate.queryForList(sql);
        if (list.size()>0){
            WaterPortHistory wph=new WaterPortHistory();
            wph.setPortId(portId);
            wph.setPortCode(pointCode);
            wph.setEnterpriseId(enterpriseId);
            wph.setEnterpriseCode(enterpriseCode);
            wph.setMonitorTime(DateUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss"));
            wph.setDataStatus("0");
            for (Map<String, Object> map : list) {
                *//*（如果折算值为空，则取标准值）
                水：平均值（PAvgValue）  标准值（StandardValue）
                汽：平均折算值（pZtdAvg）  标准值（StandardValue）*//*
                String code_pollute = map.get("Code_Pollute")+"";
                Double liveValue =0D;
                Double standardValue =0D;
                Double standardMaxValue =0D;
                Double standardMinValue =0D;
                Double exceptionMaxValue =0D;
                Double exceptionMinValue =0D;
                if (map.get("pZtdAvg")!=null){
                    liveValue = Double.valueOf(map.get("pZtdAvg").toString());
                }else {
                    if (map.get("PAvgValue")!=null){
                        liveValue = Double.valueOf(map.get("PAvgValue").toString());
                    }else {
                        liveValue = Double.valueOf(map.get("pCountValue").toString());
                    }
                }

                if (map.get("StandardValue")!=null){
                    standardValue = Double.valueOf(map.get("StandardValue").toString());
                }
                if (map.get("StandardMaxValue")!=null){
                    standardMaxValue = Double.valueOf(map.get("StandardMaxValue").toString());
                }
                if (map.get("StandardMinValue")!=null){
                    standardMinValue = Double.valueOf(map.get("StandardMinValue").toString());
                }
                if (map.get("ExceptionMaxValue")!=null){
                    exceptionMaxValue = Double.valueOf(map.get("ExceptionMaxValue").toString());
                }
                if (map.get("ExceptionMinValue")!=null){
                    exceptionMinValue = Double.valueOf(map.get("ExceptionMinValue").toString());
                }

                PortStatusHistory p=new PortStatusHistory();
                Double monitorCaseLiveValue=0D;
                if ("W01001".equals(code_pollute)){
                    //pH值
                    wph.setPhLiveValue(liveValue);
                    wph.setPhStandardValue(standardMaxValue);
//                    wph.setPhStandardMinValue(standardMinValue);
                    wph.setPhExceptionMaxValue(exceptionMaxValue);
                    wph.setPhExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("pH值");

                    monitorCaseLiveValue=liveValue;
                }else if ("W99082".equals(code_pollute)){
                    //污水流量
                    wph.setFlowLiveValue(liveValue);
                    wph.setFlowStandardValue(standardMaxValue);
//                    wph.setFlowStandardMinValue(standardMinValue);
                    wph.setFlowExceptionMaxValue(exceptionMaxValue);
                    wph.setFlowExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("污水流量");

                    monitorCaseLiveValue=liveValue;
                }else if ("W01018".equals(code_pollute)){
                    //化学需氧量
                    wph.setOxygenLiveValue(liveValue);
                    wph.setOxygenStandardValue(standardMaxValue);
//                    wph.setOxygenStandardMinValue(standardMinValue);
                    wph.setOxygenExceptionMaxValue(exceptionMaxValue);
                    wph.setOxygenExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("化学需氧量");

                    monitorCaseLiveValue=liveValue;
                }else if ("W21003".equals(code_pollute)){
                    //氨氮
                    wph.setNitrogenLiveValue(liveValue);
                    wph.setNitrogenStandardValue(standardMaxValue);
//                    wph.setNitrogenStandardMinValue(standardMinValue);
                    wph.setNitrogenExceptionMaxValue(exceptionMaxValue);
                    wph.setNitrogenExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("氨氮");

                    monitorCaseLiveValue=liveValue;
                }

                if (!"W99082".equals(code_pollute)) {//废水流量不用判断超标
                    if (liveValue!=0D && standardValue!=0D){
                        if (liveValue>standardValue){//折算值或平均值大于标准值为超标
                            wph.setDataStatus("1");//超标
                        }
                    }

                    //异常规则先注掉
//                if (!(exceptionMaxValue>liveValue && liveValue>exceptionMinValue)){
//                    wph.setDataStatus("2");//异常
//                }
                }


                if(!"0".equals(wph.getDataStatus())){
                    p.setPortStatus(wph.getDataStatus());
                    WaterPort wp = waterPortDAO.findById(wph.getPortId());
                    p.setPortId(wp.getId());
                    p.setPortNumber(wp.getNumber());
                    p.setPortName(wp.getName());
                    Enterprise e = enterpriseDAO.findById(wp.getEnterpriseId());
                    p.setEnterpriseId(e.getId());
                    p.setEnterpriseName(e.getName());
                    p.setEnterpriseType(e.getRegistType());
                    p.setBlockLevelId(e.getBlockLevelId());
                    p.setBlockId(e.getBlockId());
                    p.setPollutantCode(code_pollute);
                    p.setTime(wph.getMonitorTime());
                    p.setIsNoTickling("2");
                    //超标异常数据插入到超标记录表PortStatusHistory
                    portStatusHistoryDAO.save(p);
                    log.info("超标异常数据插入到超标记录表成功");

                    MonitorCase mc=new MonitorCase();
                    mc.setStatus("0");
                    mc.setSelfReadStatus("0");
                    mc.setPunishStatus("0");
                    mc.setSource("0");
                    mc.setEnterpriseId(p.getEnterpriseId());
                    mc.setEnterpriseName(p.getEnterpriseName());
                    mc.setBlockId(p.getBlockId());
                    mc.setBlockName(e.getBlockName());
                    mc.setBlockLevelId(p.getBlockLevelId());
                    mc.setBlockLevelName(e.getBlockLevelName());
                    mc.setEventTime(p.getTime());
                    mc.setSupervisor(e.getEnvPrincipal());
                    mc.setSupervisorPhone(e.getEpPhone());
                    if ("1".equals(p.getPortStatus())){//超标
                        mc.setReason("2");
                    }else if ("2".equals(p.getPortStatus())){//异常
                        mc.setReason("1");
                    }
                    mc.setPortName(p.getPortName());
                    mc.setPollutantType("废水");
                    mc.setOverObj(p.getPollutantName());
                    mc.setOverValue(monitorCaseLiveValue.toString());
                    mc.setThrValue(standardValue.toString());
                    //插入超标预警记录表（监控中心表）
                    monitorCaseDAO.save(mc);
                    log.info("插入超标预警记录表成功");
                }
            }

            return wph;
        }else {
            return null;
        }
    }*/

}