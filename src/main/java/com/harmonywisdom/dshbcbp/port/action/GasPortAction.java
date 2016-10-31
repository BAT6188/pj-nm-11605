package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.service.GasPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class GasPortAction extends BaseAction<GasPort, GasPortService> {
    @AutoService
    private GasPortService gasPortService;

    @Override
    protected GasPortService getService() {
        return gasPortService;
    }

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            param.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getNumber())) {
            param.andParam(new QueryParam("number", QueryOperator.EQ,entity.getNumber()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,"%"+entity.getName()+"%"));
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

    /**
     * 根据ID获取排口信息
     */
    public void getEntityById(){
        write(gasPortService.findById(entity.getId()));
    }
}