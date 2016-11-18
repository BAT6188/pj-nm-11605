package com.harmonywisdom.dshbcbp.exportword.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * pageoffice 自定义Service示例
 */
public class DutyItemCreate implements Serializable{
	
	private String id;
	
	private String dayStartTime;
	
	private String dayEndTime;
	
	private String nightStartTime;
	
	private String nightEndTime;
	
	
	/**
     * 日常值班信息
     */
    private String dutyItemInfo;
    
	/**
	 * 备注
	 */
	private String remark;
	
	

	public String getId() {
		return id;
		
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdForTemp(){
		Map m = new HashMap();
		m.put("01","一");
		m.put("02","二");
		m.put("03","三");
		m.put("04","四");
		m.put("05","五");
		m.put("06","六");
		m.put("07","七");
		m.put("08","八");
		m.put("09","九");
		m.put("10","十");
		m.put("11","十一");
		m.put("12","十二");
		if(id!=null){
			String[] strArray = null;   
	        strArray = id.split("-");
	        String month=strArray[1];
	        return (String) m.get(month);
		}
		return "";
	}
	
	public String getDayStartTime() {
		return dayStartTime;
	}

	public void setDayStartTime(String dayStartTime) {
		this.dayStartTime = dayStartTime;
	}

	public String getDayEndTime() {
		return dayEndTime;
	}

	public void setDayEndTime(String dayEndTime) {
		this.dayEndTime = dayEndTime;
	}
	
	public String getNightStartTime() {
		return nightStartTime;
	}

	public void setNightStartTime(String nightStartTime) {
		this.nightStartTime = nightStartTime;
	}

	public String getNightEndTime() {
		return nightEndTime;
	}

	public void setNightEndTime(String nightEndTime) {
		this.nightEndTime = nightEndTime;
	}

	public String getDutyItemInfo() {
		return dutyItemInfo;
	}

	public void setDutyItemInfo(String dutyItemInfo) {
		this.dutyItemInfo = dutyItemInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
