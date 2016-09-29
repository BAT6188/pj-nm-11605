package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockSecond;
import com.harmonywisdom.dshbcbp.composite.service.BlockSecondService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BlockSecondAction extends BaseAction<BlockSecond, BlockSecondService> {
    @AutoService
    private BlockSecondService blockSecondService;

    @Override
    protected BlockSecondService getService() {
        return blockSecondService;
    }
}