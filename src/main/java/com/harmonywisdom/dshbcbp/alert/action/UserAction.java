package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.apportal.sdk.right.RightServiceUtil;
import com.harmonywisdom.apportal.sdk.right.domain.Right;
import com.harmonywisdom.core.user.IUserProfile;
import com.harmonywisdom.core.user.impl.UserProfile;
import com.harmonywisdom.dshbcbp.alert.bean.User;
import com.harmonywisdom.framework.action.ActionHelper;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */
public class UserAction extends ActionHelper implements Preparable {
    /**
     * 手机端发布信息之前验证登陆信息
     */
    public void loginValidate(){
        String userId = request.getParameter("userId");
        String pwd = request.getParameter("password");
        if (PersonServiceUtil.validateUserPassword(userId, pwd)){
            IPerson person = PersonServiceUtil.getPersonByUserId(userId);
            IOrg org = OrgServiceUtil.getOrgByPersonId(person.getPersonId());
//            Map<String, Object> ret = new HashMap<String, Object>();
//            ret.put("person", person);
//            ret.put("org", org);
            User user = new User();
            user.setUserId(person.getUserId());
            user.setUserName(person.getUserName());
            user.setUserType(person.getUserType());
            user.setUserStat(person.getUserStat());
            user.setPersonBaseId(person.getPersonBaseId());
            user.setPersonId(person.getPersonId());
            user.setOrgId(org.getOrgId());
            user.setOrgName(org.getOrgName());
            user.setMobile(person.getMobile());
            user.setMenuData(getUserMenu(person.getPersonId()));
            write(user);
        }else{
            write(false);
        }
    }

    public List<Right> getUserMenu(String personId){
        List<Right> list = RightServiceUtil.getNormalRightByPersonId(personId);
        List<Right> appMenus = new ArrayList<>();
        if(list.size()>0){
            for (Right r:list){
                if(r.getResCode().startsWith("AM")){
                    appMenus.add(r);
                };
            }
        }
        return appMenus;
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

    @Override
    public void prepare() throws Exception {
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
    }
}
