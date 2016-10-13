package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PortStatusHistoryAction extends BaseAction<PortStatusHistory, PortStatusHistoryService> {
    @AutoService
    private PortStatusHistoryService portStatusHistoryService;

    @Override
    protected PortStatusHistoryService getService() {
        return portStatusHistoryService;
    }

    /**
     * highchart获取超标数据
     */
    public void getColumnHighChart(){



    }




}