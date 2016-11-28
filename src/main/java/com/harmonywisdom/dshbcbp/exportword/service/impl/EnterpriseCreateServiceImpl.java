package com.harmonywisdom.dshbcbp.exportword.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.util.DictUtil;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("enterpriseCreateService")
public class EnterpriseCreateServiceImpl extends BaseService<Enterprise,String> {

	/*@Autowired
    private DutyItemDAO dutyItemDAO;*/

	@Autowired
	private EnterpriseDAO enterpriseDAO;

	@Autowired
	private BlockService blockService;



	/**
	 * 根据businessId查询所有数据
	 */
	@Override
	public Enterprise findById(String businessId){

		Enterprise entity =  enterpriseDAO.findById(businessId);
		if(entity.getBlockId() != null && !"".equals(entity.getBlockId())){
//			Block block = blockDAO.findById(entity.getBlockId());
			String value = "44acbe64447349719b80f838af701b1e";
			Block block = blockService.findById(entity.getBlockId());
			if(block !=null && !"".equals(block)){
				entity.setBlockLevelName(block.getBlockLevelName());
				entity.setBlockName(block.getPosition()+"("+block.getBlockLeader()+")");
			}

		}
		if("1".equals(entity.getStatus())){
			entity.setStatus("运行中");
		}else{
			entity.setStatus("已停产");

		}
		if("0".equals(entity.getPollutantStatus())){
			entity.setPollutantStatus("正常");
		}else if("1".equals(entity.getPollutantStatus())){
			entity.setPollutantStatus("超标");
		}else{
			entity.setPollutantStatus("异常");
		}
		if(entity.getPollutantCode() != null&& !"".equals(entity.getPollutantCode())){
			String pollutantCodes = DictUtil.getDictName("pollutantCode", entity.getPollutantCode());
			if (pollutantCodes != null) {
				entity.setPollutantCode(pollutantCodes);
			}else{
					//输出原值
			}
		}

		String orgCodes = DictUtil.getDictName("orgType",entity.getOrgCode());
		entity.setOrgCode(orgCodes);
		String pollutantTypes = DictUtil.getDictName("pollutantType",entity.getPollutantType());
		entity.setPollutantType(pollutantTypes);
		String pollutantLevels = DictUtil.getDictName("pollutantLevel",entity.getPollutantLevel());
		entity.setPollutantLevel(pollutantLevels);
		String superviseType = entity.getSuperviseType();
		if("0".equals(superviseType)){
			entity.setSuperviseType("重点排污单位");
		}else{
			entity.setSuperviseType("一般排污单位");
		}
		String isSpecial = entity.getIsSpecial();
		if("0".equals(isSpecial)){
			entity.setIsSpecial("否");
		}else{
			entity.setIsSpecial("是");
			}
		String registTypes = DictUtil.getDictName("registType",entity.getRegistType());
		entity.setRegistType(registTypes);
		entity.setRegistTime(entity.getRegistTime());
		String affiliations = DictUtil.getDictName("affiliation",entity.getAffiliation());
		entity.setAffiliation(affiliations);
		String scales= DictUtil.getDictName("scale",entity.getScale());
		entity.setScale(scales);
		String industryTypes = DictUtil.getDictName("industryType",entity.getIndustryType());
		String industrialParks = DictUtil.getDictName("industrialPark",entity.getIndustrialPark());
		entity.setIndustrialPark(industrialParks);
		String valleys = DictUtil.getDictName("valley",entity.getValley());
		entity.setValley(valleys);

		return entity;
		/*DutyItemCreate entity=new DutyItemCreate();
		String sql="select * from HW_EMSYNTHESIS_DUTY_ITEM d where d.duty_Id='"+businessId+"'";
		List<DutyItem>list=dutyItemDAO.getListBySQL(sql);
		Duty duty=new Duty();
		duty=dutyDAO.findById(businessId);
		SimpleDateFormat sdf = new SimpleDateFormat("E");

		if(duty!=null){
			entity.setRemark(duty.getRemark());
			entity.setId(businessId);
			entity.setDayStartTime(duty.getDayStartTime());
			entity.setDayEndTime(duty.getDayEndTime());
			entity.setNightStartTime(duty.getNightEndTime());
			entity.setNightEndTime(duty.getNightEndTime());
		}
		if(list!=null&&list.size()>0){
			String dutyItemInfos="[";
			for(DutyItem dutyItem:list){
				if(dutyItem!=null){
					String dutyItemInfo= JSONObject.toJSONString(dutyItem);
					dutyItemInfo=dutyItemInfo.substring(0, dutyItemInfo.length()-1);
					String week=",\"week\":\""+sdf.format(dutyItem.getDutyDate()).substring(2, 3)+"\"},";
					dutyItemInfos+=dutyItemInfo+week;
				}
			}
			if(!"[".equals(dutyItemInfos)){
				dutyItemInfos=dutyItemInfos.substring(0,dutyItemInfos.length()-1);
			}
			dutyItemInfos+="]";
			entity.setDutyItemInfo(dutyItemInfos);
		}
		return entity;*/
	}



	@Override
	protected BaseDAO<Enterprise, String> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
