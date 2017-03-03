-- 查询排口信息
select w.monitor_time,w.enterprise_code,w.enterprise_id,w.number,w.port_status from hw_dshbcbp_water_port w where w.enterprise_code is not null;
select w.monitor_time,w.enterprise_code,w.enterprise_id,w.number,w.port_status from hw_dshbcbp_gas_port w where w.enterprise_code is not null;


-- 废水废气数据库监测指标实时数据:

-- 废气
SELECT  
  w.Code_Pollute,w.StandardValue,w.PAvgValue,pZtdAvg,w.Datatime
FROM  
  T_AutoMoni_HourData_Gas w  
WHERE  
  w.Code_Pollute IN (  
    'A21002',  
    'A21026',  
    'A99060',  
    'A34013',  
    'A19001'  
  )  
AND w.PSCode = '150600000011'  
AND w.PointCode = '1'  
ORDER BY Datatime desc;


-- 废水
SELECT  
  w.Code_Pollute,w.StandardValue,w.PAvgValue,pZtdAvg,Datatime
FROM  
  T_AutoMoni_HourData_Water w  
WHERE  
  w.Code_Pollute IN (  
    'W01001',  
    'W99082',  
    'W01018',  
    'W21003'  
  )  
AND w.PSCode = '150600000008'  
AND w.PointCode = '1'  
AND w.Datatime >= '2017-02-26 22:00:00'
ORDER BY Datatime desc;

-- 手动执行定时：
http://172.17.29.46:8080/dshbcbp/action/S_port_WaterPortHistory_test.action
http://localhost:8081/dshbcbp/action/S_port_WaterPortHistory_test.action
