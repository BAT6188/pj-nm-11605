package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class EnterpriseAction extends BaseAction<Enterprise, EnterpriseService> {
    @AutoService
    private EnterpriseService enterpriseService;
    @AutoService
    private AttachmentService attachmentService;
    @Override
    protected EnterpriseService getService() {
        return enterpriseService;
    }

    /**
     * 根据id获取企业信息
     */
    public void getEnterpriseInfo(){
        Enterprise enterprise = enterpriseService.findById(this.entity.getId());
        write(enterprise);
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getIsDel())) {
            param.andParam(new QueryParam("isDel", QueryOperator.EQ,entity.getIsDel()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,"%"+entity.getName()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getPollutantType())) {
            param.andParam(new QueryParam("pollutantType", QueryOperator.LIKE,"%"+entity.getPollutantType()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getOrgCode())) {
            param.andParam(new QueryParam("orgCode", QueryOperator.LIKE,"%"+entity.getOrgCode()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getSuperviseType())) {
            param.andParam(new QueryParam("superviseType", QueryOperator.EQ,entity.getSuperviseType()));
        }
        if (StringUtils.isNotBlank(entity.getArea())) {
            param.andParam(new QueryParam("area", QueryOperator.EQ,entity.getArea()));
        }
        if (StringUtils.isNotBlank(entity.getIsSpecial())) {
            param.andParam(new QueryParam("isSpecial", QueryOperator.EQ,entity.getIsSpecial()));
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
     * 企业信息伪删除
     */
    public void deleteEnterprise(){
        IPerson person = ApportalUtil.getPerson(request);
        Enterprise enterprise = enterpriseService.findById(entity.getId());
        enterprise.setIsDel("1");
        enterprise.setDelerCode(person.getUserId());
        enterprise.setDelerName(person.getUserName());
        enterprise.setDelOpinion(entity.getDelOpinion());
        enterprise.setDelTime(new Date());
        enterpriseService.update(enterprise);
        write(String.format("{\"success\": true, \"id\": \"%s\"}", entity.getId()));
    }
}