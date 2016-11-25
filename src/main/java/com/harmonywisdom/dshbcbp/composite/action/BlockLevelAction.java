package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class BlockLevelAction extends BaseAction<BlockLevel, BlockLevelService> {
    @AutoService
    private BlockLevelService blockLevelService;

    @Override
    protected BlockLevelService getService() {
        return blockLevelService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getName())) {
            params.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        return condition;
    }

    /**
     * 加载网格树
     */
    public void getBlockTree(){
        List<ZNodeDTO> levels = getService().getBlockTree();
        write(levels);
    }
    /**
     * 加载四级网格数
     */
    public void getBlock(){
        String id=request.getParameter("id");
        List<ZNodeDTO> block = getService().getBlock(id);
        write(block);
    }

    /**
     * 获取一张图页面tree
     */
    public void getOneImageTree(){
        String searText = request.getParameter("searchText");
        List<ZNodeDTO> znodes = getService().getOneImageTree(searText);
        write(znodes);


    }

    public void getAllBlocksZtree(){
        write(blockLevelService.getAllBlockZtree());
    }

    public void getAllBlockLevelAndBlock(){
        write(blockLevelService.getAllBlockLevelAndChild());
    }
}