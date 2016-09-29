package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BlockLevelAction extends BaseAction<BlockLevel, BlockLevelService> {
    @AutoService
    private BlockLevelService blockLevelService;

    @Override
    protected BlockLevelService getService() {
        return blockLevelService;
    }
}