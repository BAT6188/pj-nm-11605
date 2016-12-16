package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.core.user.IUserProfile;
import com.harmonywisdom.core.user.impl.UserProfile;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.IBaseService;

/**
 * Created by Administrator on 2016/12/16.
 */
public class UserAction extends BaseAction {
    @Override
    protected IBaseService getService() {
        return null;
    }

    public void govUpdatePwd(){
        UserProfile userProfile = (UserProfile) request.getSession().getAttribute(IUserProfile.J2EE_USER_NAME);
        String newPwd = request.getParameter("newPassword");
        String oldPwd = request.getParameter("oldPassword");
        boolean flag = PersonServiceUtil.validateUserPassword(userProfile.getUserID(), oldPwd);
        if(flag){
            PersonServiceUtil.updateUserPassword(userProfile.getUserID(), newPwd);
            write(1);
        }else{
            write(0);
        }
    }
}
