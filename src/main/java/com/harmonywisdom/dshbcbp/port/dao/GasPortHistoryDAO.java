package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.dao.MonitorCaseDAO;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.framework.dao.DefaultDAO;
import com.harmonywisdom.framework.util.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class GasPortHistoryDAO extends DefaultDAO<GasPortHistory, String> {
    @Autowired
    private JdbcTemplate portJdbcTemplate;

    @Autowired
    private GasPortDAO gasPortDAO;

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private PortStatusHistoryDAO portStatusHistoryDAO;

    @Autowired
    private MonitorCaseDAO monitorCaseDAO;

    /**
     * 通过时间查询最新的废气排口历史数据列表
     * @param enterpriseCode
     * @param enterpriseId
     * @param pointCode
     * @param portId
     * @param startTime
     * @return
     */
    public List<GasPortHistory> getGasPortHistoryDataListByDateTime(String enterpriseCode, String enterpriseId
            , String pointCode,String portId,String startTime){
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
                "  T_AutoMoni_HourData_Gas w  \n" +
                "WHERE  \n" +
                "  w.Code_Pollute IN (  \n" +
                "    'A21002',  \n" +
                "    'A21026',  \n" +
                "    'A99060',  \n" +
                "    'A34013',  \n" +
                "    'A19001'  \n" +
                "  )  \n" +
                "AND w.PSCode = '"+enterpriseCode+"'  \n" +
                "AND w.PointCode = '"+pointCode+"'  \n" +
                "AND w.Datatime > '"+startTime+"'";

        log.info("废气实时数据sql: \n"+sql);
        List<Map<String, Object>> list = portJdbcTemplate.queryForList(sql);
        List<GasPortHistory> historyList=new ArrayList<>();
        for (Map<String, Object> map : list) {
            GasPortHistory gasPortHistory = getGasPortHistory((Date) map.get("Datatime"),historyList);
            if(gasPortHistory == null){
                gasPortHistory = new GasPortHistory();
                gasPortHistory.setEnterpriseId(enterpriseId);
                gasPortHistory.setEnterpriseCode(enterpriseCode);
                gasPortHistory.setPortCode(pointCode);
                gasPortHistory.setPortId(portId);
                gasPortHistory.setMonitorTime((Date)map.get("Datatime"));
                gasPortHistory.setDataStatus("0");
                gasPortHistory.setNitrogenStatus("0");
                gasPortHistory.setSulfurStatus("0");
                gasPortHistory.setGasFlowStatus("0");
                gasPortHistory.setDustStatus("0");
                gasPortHistory.setOxygenStatus("0");
                historyList.add(gasPortHistory);
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

            if ("A21002".equals(code_pollute)){
                //氮氧化物
                gasPortHistory.setNitrogenLiveValue(LiveValue);
                gasPortHistory.setNitrogenStandardValue(standardValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        gasPortHistory.setNitrogenStatus("1");//超标
                    }
                }
            }else if ("A21026".equals(code_pollute)){
                //二氧化硫
                gasPortHistory.setSulfurLiveValue(LiveValue);
                gasPortHistory.setSulfurStandardValue(standardValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        gasPortHistory.setSulfurStatus("1");//超标
                    }
                }
            }else if ("A99060".equals(code_pollute)){
                //废气流量
                //废气流量不用判断超标
                if (map.get("pCountValue")!=null){
                    LiveValue = Double.valueOf(map.get("pCountValue").toString());
                }
                gasPortHistory.setGasFlowLiveValue(LiveValue);
                gasPortHistory.setGasFlowStandardValue(standardValue);
                gasPortHistory.setGasFlowStatus("0");
            }else if ("A34013".equals(code_pollute)){
                //烟尘
                gasPortHistory.setDustLiveValue(LiveValue);
                gasPortHistory.setDustStandardValue(standardValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        gasPortHistory.setDustStatus("1");//超标
                    }
                }
            }else if ("A19001".equals(code_pollute)){
                //氧含量
                gasPortHistory.setOxygenLiveValue(LiveValue);
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        gasPortHistory.setOxygenStatus("1");//超标
                    }
                }
                gasPortHistory.setOxygenStandardValue(standardValue);
            }

            if (!"A99060".equals(code_pollute)){//废气流量不用判断超标
                if (LiveValue!=0D && standardValue!=0D){
                    if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                        gasPortHistory.setDataStatus("1");//超标
                    }
                }
                //异常规则先注掉
//                if (!(exceptionMaxValue>LiveValue && LiveValue>exceptionMinValue)){
//                    gasPortHistory.setDataStatus("2");//异常
//                }
            }
        }
        return historyList;
    }

    private GasPortHistory getGasPortHistory(Date dateTime,List<GasPortHistory>  historyList){
        for(GasPortHistory obj : historyList){
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
    @Deprecated
    public GasPortHistory getGasPortHistoryData(String enterpriseCode, String enterpriseId, String pointCode,String portId,String startTime,String endTime){
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
                "  T_AutoMoni_HourData_Gas w  \n" +
                "WHERE  \n" +
                "  w.Code_Pollute IN (  \n" +
                "    'A21002',  \n" +
                "    'A21026',  \n" +
                "    'A99060',  \n" +
                "    'A34013',  \n" +
                "    'A19001'  \n" +
                "  )  \n" +
                "AND w.PSCode = '"+enterpriseCode+"'  \n" +
                "AND w.PointCode = '"+pointCode+"'  \n" +
                "AND w.Datatime >= '"+startTime+"'  \n" +
                "AND w.Datatime <= '"+endTime+"'";

        log.info("废气实时数据sql: \n"+sql);
        List<Map<String, Object>> list = portJdbcTemplate.queryForList(sql);
        if (list.size()>0){
            GasPortHistory gph=new GasPortHistory();
            gph.setDataStatus("0");
            gph.setPortId(portId);
            gph.setPortCode(pointCode);
            gph.setEnterpriseId(enterpriseId);
            gph.setMonitorTime(DateUtil.strToDate(startTime,"yyyy-MM-dd HH:mm:ss"));
            for (Map<String, Object> map : list) {
                /*（如果折算值为空，则取标准值）
                水：平均值（PAvgValue）  标准值（StandardValue）
                汽：平均折算值（pZtdAvg）  标准值（StandardValue）*/
                String code_pollute = map.get("Code_Pollute")+"";
                Double LiveValue =0D;
                Double standardValue =0D;
                Double standardMaxValue =0D;
                Double standardMinValue =0D;
                Double exceptionMaxValue =0D;
                Double exceptionMinValue =0D;
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
                if ("A21002".equals(code_pollute)){
                    //氮氧化物
                    gph.setNitrogenLiveValue(LiveValue);
                    gph.setNitrogenStandardValue(standardValue);
                    if (LiveValue!=0D && standardValue!=0D){
                        if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                            gph.setNitrogenStatus("1");//超标
                        }
                    }
                    gph.setNitrogenExceptionMinValue(exceptionMinValue);
                    gph.setNitrogenExceptionMaxValue(exceptionMaxValue);

                    p.setPollutantName("氮氧化物");
                }else if ("A21026".equals(code_pollute)){
                    //二氧化硫
                    gph.setSulfurLiveValue(LiveValue);
                    gph.setSulfurStandardValue(standardValue);
                    if (LiveValue!=0D && standardValue!=0D){
                        if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                            gph.setSulfurStatus("1");//超标
                        }
                    }
                    gph.setSulfurExceptionMaxValue(exceptionMaxValue);
                    gph.setSulfurExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("二氧化硫");
                }else if ("A99060".equals(code_pollute)){
                    //废气流量
                    if (map.get("pCountValue")!=null){
                        LiveValue = Double.valueOf(map.get("pCountValue").toString());
                    }
                    gph.setGasFlowLiveValue(LiveValue);
                    gph.setGasFlowStandardValue(standardValue);
                    gph.setGasFlowStatus("0");
                    gph.setGasFlowExceptionMaxValue(exceptionMaxValue);
                    gph.setGasFlowExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("废气流量");
                }else if ("A34013".equals(code_pollute)){
                    //烟尘
                    gph.setDustLiveValue(LiveValue);
                    gph.setDustStandardValue(standardValue);
                    if (LiveValue!=0D && standardValue!=0D){
                        if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                            gph.setDustStatus("1");//超标
                        }
                    }
                    gph.setDustExceptionMaxValue(exceptionMaxValue);
                    gph.setDustExceptionMinValue(exceptionMinValue);

                    p.setPollutantName("烟尘");
                }else if ("A19001".equals(code_pollute)){
                    //氧含量
                    gph.setOxygenLiveValue(LiveValue);
                    if (LiveValue!=0D && standardValue!=0D){
                        if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                            gph.setOxygenStatus("1");//超标
                        }
                    }
                    gph.setOxygenStandardValue(standardValue);
                    gph.setOxygenExceptionMinValue(exceptionMinValue);
                    gph.setOxygenExceptionMaxValue(exceptionMaxValue);

                    p.setPollutantName("氧含量");
                }

                if (!"A99060".equals(code_pollute)){//废气流量不用判断超标
                    if (LiveValue!=0D && standardValue!=0D){
                        if (LiveValue>standardValue){//折算值或平均值大于标准值为超标
                            gph.setDataStatus("1");//超标
                        }
                    }

                    //异常规则先注掉
//                if (!(exceptionMaxValue>LiveValue && LiveValue>exceptionMinValue)){
//                    gph.setDataStatus("2");//异常
//                }
                }


                //超标异常数据插入到超标记录表PortStatusHistory
                if(!"0".equals(gph.getDataStatus())){
                    p.setPortStatus(gph.getDataStatus());
                    GasPort gp = gasPortDAO.findById(gph.getPortId());
                    p.setPortId(gp.getId());
                    p.setPortNumber(gp.getNumber());
                    p.setPortName(gp.getName());
                    Enterprise e = enterpriseDAO.findById(gp.getEnterpriseId());
                    p.setEnterpriseId(e.getId());
                    p.setEnterpriseName(e.getName());
                    p.setEnterpriseType(e.getRegistType());
                    p.setBlockLevelId(e.getBlockLevelId());
                    p.setBlockId(e.getBlockId());
                    p.setPollutantCode(code_pollute);
                    p.setTime(gph.getMonitorTime());
                    p.setIsNoTickling("2");
                    portStatusHistoryDAO.save(p);
                    log.info("废气超标异常数据插入到超标记录表成功");

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
            return gph;
        }else {
            return null;
        }
    }
}