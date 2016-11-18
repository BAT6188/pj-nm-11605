package com.harmonywisdom.dshbcbp.exportword.service.impl;

import com.harmonywisdom.dshbcbp.exportword.bean.DutyItemCreate;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.stereotype.Service;


/**
 * pageoffice 自定义Service示例
 */
@Service("dutyItemCreateService")
public class DutyItemCreateServiceImpl extends BaseService<DutyItemCreate,String> {
	
	/*@Autowired
    private DutyItemDAO dutyItemDAO;*/

	
	
	/**
	 * 根据businessId查询所有数据
	 */
	@Override
	public DutyItemCreate findById(String businessId){
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
		return null;
	}



	@Override
	protected BaseDAO<DutyItemCreate, String> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
