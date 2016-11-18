package com.harmonywisdom.dshbcbp.utils;

import com.harmonywisdom.apportal.sdk.dictdata.DictDataServiceUtil;
import com.harmonywisdom.apportal.sdk.dictdata.domain.DictData;
import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.apportal.sdk.right.RightServiceUtil;
import com.harmonywisdom.apportal.sdk.right.domain.Right;
import com.harmonywisdom.apportal.sdk.role.IRole;
import com.harmonywisdom.apportal.sdk.role.RoleServiceUtil;
import com.harmonywisdom.core.user.impl.UserProfile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public class ApportalUtil {
	
	/**
	 * 取得apportal的用户信息
	 * @param request
	 * @return
	 * @
	 */
	public static UserProfile getUserProfile(HttpServletRequest request){
		UserProfile userProfile =  (UserProfile) request.getSession().getAttribute(UserProfile.J2EE_USER_NAME);
		if(null != userProfile){
			return userProfile;
		} else {
			return null;
		}
		
	}
	
	/**
	 * 根据当前用户的系统ID取得所有用户信息
	 * @param request
	 * @return List<IPerson> 所有用户信息集合
	 */
	@SuppressWarnings("unchecked")
	public static List<IPerson> getPersonsBySysId(HttpServletRequest request) {
		List<IPerson> list  = PersonServiceUtil.getAllPersonBySysId(getUserProfile(request).getSysId());
		
		return list;
	}
	/**
	 * 根据当前用户ID取得用户角色信息
	 * @param request
	 * @return List<IRole> 所有用户角色信息集合
	 */
	@SuppressWarnings("unchecked")
	public static List<IRole> getRoleByPersonId(HttpServletRequest request) {
		UserProfile userProfile = getUserProfile(request);
		List<IRole> list= RoleServiceUtil.getRoleByPersonId(userProfile.getPersonId());
		
		return list;
	}
	
	/**
	 * 从门户中取得当前登录用户人员信息
	 * @param request
	 * @return IPerson 用户信息
	 */
	@SuppressWarnings("unchecked")
	public static IPerson getPerson(HttpServletRequest request) {
		UserProfile userProfile = getUserProfile(request);
		if (userProfile != null) {
			IPerson person  = PersonServiceUtil.getPerson(userProfile.getPersonId());
			return person;
		}
		return null;
	}

	/**
	 * 获取当前用户的组织信息
	 * @param request
	 * @return
     */
	public static IOrg getIOrgOfCurrentUser(HttpServletRequest request){
		IPerson person = ApportalUtil.getPerson(request);
		String orgId=person.getOrgId();
		if(orgId!=null){
			IOrg org = OrgServiceUtil.getOrgByOrgId(orgId);
			return org;
		}else{
			return null;
		}
	}
	
	/**
	 * 批量取得字典项集合数据,Map格式
	 * @param dictCode String[] 字典Code数组
	 * @return List<IPerson>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,List<DictData>> getDictDataMap(String[] dictCode) {
		Map<String,List<DictData>> map  = DictDataServiceUtil.getDictDatas(dictCode);
		return map;
	}

	/**
	 * 通过ServletContext取得字典项集合数据
	 * @param dictCode String 字典Code
	 * @return List<IPerson>
	 */
	@SuppressWarnings("unchecked")
	public static List<DictData>  getDictData(String dictCode,ServletContext context) {
		
		Map<String, List<DictData>> dictDataMap = (Map<String, List<DictData>>)context.getAttribute("dictDataMap");
		List dictDataList = dictDataMap.get(dictCode);
		return dictDataList;
	}	
	
	/**
	 * 取得字典项数据
	 * @param dictCode String 字典大类别Code
	 * @param dictDataCode String 字典子类Code
	 * @return List<IPerson>
	 */
	@SuppressWarnings("unchecked")
	public static DictData getDictDataByCode(String dictCode,String dictDataCode) {
		DictData dict  = DictDataServiceUtil.getDictDataByCode(dictCode, dictDataCode);
		return dict;
	}

	
	/**
	 * 取得字典项数据
	 * @param dictCode String 字典大类别Code
	 * @param dictDataCode String 字典子类Code
	 * @return List<IPerson>
	 */
	@SuppressWarnings("unchecked")
	public static DictData getDictDataByCode(String dictCode,String dictDataCode,ServletContext context) {
		List<DictData> list = getDictData(dictCode,context);
		for(DictData dict : list) {
			if(dict.getDictdataCode().equals(dictDataCode)) {
				return dict;
			}
		}
		return null;
	}
	

	
	/**
	 * 取得菜单数据信息
	 * @param request
	 * @return List<IPerson>
	 */
	@SuppressWarnings("unchecked")
	public static List<Right> getMenuData(HttpServletRequest request) {
		List<Right> list  = RightServiceUtil.getNormalRightByPersonId(getUserProfile(request).getPersonId());
		return list;
	}
	

}
