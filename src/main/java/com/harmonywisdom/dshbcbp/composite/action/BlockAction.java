package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class BlockAction extends BaseAction<Block, BlockService> {
    @AutoService
    private BlockService blockService;

    @Override
    protected BlockService getService() {
        return blockService;
    }
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getOrgName())) {
            params.andParam(new QueryParam("orgName", QueryOperator.LIKE,entity.getOrgName()));
        }
        if (StringUtils.isNotBlank(entity.getBlockLevelId())) {
            params.andParam(new QueryParam("blockLevelId", QueryOperator.LIKE,entity.getBlockLevelId()));
        }
        if (StringUtils.isNotBlank(entity.getBlockLevelId())) {
            params.andParam(new QueryParam("blockLevelId", QueryOperator.EQ,entity.getBlockLevelId()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        return condition;
    }
    @Override
    public void save() {
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        super.save();
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
    }

    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }

    public void findByIds(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<Block> list = getService().findByIds(ids);
            write(list);
        }
    }

    public void findLevelById(){
        String id=request.getParameter("blockLevelId");
        List<Block> list=getService().findByLevelId(id);
        write(list);
    }

    public void findChildrenBlock(){
        String parentBlockId = request.getParameter("parentBlockId");
        if (StringUtils.isNotBlank(parentBlockId)) {
            List<Block> blocks = getService().queryJPQL("from Block where parentBlockId=?",Block.class, parentBlockId);
            write(blocks);
        } else {
            write(false);
        }
    }
}