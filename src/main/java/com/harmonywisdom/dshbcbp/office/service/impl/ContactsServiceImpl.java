package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.dshbcbp.office.dao.ContactsDAO;
import com.harmonywisdom.dshbcbp.office.service.ContactsService;
import com.harmonywisdom.dshbcbp.utils.EntityUtil;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("contactsService")
public class ContactsServiceImpl extends BaseService<Contacts, String> implements ContactsService {
    @Autowired
    private ContactsDAO contactsDAO;

    @Override
    protected BaseDAO<Contacts, String> getDAO() {
        return contactsDAO;
    }

    @Override
    public String removeContactFromBlock(String[] ids) {
        String returnString = "";
        if(ids.length>0){
            for(String id:ids){
                returnString +=contactsDAO.executeJPQL("update Contacts set blockLevelId=null,blockId=null where id=?",id);
            }
        }
        return returnString;
    }

    @Override
    public String updateContact(Contacts contacts) {
        Map<String,Object> map = EntityUtil.getUpdateMap(contacts);
        return String.valueOf(contactsDAO.executeJPQL(String.valueOf(map.get("upStr")),(Map<String, Object>)map.get("valMap")));
    }

    @Override
    public String addContactsToBlock(Contacts contacts) {
        String[] ids = contacts.getIds();
        if(ids.length>0){
            contacts.setIds(null);
            for(String id:ids){
                contacts.setId(id);
                Map<String,Object> map = EntityUtil.getUpdateMap(contacts);
                contactsDAO.executeJPQL(String.valueOf(map.get("upStr")),(Map<String, Object>)map.get("valMap"));
            }
        }
        return contacts.getId();
    }
}