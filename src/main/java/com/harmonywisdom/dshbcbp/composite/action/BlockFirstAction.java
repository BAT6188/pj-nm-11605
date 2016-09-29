package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockFirst;
import com.harmonywisdom.dshbcbp.composite.service.BlockFirstService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BlockFirstAction extends BaseAction<BlockFirst, BlockFirstService> {
    @AutoService
    private BlockFirstService blockFirstService;

    @Override
    protected BlockFirstService getService() {
        return blockFirstService;
    }
}