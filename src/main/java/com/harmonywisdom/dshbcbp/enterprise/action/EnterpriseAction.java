package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        /*----排污档案列表查询条件---*/
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,"%"+entity.getName()+"%"));
        }
        if(StringUtils.isNotBlank(entity.getPollutantStatus())){
            param.andParam(new QueryParam("pollutantStatus", QueryOperator.EQ,entity.getPollutantStatus()));
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
        /*---删除排污档案查询条件---*/
        if (StringUtils.isNotBlank(entity.getDelOpinion())) {
            param.andParam(new QueryParam("delOpinion", QueryOperator.LIKE,"%"+entity.getDelOpinion()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getDelerName())) {
            param.andParam(new QueryParam("delerName", QueryOperator.LIKE,"%"+entity.getDelerName()+"%"));
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if(StringUtils.isNotBlank(startTime)){
                Date starttime = sdf.parse(startTime+" 00:00:00");
                param.andParam(new QueryParam("delTime", QueryOperator.GE,starttime));
            }
            if(StringUtils.isNotBlank(endTime)){
                Date endtime = sdf.parse(endTime+" 23:59:59");
                param.andParam(new QueryParam("delTime", QueryOperator.LE,endtime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
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
        if(StringUtils.isNotBlank(entity.getPlaneMap())){
            attachmentService.updateBusinessId(entity.getPlaneMap(),entity.getPlaneMap());
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

    public void findByIds(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<Enterprise> list = getService().findByIds(ids);
            write(list);
        }
    }

    /**
     * 获取排口树结构
     */
    public void getEnterprisePortZtree(){
        String code = request.getParameter("code");
        if(StringUtils.isNotBlank(code)){
            write(enterpriseService.getEnterprisePortZtree(code));
        }else{
            DictBean bean = new DictBean();
            bean.setCode(code);
            bean.setName("没有查询到数据!");
            bean.setParentCode("-1");
            bean.setSerial(0);
            write(bean);
        }

    }

    /**
     * 查询企业报警状态
     */
    public void queryEnterpriseAlertStatus(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<Map<String,String>> list = getService().queryEnterpriseAlertStatus(ids);
            write(list);
        }
    }
}