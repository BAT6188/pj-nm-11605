package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PortStatusHistoryDAO extends DefaultDAO<PortStatusHistory, String> {
    /**
     * 总数
     * @param countSql
     * @return
     */
    public long getCount(String countSql) {
        long count = 0;
        List<Object> list = this.queryNativeSQL(countSql);
        if (list != null && list.size() > 0) {
            count = Long.parseLong(String.valueOf(list.get(0)));
        }
        return count;
    }
}