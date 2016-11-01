package com.harmonywisdom.dshbcbp.common.dict.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.common.dict.bean.OrgPerson;
import com.harmonywisdom.dshbcbp.common.dict.util.DictUtil;
import com.harmonywisdom.framework.action.ActionHelper;
import com.opensymphony.xwork2.Preparable;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hotleave on 15-3-9.
 */
public class DictAction extends ActionHelper implements Preparable {
    /**
     * 输出数据字典项
     * <p>
     * 输出的字典项格式为：<code>{name: 'foo', code: 'bar'}</code>
     * </p>
     * <ul>
     * <li>提供单一code时，直接返回这个code对应的字典项列表<code>list&lt;dict&gt;</code></li>
     * <li>提供多个code时，返回<code>Map&lt;code, list&gt;</code></li>
     * </ul>
     */
    public void list() {
        String[] codes = getParamValues("code");
        if (codes == null) {
            write("[]");
            return;
        }

        if (codes.length == 1) {
            write(DictUtil.getDictList(codes[0]));
        } else {
            Map<String, List<DictBean>> map = new HashMap<String, List<DictBean>>(codes.length);
            for (String code : codes) {
                map.put(code, DictUtil.getDictList(code));
            }

            write(map);
        }
    }

    public void multipleList() {
        String[] codes = getParamValues("code");
        String parentCode = getParamValue("parentCode");
        if (codes == null) {
            write("[]");
            return;
        }

        if (codes.length == 1) {
            write(DictUtil.getDictList(codes[0],parentCode));
        } else {
            Map<String, List<DictBean>> map = new HashMap<String, List<DictBean>>(codes.length);
            for (String code : codes) {
                map.put(code, DictUtil.getDictList(code,parentCode));
            }

            write(map);
        }
    }

    public void getOrgList(){
    	List<IOrg> orgs = OrgServiceUtil.getAllNotDelOrg();
    	write(JSON.toJSONString(orgs));
    }

    @Override
    public void prepare() throws Exception {
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
    }

    public void getOrgPersonList(){
        String orgCode = request.getParameter("orgCode");
        List<OrgPerson> orgPersonList = findOrgPersonByOrgCode(orgCode,"-1");
        write(orgPersonList);
    }

    public List<OrgPerson> findOrgPersonByOrgCode(String orgCode,String orgParentId){
        List<OrgPerson> orgPersonList = new ArrayList<>();

        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        OrgPerson org = new OrgPerson();
        org.setId(iOrg.getOrgId());
        org.setParentId(orgParentId);
        org.setName(iOrg.getOrgName());
        orgPersonList.add(org);
        List<IPerson> personList = PersonServiceUtil.getPersonByOrgId(iOrg.getOrgId());
        if(personList.size()>0){
            for (IPerson iPerson:personList){
                OrgPerson orgPerson = new OrgPerson();
                orgPerson.setId(iPerson.getPersonId());
                orgPerson.setName(iPerson.getUserName());
                String job = (String) iPerson.getExtattrMap().get("job");
                if(StringUtils.isNotBlank(job)){
                    orgPerson.setJob(job);
                }
                orgPerson.setParentId(iOrg.getOrgId());
                orgPersonList.add(orgPerson);
            }
        }else{
            OrgPerson noOrg = new OrgPerson();
            noOrg.setId("false");
            noOrg.setParentId(iOrg.getOrgId());
            noOrg.setName("没有查询到相关人员");
            orgPersonList.add(noOrg);
        }

        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(orgs.size()>0){
            for(IOrg iOrgChild:orgs){
                List<OrgPerson> orgPersons = findOrgPersonByOrgCode(iOrgChild.getOrgCode(),iOrg.getOrgId());
                if(orgPersons.size()>0){
                    for(OrgPerson op:orgPersons){
                        orgPersonList.add(op);
                    }
                }else{
                    OrgPerson noOrg = new OrgPerson();
                    noOrg.setId("false");
                    noOrg.setParentId(iOrgChild.getOrgId());
                    noOrg.setName("没有查询到相关人员");
                    orgPersonList.add(noOrg);
                }
            }
        }
        return orgPersonList;
    }
}
