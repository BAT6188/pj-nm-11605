package com.harmonywisdom.dshbcbp.common.dict.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.common.dict.util.DictUtil;
import com.harmonywisdom.framework.action.ActionHelper;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

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
    
    public void getOrgList(){
    	List<IOrg> orgs = OrgServiceUtil.getAllNotDelOrg();
    	write(JSON.toJSONString(orgs));
    }

    @Override
    public void prepare() throws Exception {
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
    }
}
