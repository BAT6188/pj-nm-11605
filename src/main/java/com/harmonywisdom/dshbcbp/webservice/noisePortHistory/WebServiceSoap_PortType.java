/**
 * WebServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public interface WebServiceSoap_PortType extends java.rmi.Remote {

    /**
     * 登录验证
     */
    public boolean checkUser(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取用户有权限的监测点和区域
     */
    public GetAreaPointDataResponseGetAreaPointDataResult getAreaPointData(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 保存密码
     */
    public boolean savePwd(String strUserName, String strOldPwd, String strNewPwd) throws java.rmi.RemoteException;

    /**
     * 获取用户详细信息
     */
    public TSysUserVo getUserInfoData(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取监测点详细信息
     */
    public TSysStationPointVo getPointInfoData(String strPointCode, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取所有监测点状态
     */
    public GetAllPointStataResponseGetAllPointStataResult getAllPointStata(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声实时数据
     */
    public GetNoiseRealTimeResponseGetNoiseRealTimeResult getNoiseRealTime(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取气象实时数据
     */
    public GetWeatherRealTimeResponseGetWeatherRealTimeResult getWeatherRealTime(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取车流量实时数据
     */
    public GetTrafficRealTimeResponseGetTrafficRealTimeResult getTrafficRealTime(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声单点日报表头数据
     */
    public GetSinglePointDayReport_HeadResponseGetSinglePointDayReport_HeadResult getSinglePointDayReport_Head(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声单点日报表数据
     */
    public GetSinglePointDayReport_BodyResponseGetSinglePointDayReport_BodyResult getSinglePointDayReport_Body(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声单点月报表头数据
     */
    public GetSinglePointMonthReport_HeadResponseGetSinglePointMonthReport_HeadResult getSinglePointMonthReport_Head(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声单点月报表数据
     */
    public GetSinglePointMonthReport_BodyResponseGetSinglePointMonthReport_BodyResult getSinglePointMonthReport_Body(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声单点季报表头数据
     */
    public GetSinglePointQuarterReport_HeadResponseGetSinglePointQuarterReport_HeadResult getSinglePointQuarterReport_Head(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声单点季报表数据
     */
    public GetSinglePointQuarterReport_BodyResponseGetSinglePointQuarterReport_BodyResult getSinglePointQuarterReport_Body(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取分布统计数据
     */
    public GetDistributionStatistics_BodyResponseGetDistributionStatistics_BodyResult getDistributionStatistics_Body(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取动态分析数据
     */
    public GetDynamicAnalysis_BodyResponseGetDynamicAnalysis_BodyResult getDynamicAnalysis_Body(String strPointCode, String strDayTimeStrat, String strDayTimeEnd, String strHour, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取相关性统计数据
     */
    public GetRelativityData_BodyResponseGetRelativityData_BodyResult getRelativityData_Body(String strPointCode, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取噪声分钟采集率
     */
    public GetCollectionRate_Noise_BodyResponseGetCollectionRate_Noise_BodyResult getCollectionRate_Noise_Body(String strPointCodes, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取气象分钟采集率
     */
    public GetCollectionRate_Weather_BodyResponseGetCollectionRate_Weather_BodyResult getCollectionRate_Weather_Body(String strPointCodes, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取车流量分钟采集率
     */
    public GetCollectionRate_Traffic_BodyResponseGetCollectionRate_Traffic_BodyResult getCollectionRate_Traffic_Body(String strPointCodes, String strDayTime, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取用户所在区域的地图基础数据
     */
    public TSysAreaVo getInitMapInfo(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取用户有权查看的监测点信息
     */
    public GetInitPointInfoResponseGetInitPointInfoResult getInitPointInfo(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取用户有权查看的监测点信息,从给定值
     */
    public GetInitPointInfoAllResponseGetInitPointInfoAllResult getInitPointInfoAll(String strUserName, String strUserPwd, String strPtName, String strPtCateGory, String strPtService) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的个数
     */
    public int getPointWarningInfoNum(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的个数(新方式)
     */
    public int getPointWarningInfoNumNew(String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的指定数据个数
     */
    public int getPointWarningInfoSeachNum(String strPointCodes, String strDayTimeStrat, String strDayTimeEnd, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的指定数据个数（新方式）
     */
    public int getPointWarningInfoSeachNumNew(String strPointCodes, String strDayTimeStrat, String strDayTimeEnd, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的指定数据
     */
    public GetPointWarningInfoResponseGetPointWarningInfoResult getPointWarningInfo(int iPage, int iRows, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的指定数据（新方式）
     */
    public GetPointWarningInfoNewResponseGetPointWarningInfoNewResult getPointWarningInfoNew(int iPage, int iRows, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的指定数据
     */
    public GetPointWarningInfoSeachResponseGetPointWarningInfoSeachResult getPointWarningInfoSeach(String strPointCodes, String strDayTimeStrat, String strDayTimeEnd, int iPage, int iRows, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取告警信息表中的指定数据（新方式）
     */
    public GetPointWarningInfoSeachNewResponseGetPointWarningInfoSeachNewResult getPointWarningInfoSeachNew(String strPointCodes, String strDayTimeStrat, String strDayTimeEnd, int iPage, int iRows, String strUserName, String strUserPwd) throws java.rmi.RemoteException;

    /**
     * 获取子字典项
     */
    public TSysDictVo[] getCDicts(String strUserName, String strUserPwd, String strDictCode) throws java.rmi.RemoteException;
}
