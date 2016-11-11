package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.framework.service.IBaseService;

public interface ContactsService extends IBaseService<Contacts, String> {

    String removeContactFromBlock(String[] ids);

    /**
     * 增量更新
     * @param contacts
     * @return
     */
    String updateContact(Contacts contacts);

    /**
     * 联系人添加网格
     * @param contacts
     * @return
     */
    String addContactsToBlock(Contacts contacts);
}