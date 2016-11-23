package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.dao.PortStatusHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%Y-%m')AS MONTH,\n" +
                "(SELECT COUNT(*) FROM `hw_dshbcbp_port_status_history` t0 WHERE t0.port_status='1' AND DATE_FORMAT(t0.start_time,'%m') = DATE_FORMAT(t.start_time,'%m')) AS yjf\n" +
                "FROM `hw_dshbcbp_port_status_history` t WHERE DATE_FORMAT(start_time,'%Y-%m-%d')> '"+firstTime+"' AND DATE_FORMAT(start_time,'%Y-%m-%d')<= '"+lastTime+"'" + whereSql);
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
        whereSql += " GROUP BY DATE_FORMAT(t.`start_time`,'%m')";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(t.`start_time`,'%m')AS MONTH,\n" +
                "IFNULL((SELECT COUNT(*) AS b\n" +
                "FROM HW_DSHBCBP_PORT_STATUS_HISTORY t2 \n" +
                "WHERE DATE_FORMAT(t2.start_time,'%Y') = '"+lastYear+"' \n" +
                "AND DATE_FORMAT(t2.start_time,'%m') = DATE_FORMAT(t.`start_time`,'%m') \n" +
                "GROUP BY DATE_FORMAT(t.`start_time`,'%Y-%m')),0) AS '2015',\n" +
                "IFNULL((SELECT COUNT(*) AS b\n" +
                "FROM HW_DSHBCBP_PORT_STATUS_HISTORY t2 \n" +
                "WHERE DATE_FORMAT(t2.start_time,'%Y') = '"+currentYear+"'  \n" +
                "AND DATE_FORMAT(t2.start_time,'%m') = DATE_FORMAT(t.`start_time`,'%m') \n" +
                "GROUP BY DATE_FORMAT(t.`start_time`,'%Y-%m')),0) AS '2016'\n" +
                "FROM HW_DSHBCBP_PORT_STATUS_HISTORY t\n" +
                " WHERE DATE_FORMAT(t.`start_time`,'%m')>= '" + strsStart + "' AND DATE_FORMAT(t.`start_time`,'%m')<= '" + strEnd + "'" + whereSql);
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
    public List<PortStatusHistory> companyByExcessive() {
        List<PortStatusHistory> portStatusHistories = getDAO().queryJPQL("from PortStatusHistory where isNoTickling='2' order by startTime DESC,id DESC");
        return portStatusHistories;
    }


}