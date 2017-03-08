package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.dao.PortStatusHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("portStatusHistoryService")
public class PortStatusHistoryServiceImpl extends BaseService<PortStatusHistory, String> implements PortStatusHistoryService {
    @Autowired
    private PortStatusHistoryDAO portStatusHistoryDAO;

    @Override
    protected BaseDAO<PortStatusHistory, String> getDAO() {
        return portStatusHistoryDAO;
    }


    /**
     * 超标柱状图数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findColumnData(String name,String firstTime, String lastTime) {
        String whereSql = " AND 1=1 ";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(time,'%Y-%m')AS MONTH,\n" +
                "(SELECT COUNT(*) FROM `hw_dshbcbp_port_status_history` t0 WHERE t0.port_status='1' AND DATE_FORMAT(t0.time,'%Y-%m') = DATE_FORMAT(t.time,'%Y-%m')) AS yjf\n" +
                "FROM `hw_dshbcbp_port_status_history` t WHERE DATE_FORMAT(time,'%Y-%m-%d')> '"+firstTime+"' AND DATE_FORMAT(time,'%Y-%m-%d')<= '"+lastTime+"'" + whereSql);
        return list;
    }

    /**
     * 超标同期对比分析
     * @param name
     * @param startSdate
     * @param lastSdate
     * @return
     */
    @Override
    public List<Object[]> findColumnRatio(String name, String startSdate, String lastSdate) {
        String strsStart = startSdate.substring(5,7);
        String currentYear = startSdate.substring(0,4);
        String lastYear = String.valueOf((Integer.parseInt(currentYear) -1));
        String strEnd = lastSdate.substring(5,7);
        String whereSql = "AND 1=1 ";
        whereSql += "AND port_status='1'";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }
        whereSql += " GROUP BY DATE_FORMAT(t.`time`,'%m')";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(t.`time`,'%m')AS MONTH,\n" +
                "IFNULL((SELECT COUNT(*) AS b\n" +
                "FROM HW_DSHBCBP_PORT_STATUS_HISTORY t2 \n" +
                "WHERE DATE_FORMAT(t2.time,'%Y') = '"+lastYear+"' \n" +
                "AND DATE_FORMAT(t2.time,'%m') = DATE_FORMAT(t.`time`,'%m') \n" +
                "GROUP BY DATE_FORMAT(t.`time`,'%Y-%m')),0) AS '2015',\n" +
                "IFNULL((SELECT COUNT(*) AS b\n" +
                "FROM HW_DSHBCBP_PORT_STATUS_HISTORY t2 \n" +
                "WHERE DATE_FORMAT(t2.time,'%Y') = '"+currentYear+"'  \n" +
                "AND DATE_FORMAT(t2.time,'%m') = DATE_FORMAT(t.`time`,'%m') \n" +
                "GROUP BY DATE_FORMAT(t.`time`,'%Y-%m')),0) AS '2016'\n" +
                "FROM HW_DSHBCBP_PORT_STATUS_HISTORY t\n" +
                " WHERE DATE_FORMAT(t.`time`,'%m')>= '" + strsStart + "' AND DATE_FORMAT(t.`time`,'%m')<= '" + strEnd + "'" + whereSql);
        return list;
    }

    @Override
    public List<PortStatusHistory> findByPortids(String... portids) {
        return getDAO().find("portId in ?1", Arrays.asList(portids));
    }

    @Override
    public List<PortStatusHistory> findByEnterpriseids(String... enterpriseIds) {
        return getDAO().find("enterpriseId in ?1", Arrays.asList(enterpriseIds));
    }

    /**
     * 企业更新反馈状态
     * @param id
     */
    @Override
    public int updateStatus(String id) {
        int port = getDAO().executeJPQL("update PortStatusHistory t set t.isNoTickling='1' where t.id=? ",id);
        return port;
    }

    /**
     * 企业超标异常信息
     * @return
     */
    @Override
    public List<PortStatusHistory> companyByExcessive(String enterpriseId) {
        List<PortStatusHistory> portStatusHistories = getDAO().queryJPQL("from PortStatusHistory where enterpriseId=?1 order by time DESC,id DESC",enterpriseId);
        return portStatusHistories;
    }

    /**
     * 超标同期对比查询列表
     * @param params
     * @param paging
     * @return
     */
    @Override
    public QueryResult<PortStatusHistory> excessiveRatiolist(Map<String, String> params, Paging paging) {

        QueryResult<PortStatusHistory> result = new QueryResult<>();
        List<PortStatusHistory> rows = new ArrayList<PortStatusHistory>();

        //分页条件
        int startIndex = paging.getStartIndex();
        int endIndex = paging.getStartIndex() + paging.getPageSize();


        StringBuilder whereSql = new StringBuilder(" where 1=1 ");
        if(StringUtils.isNotBlank(params.get("strStatus"))){
            whereSql.append("and port_status = '").append(params.get("strStatus"));
        }
        if (StringUtils.isNotBlank(params.get("firstTime")) || StringUtils.isNotBlank(params.get("lastTime"))) {
            whereSql.append("' and ( t.time > '").append(params.get("firstTime")).append("' and t.time < '").append(params.get("lastTime")+"')");
        }
        if(StringUtils.isNotBlank(params.get("lastStartTime")) || StringUtils.isNotBlank(params.get("lastEndTime"))){
            whereSql.append("OR port_status = '").append(params.get("strStatus")).append("' and (t.time > '").append(params.get("lastStartTime")).append("' and t.time < '").append(params.get("lastEndTime")+"')");
        }

        String countSql = "select count(*) from HW_DSHBCBP_PORT_STATUS_HISTORY t" +whereSql.toString();
        String querySql = "select t.* from HW_DSHBCBP_PORT_STATUS_HISTORY t " +whereSql.toString()+"limit " + startIndex+","+endIndex;

        long total = portStatusHistoryDAO.getCount(countSql);

        List<PortStatusHistory> list = portStatusHistoryDAO.queryNativeSQL(querySql,PortStatusHistory.class,null);

        if(list != null && list.size()>0){
            PortStatusHistory portStatus = null;
            for(PortStatusHistory strExcessive : list){
                portStatus = new PortStatusHistory();
                portStatus.setId(String.valueOf(strExcessive.getId()));
                //企业名称enterpriseName
                portStatus.setEnterpriseName(strExcessive.getEnterpriseName());
                portStatus.setPortNumber(strExcessive.getPortNumber());
                portStatus.setPortName(strExcessive.getPortName());
                portStatus.setPollutantName(strExcessive.getPollutantName());
                portStatus.setLiveValue(strExcessive.getLiveValue());
                portStatus.setStandardValue(strExcessive.getStandardValue());
                //标题res_title
                //状态开始时间startTime
                Date date = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                portStatus.setTime(strExcessive.getTime());
                //状态portStatus
                portStatus.setPortStatus(strExcessive.getPortStatus());
                //解决方案solution
                rows.add(portStatus);
            }


        }
        result.setRows(rows);
        result.setTotal(total);
        return result;
    }


}