package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.core.user.IUserProfile;
import com.harmonywisdom.core.user.impl.UserProfile;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/12/16.
 */
public class UserAction extends BaseAction {
    @Override
    protected IBaseService getService() {
        return null;
    }

    /**
     * 手机端发布信息之前验证登陆信息
     */
    public void loginValidate(){
        String userId = request.getParameter("userId");
        String pwd = request.getParameter("password");
        if (PersonServiceUtil.validateUserPassword(userId, pwd)){
            IPerson person = PersonServiceUtil.getPersonByUserId(userId);
            IOrg org = OrgServiceUtil.getOrgByPersonId(person.getPersonId());
            Map<String, Object> ret = new HashMap<String, Object>();
            ret.put("person", person);
            ret.put("org", org);
            write(ret);
        }else{
            write(false);
        }

    }

    /**
     * 更改登录用户密码
     */
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
