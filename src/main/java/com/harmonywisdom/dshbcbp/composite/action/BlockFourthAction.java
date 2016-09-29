package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockFourth;
import com.harmonywisdom.dshbcbp.composite.service.BlockFourthService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BlockFourthAction extends BaseAction<BlockFourth, BlockFourthService> {
    @AutoService
    private BlockFourthService blockFourthService;

    @Override
    protected BlockFourthService getService() {
        return blockFourthService;
    }
}