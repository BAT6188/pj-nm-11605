package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockThird;
import com.harmonywisdom.dshbcbp.composite.service.BlockThirdService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BlockThirdAction extends BaseAction<BlockThird, BlockThirdService> {
    @AutoService
    private BlockThirdService blockThirdService;

    @Override
    protected BlockThirdService getService() {
        return blockThirdService;
    }
}