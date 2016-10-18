package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
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
     * 加载资料类型树的子节点
     */
    public void getBlockLevelTree(){
        String id = request.getParameter("id");
        List<BlockLevel> nodes = new ArrayList<BlockLevel>();
        //默认加载根节点
        if(id == null ||"".equals(id)){
            BlockLevel level = new BlockLevel();
            nodes.add(level);
        }else{//否则加载节点下的数据
            List<BlockLevel> list = blockLevelService.findByParentId(id);
            for(BlockLevel level: list) {

                nodes.add(level);
            }
        }
        write(nodes);
    }
}


