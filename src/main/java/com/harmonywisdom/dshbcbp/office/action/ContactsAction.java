package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.dshbcbp.office.service.ContactsService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ContactsAction extends BaseAction<Contacts, ContactsService> {
    @AutoService
    private ContactsService contactsService;

    @Override
    protected ContactsService getService() {
        return contactsService;
    }
}