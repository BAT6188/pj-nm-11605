package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.dshbcbp.office.service.ContactsService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

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

        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.EQ,entity.getType()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }
        if (StringUtils.isNotBlank(entity.getPosition())) {
            param.andParam(new QueryParam("position", QueryOperator.LIKE,entity.getPosition()));
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
        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("sort", Direction.DESC);
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