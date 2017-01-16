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
		Enterprise enterprise = enterpriseDAO.findById(enterpriseId);
		List<Punish> punishList = punishDAO.find("dispatchTaskId=?", id);
		Punish punish=new Punish();
		if (punishList.size()>0){
			punish=punishList.get(0);
		}

		String yyyyMMdd = DateUtil.dateToStr(dispatchTask.getOverTime(), "yyyyMMdd");
		String year = yyyyMMdd.substring(0, 4);
		String month = yyyyMMdd.substring(4, 6);
		String day = yyyyMMdd.substring(6);
		overManage.setYear(year);
		overManage.setMonth(month);
		overManage.setDay(day);
		overManage.setEnterpriseNamea(dispatchTask.getEnterpriseName());
		overManage.setEnterpriseNameb(dispatchTask.getEnterpriseName());
		overManage.setBlockLevelName(dispatchTask.getBlockLevelName());
		overManage.setBlockName(dispatchTask.getBlockName());
		String pollutantType = enterprise.getPollutantType();
		String pollutantTypeString="";
		String[] split = pollutantType.split(",");
		for (String s : split) {
			String dictName = DictUtil.getDictName("pollutantType", s);
			if (StringUtils.isNotEmpty(dictName)){
				pollutantTypeString+=" "+dictName;
			}
		}
		overManage.setPollutantType(pollutantTypeString);
		overManage.setArtificialPerson(enterprise.getArtificialPerson());
		overManage.setApPhone(enterprise.getApPhone());
		overManage.setEnvPrincipal(enterprise.getEnvPrincipal());
		overManage.setEpPhone(enterprise.getEpPhone());
		overManage.setContent(dispatchTask.getContent());
		overManage.setCaseReason(dispatchTask.getCaseReason());
		overManage.setOverSuggestion(dispatchTask.getOverSuggestion());
		overManage.setCaseName(punish.getCaseName());
		overManage.setFilingDate(DateUtil.dateToStr(punish.getFilingDate(),"yyyy-MM-dd HH:mm"));
		overManage.setCode(punish.getCode());
		overManage.setDecideCode(punish.getDecideCode());
		overManage.setProvision(punish.getProvision());
		overManage.setExeDate(DateUtil.dateToStr(punish.getExeDate(),"yyyy-MM-dd HH:mm"));
		String type = punish.getType();
		if ("1".equals(type)){
			overManage.setType("罚款");
		}else if ("2".equals(type)){
			overManage.setType("警告");
		}else if ("3".equals(type)){
			overManage.setType("责令停产整顿");
		}else if ("4".equals(type)){
			overManage.setType("责令停产、停业、关闭");
		}
		overManage.setMoney(punish.getMoney());
		overManage.setExeDate(DateUtil.dateToStr(punish.getExeDate(),"yyyy-MM-dd HH:mm"));
		overManage.setEndDate(DateUtil.dateToStr(punish.getEndDate(),"yyyy-MM-dd HH:mm"));
		overManage.setAttn(punish.getAttn());
		overManage.setClosedDate(DateUtil.dateToStr(punish.getClosedDate(),"yyyy-MM-dd HH:mm"));
		overManage.setPunishContent(punish.getContent());
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
