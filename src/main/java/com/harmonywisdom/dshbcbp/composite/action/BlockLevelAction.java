package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.ArrayList;
import java.util.List;

public class BlockLevelAction extends BaseAction<BlockLevel, BlockLevelService> {
    @AutoService
    private BlockLevelService blockLevelService;

    @Override
    protected BlockLevelService getService() {
        return blockLevelService;
    }

    /**
     * 加载网格树
     */
    public void getBlockTree(){
        List<ZNodeDTO> levels = getService().getBlockTree();
        write(levels);
    }
}


