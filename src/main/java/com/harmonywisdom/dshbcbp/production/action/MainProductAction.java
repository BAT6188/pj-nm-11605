package com.harmonywisdom.dshbcbp.production.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.production.bean.MainProduct;
import com.harmonywisdom.dshbcbp.production.service.MainProductService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class MainProductAction extends BaseAction<MainProduct, MainProductService> {
    @AutoService
    private MainProductService mainProductService;

    @Override
    protected MainProductService getService() {
        return mainProductService;
    }

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            param.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getProduct())) {
            param.andParam(new QueryParam("product", QueryOperator.LIKE,"%"+entity.getProduct()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getRawMaterial())) {
            param.andParam(new QueryParam("rawMaterial", QueryOperator.LIKE,"%"+entity.getRawMaterial()+"%"));
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("createTime", Direction.DESC);
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
        if(entity.getCreateTime()==null){
            entity.setCreateTime(new Date());
        }

        super.save();

        if(StringUtils.isNotBlank(entity.getAttachmentId())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentId().split(","));

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
}