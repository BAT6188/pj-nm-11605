package com.harmonywisdom.dshbcbp.exportword.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.common.dict.util.DictUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.dao.DispatchTaskDAO;
import com.harmonywisdom.dshbcbp.dispatch.dao.FeedbackDAO;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.exelaw.bean.Punish;
import com.harmonywisdom.dshbcbp.exelaw.dao.PunishDAO;
import com.harmonywisdom.dshbcbp.exportword.bean.OverManage;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.util.string.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * pageoffice 自定义Service示例
 */
@Service("overManageService")
public class OverManageServiceImpl extends BaseService<OverManage,String> {
	
	@Autowired
    private DispatchTaskDAO dispatchTaskDAO;

	@Autowired
	private PunishDAO punishDAO;

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	private EnterpriseDAO enterpriseDAO;

	/**
	 *
	 * @param id dispatch表id
	 * @return
	 */
	@Override
	public OverManage findById(String id) {
		OverManage overManage=new OverManage();
		DispatchTask dispatchTask = dispatchTaskDAO.findById(id);
		String enterpriseId = dispatchTask.getEnterpriseId();
		Enterprise enterprise =new Enterprise();
		if (enterpriseId!=null){
			enterprise = enterpriseDAO.findById(enterpriseId);
		}
		List<Punish> punishList = punishDAO.find("dispatchTaskId=?", id);
		Punish punish=new Punish();
		if (punishList.size()>0){
			punish=punishList.get(0);
		}

		String yyyyMMdd = DateUtil.dateToStr(dispatchTask.getOverTime(), "yyyyMMdd");
		String year = yyyyMMdd.substring(0, 4);
		String month = yyyyMMdd.substring(4, 6);
		String day = yyyyMMdd.substring(6);
		overManage.setYear(StringUtils.defaultString(year));
		overManage.setMonth(StringUtils.defaultString(month));
		overManage.setDay(StringUtils.defaultString(day));
		overManage.setEnterpriseNamea(StringUtils.defaultString(dispatchTask.getEnterpriseName()));
		overManage.setEnterpriseNameb(StringUtils.defaultString(dispatchTask.getEnterpriseName()));
		overManage.setBlockLevelName(StringUtils.defaultString(dispatchTask.getBlockLevelName()));
		overManage.setBlockName(StringUtils.defaultString(dispatchTask.getBlockName()));
		String pollutantType = enterprise.getPollutantType();
		if (pollutantType==null){
			pollutantType="";
		}
		String pollutantTypeString="";
		String[] split = pollutantType.split(",");
		for (String s : split) {
			String dictName = DictUtil.getDictName("pollutantType", s);
			if (StringUtils.isNotEmpty(dictName)){
				pollutantTypeString+=" "+dictName;
			}
		}
		overManage.setPollutantType(StringUtils.defaultString(pollutantTypeString));
		overManage.setArtificialPerson(StringUtils.defaultString(enterprise.getArtificialPerson()));
		overManage.setApPhone(StringUtils.defaultString(enterprise.getApPhone()));
		overManage.setEnvPrincipal(StringUtils.defaultString(enterprise.getEnvPrincipal()));
		overManage.setEpPhone(StringUtils.defaultString(enterprise.getEpPhone()));
		overManage.setContent(StringUtils.defaultString(dispatchTask.getContent()));
		overManage.setCaseReason(StringUtils.defaultString(dispatchTask.getCaseReason()));
		overManage.setOverSuggestion(StringUtils.defaultString(dispatchTask.getOverSuggestion()));
		overManage.setCaseName(StringUtils.defaultString(punish.getCaseName()));
		overManage.setFilingDate(StringUtils.defaultString(DateUtil.dateToStr(punish.getFilingDate(),"yyyy-MM-dd HH:mm")));
		overManage.setCode(StringUtils.defaultString(punish.getCode()));
		overManage.setDecideCode(StringUtils.defaultString(punish.getDecideCode()));
		overManage.setProvision(StringUtils.defaultString(punish.getProvision()));
		overManage.setExeDesc(StringUtils.defaultString(punish.getExeDesc()));
		overManage.setExeDate(StringUtils.defaultString(DateUtil.dateToStr(punish.getExeDate(),"yyyy-MM-dd HH:mm")));
		String type = punish.getType();
		if ("1".equals(type)){
			overManage.setType("罚款");
		}else if ("2".equals(type)){
			overManage.setType("警告");
		}else if ("3".equals(type)){
			overManage.setType("责令停产整顿");
		}else if ("4".equals(type)){
			overManage.setType("责令停产、停业、关闭");
		}else {
			overManage.setType("");
		}
		if(punish.getMoney()==null){
			overManage.setMoney(0D);
		}else {
			overManage.setMoney(punish.getMoney());
		}
		overManage.setExeDate(StringUtils.defaultString(DateUtil.dateToStr(punish.getExeDate(),"yyyy-MM-dd HH:mm")));
		overManage.setEndDate(StringUtils.defaultString(DateUtil.dateToStr(punish.getEndDate(),"yyyy-MM-dd HH:mm")));
		overManage.setAttn(StringUtils.defaultString(punish.getAttn()));
		overManage.setClosedDate(StringUtils.defaultString(DateUtil.dateToStr(punish.getClosedDate(),"yyyy-MM-dd HH:mm")));
		overManage.setPunishContent(StringUtils.defaultString(punish.getContent()));
		List<Feedback> feedbackList = feedbackDAO.find("dispatchId=?", id);
		if (feedbackList.size()>0){
			overManage.setFeedbackListObject(feedbackList);
			overManage.setFeedbackList(JSONArray.toJSONString(feedbackList));
		}
		return overManage;
	}

	@Override
	protected BaseDAO<OverManage, String> getDAO() {
		return null;
	}
}
