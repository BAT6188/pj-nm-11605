-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile
-- 废水废气数据库监测指标实时数据:

-- 废气
SELECT  
  w.Code_Pollute,w.StandardValue,w.PAvgValue,pZtdAvg
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
AND w.Datatime >= '2017-02-26 21:00:00'  
AND w.Datatime <= '2017-02-26 21:59:59';


-- 废水
SELECT  
  w.Code_Pollute,w.StandardValue,w.PAvgValue,pZtdAvg
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
AND w.Datatime <= '2017-02-26 22:59:59';