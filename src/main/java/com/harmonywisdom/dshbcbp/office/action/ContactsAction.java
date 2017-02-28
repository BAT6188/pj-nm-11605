package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.dshbcbp.office.service.ContactsService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;

public class ContactsAction extends BaseAction<Contacts, ContactsService> {
    @AutoService
    private ContactsService contactsService;
    @AutoService
    private AttachmentService attachmentService;
    @Override
    protected ContactsService getService() {
        return contactsService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();

        String mobileOperType = request.getParameter("mobileOperType");

        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.EQ,entity.getType()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }
        if (StringUtils.isNotBlank(entity.getPosition())) {
            param.andParam(new QueryParam("position", QueryOperator.EQ,entity.getPosition()));
        }
        if (StringUtils.isNotBlank(entity.getDepartment())) {
            param.andParam(new QueryParam("department", QueryOperator.LIKE,entity.getDepartment()));
        }
        if (StringUtils.isNotBlank(entity.getOrgId())) {
            param.andParam(new QueryParam("orgId", QueryOperator.EQ,entity.getOrgId()));
        }
        if (StringUtils.isNotBlank(entity.getBlockLevelId())) {
            param.andParam(new QueryParam("blockLevelId", QueryOperator.EQ,entity.getBlockLevelId()));
        }
        if (StringUtils.isNotBlank(entity.getBlockId())) {
            param.andParam(new QueryParam("blockId", QueryOperator.EQ,entity.getBlockId()));
        }
        String blockNeed = getParamValue("blockNeed");
        if (StringUtils.isNotBlank(blockNeed)) {
            param.andParam(new QueryParam("blockLevelId", QueryOperator.IS,""));
            param.andParam(new QueryParam("blockId", QueryOperator.IS,""));
        }
        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }

        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("sort", Direction.DESC);
        LinkedHashMap<String, Direction> orders = new LinkedHashMap<>();
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
            //orders.put("mobileTimestamp",Direction.DESC);
        }
        return condition;
    }

    @Override
    public void list() {
        super.list();
    }

    public void listForMobile() {
        QueryResult<Contacts> result = this.query();
        List<Contacts> rows = result.getRows();
        if (rows != null && rows.size() > 0) {
            for (Contacts c : rows) {
                c.setHeadImage(null);
            }
        }
        write(result);
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

    /**
     * 从网格中移除人员
     */
    public void removeContactFromBlock(){
        String[] deleteId = request.getParameterValues("deletedId");
        write(String.format("{\"success\": true, \"id\": \"%s\"}", contactsService.removeContactFromBlock(deleteId)));
    }

    /**
     * 增量更新Contact
     */
    public void updateContact(){
        Contacts contacts = new Contacts();
        contacts.setApportalUserId(this.entity.getApportalUserId());
        List<Contacts> contactsList = contactsService.findBySample(contacts);
        if(contactsList.size()>0){
            write(String.format("{\"success\": false, \"name\": \"%s\"}", contactsList.get(0).getName()));
        }else{
            write(String.format("{\"success\": true, \"id\": \"%s\"}", contactsService.updateContact(this.entity)));
        }
    }

    /**
     * 将联系人批量添加网格
     */
    public void addContactsToBlock(){
        write(String.format("{\"success\": true, \"id\": \"%s\"}", contactsService.addContactsToBlock(this.entity)));
    }
}