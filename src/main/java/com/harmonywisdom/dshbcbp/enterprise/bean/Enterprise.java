package com.harmonywisdom.dshbcbp.enterprise.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业基本信息
 */
@Entity
@Table(name = "HW_DSHBCBP_ENTERPRISE")
public class Enterprise implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 单位名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 网格级别
     */
    @Column(name = "block_level_id")
    private String blockLevelId;
    @Column(name = "block_level_name")
    private String blockLevelName;

    /**
     * 所属网格
     */
    @Column(name = "block_id", length = 32)
    private String blockId;
    @Column(name = "block_name")
    private String blockName;

    /**
     * 企业运行状态
     * 1:运行中
     * 2:已停产
     */
    @Column(name = "status",length = 1)
    private String status;

    /**
     * 污染源状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "pollutant_status",length = 1)
    private String pollutantStatus;

    /**
     *单位地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 污染源代码
     */
    @Column(name = "pollutant_code")
    private String pollutantCode;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     *组织机构代码
     */
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 法定代表人
     */
    @Column(name = "artificial_person")
    private String artificialPerson;

    /**
     * 法定代表职务
     */
    @Column(name = "ap_position")
    private String apPosition;

    /**
     * 法定代表人电话
     */
    @Column(name = "ap_phone")
    private String apPhone;

    /**
     * 企业环保负责人
     */
    @Column(name = "env_principal")
    private String envPrincipal;

    /**
     *企业环保负责人职务
     */
    @Column(name = "ep_position")
    private String epPosition;

    /**
     *企业环保负责人电话
     */
    @Column(name = "ep_phone")
    private String epPhone;

    /**
     * 行业主管部门
     */
    @Column(name = "industry_department")
    private String industryDepartment;

    /**
     * 主管部门负责人
     */
    @Column(name = "industry_department_person")
    private String industryDepartmentPerson;

    /**
     * 主管部门负责人联系电话
     */
    @Column(name = "industry_department_person_phone")
    private String industryDepartmentPersonPhone;

    /**
     * 网格环保负责人
     */
    @Column(name = "block_person")
    private String blockPerson;

    /**
     * 网格环保负责人联系电话
     */
    @Column(name = "block_person_phone")
    private String blockPersonPhone;

    /**
     * 网格公安负责人
     */
    @Column(name = "block_police_person")
    private String blockPolicePerson;

    /**
     * 网格公安负责人联系电话
     */
    @Column(name = "block_police_person_phone")
    private String blockPolicePersonPhone;

    /**
     * 污染源类型（多选）
     * 废水
     * 废气
     * 污水处理厂
     * 重金属
     * 畜禽养殖
     * 固废
     * 危险废物
     * 省级实验室
     * 二级以上医院
     * 其他
     */
    @Column(name = "pollutant_type")
    private String pollutantType;

    /**
     *污染源管理级别
     * 国控
     * 省（区）控
     * 市控
     * 其他
      */
    @Column(name = "pollutant_level")
    private String pollutantLevel;

    /**
     * 排污单位监管类型
     * 重点排污单位
     * 一般排污单位
     */
    @Column(name = "supervise_type")
    private String superviseType;

    /**
     * 是否特殊监管对象（单选）
     * 0:否
     * 1:是
     */
    @Column(name = "is_special",length = 1)
    private String isSpecial;

    /**
     * 登记注册类型
     * 合作经营企业（港或澳、台资）
     * 港、澳、台商独资经营企业
     * 港、澳、台商独资股份有限公司
     * 外商投资企业
     * 中外合资经营企业
     * 中外合作经营企业
     * 外资企业
     * 外商投资股份有限公司
     * 个体经营
     * 内资企业
     * 国有企业
     * 集体企业
     * 股份合作企业
     * 联营企业
     * 国有联营企业
     * 集体联营企业
     * 国有与集体联营企业
     * 其他联营企业
     * 有限责任工司
     * 国有独资公司
     * 其他有限责任公司
     * 股份有限公司
     * 私营企业
     * 私营独资企业
     * 私营合伙企业
     */
    @Column(name = "regist_type")
    private String registType;

    /**
     * 登记注册时间
     */
    @Column(name = "regist_time")
    private Date registTime;

    /**
     * 隶属关系
     * 中央
     * 省
     * 市、地区
     * 县
     * 街道、镇、乡
     * 街道
     * 镇
     * 乡
     * 居民、村民委员会
     * 居民委员会
     * 村民委员会
     * 其他
      */
    @Column(name = "affiliation")
    private String affiliation;

    /**
     * 排污单位规模
     * 县以上工业企业
     * 县以上非工业企业
     * 事业单位
     * 乡镇街道工业企业
     * 乡镇街道非工业企业
     * 部队
     * 其他
     */
    @Column(name = "scale")
    private String scale;

    /**
     * 行业类别（弹出树选择）
     */
    @Column(name = "industry_type")
    private String industryType;

    /**
     * 行政区
     */
    @Column(name = "area")
    private String area;

    /**
     * 所在工业园区
     */
    @Column(name = "industrial_park")
    private String industrialPark;

    /**
     * 所属流域
     */
    @Column(name = "valley")
    private String valley;

    /**
     * 开业时间
     */
    @Column(name = "open_date")
    private Date openDate;

    /**
     * 最近扩建时间
     */
    @Column(name = "extension_date")
    private Date extensionDate;

    /**
     * 单位介绍
     */
    //@Lob
    @Column(name = "org_info",columnDefinition = "longtext")
    private String orgInfo;

    /**
     * 周边环境敏感点
     */
    //@Lob
    @Column(name = "env_desc",columnDefinition = "longtext")
    private String envDesc;

    /**
     * 系统创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除 1:已删除; 0：未删除
     */
    @Column(name = "is_del",length = 1)
    private String isDel;

    /**
     * 删除操作人code
     */
    @Column(name = "deler_code")
    private String delerCode;

    /**
     * 删除操作人姓名
     */
    @Column(name = "deler_name")
    private String delerName;

    /**
     * 删除时间
     */
    @Column(name = "del_time")
    private Date delTime;

    /**
     * 删除意见
     */
    @Column(name = "del_opinion")
    private String delOpinion;

    /**
     * 平面图
     */
    @Column(name = "plane_map")
    private String planeMap;

    /**
     * 是否含有油烟排口 0:不含;1：包含
     */
    @Column(name = "have_fumes_port")
    private String haveFumesPort;

    /**
     * 是否在线监控 0:否：1：是
     */
    @Column(name = "IS_ONLINE_MONITORING")
    private String isOnlineMonitoring;

    /**
     * 用户登录名
     */
    @Column(name="USERNAME",length=100)
    private String userName;

    /**
     * 用户登录密码
     */
    @Column(name="PASSWORD",length=100)
    private String password;
    /**
     * 用户登录确认密码
     */
    @Column(name="SEC_PASSWORD",length=100)
    private String secpassword;


    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",nullable = false,columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;

    /**
     * 附件
     */
    @Transient
    private String attachmentId;

    /**
     * 导出word时使用
     */
    @Transient
    private String enterpriseList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecpassword() {
        return secpassword;
    }

    public void setSecpassword(String secpassword) {
        this.secpassword = secpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(String blockLevelName) {
        this.blockLevelName = blockLevelName;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPollutantStatus() {
        return pollutantStatus;
    }

    public void setPollutantStatus(String pollutantStatus) {
        this.pollutantStatus = pollutantStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPollutantCode() {
        return pollutantCode;
    }

    public void setPollutantCode(String pollutantCode) {
        this.pollutantCode = pollutantCode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getArtificialPerson() {
        return artificialPerson;
    }

    public void setArtificialPerson(String artificialPerson) {
        this.artificialPerson = artificialPerson;
    }

    public String getApPosition() {
        return apPosition;
    }

    public void setApPosition(String apPosition) {
        this.apPosition = apPosition;
    }

    public String getApPhone() {
        return apPhone;
    }

    public void setApPhone(String apPhone) {
        this.apPhone = apPhone;
    }

    public String getEnvPrincipal() {
        return envPrincipal;
    }

    public void setEnvPrincipal(String envPrincipal) {
        this.envPrincipal = envPrincipal;
    }

    public String getEpPosition() {
        return epPosition;
    }

    public void setEpPosition(String epPosition) {
        this.epPosition = epPosition;
    }

    public String getEpPhone() {
        return epPhone;
    }

    public void setEpPhone(String epPhone) {
        this.epPhone = epPhone;
    }

    public String getPollutantType() {
        return pollutantType;
    }

    public void setPollutantType(String pollutantType) {
        this.pollutantType = pollutantType;
    }

    public String getPollutantLevel() {
        return pollutantLevel;
    }

    public void setPollutantLevel(String pollutantLevel) {
        this.pollutantLevel = pollutantLevel;
    }

    public String getSuperviseType() {
        return superviseType;
    }

    public void setSuperviseType(String superviseType) {
        this.superviseType = superviseType;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getRegistType() {
        return registType;
    }

    public void setRegistType(String registType) {
        this.registType = registType;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustrialPark() {
        return industrialPark;
    }

    public void setIndustrialPark(String industrialPark) {
        this.industrialPark = industrialPark;
    }

    public String getValley() {
        return valley;
    }

    public void setValley(String valley) {
        this.valley = valley;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getExtensionDate() {
        return extensionDate;
    }

    public void setExtensionDate(Date extensionDate) {
        this.extensionDate = extensionDate;
    }

    public String getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(String orgInfo) {
        this.orgInfo = orgInfo;
    }

    public String getEnvDesc() {
        return envDesc;
    }

    public void setEnvDesc(String envDesc) {
        this.envDesc = envDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getDelerCode() {
        return delerCode;
    }

    public void setDelerCode(String delerCode) {
        this.delerCode = delerCode;
    }

    public String getDelerName() {
        return delerName;
    }

    public void setDelerName(String delerName) {
        this.delerName = delerName;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelOpinion() {
        return delOpinion;
    }

    public void setDelOpinion(String delOpinion) {
        this.delOpinion = delOpinion;
    }

    public String getPlaneMap() {
        return planeMap;
    }

    public void setPlaneMap(String planeMap) {
        this.planeMap = planeMap;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getHaveFumesPort() {
        return haveFumesPort;
    }

    public void setHaveFumesPort(String haveFumesPort) {
        this.haveFumesPort = haveFumesPort;
    }

    public String getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(String enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public String getIsOnlineMonitoring() {
        return isOnlineMonitoring;
    }

    public void setIsOnlineMonitoring(String isOnlineMonitoring) {
        this.isOnlineMonitoring = isOnlineMonitoring;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }

    public String getIndustryDepartment() {
        return industryDepartment;
    }

    public void setIndustryDepartment(String industryDepartment) {
        this.industryDepartment = industryDepartment;
    }

    public String getIndustryDepartmentPerson() {
        return industryDepartmentPerson;
    }

    public void setIndustryDepartmentPerson(String industryDepartmentPerson) {
        this.industryDepartmentPerson = industryDepartmentPerson;
    }

    public String getIndustryDepartmentPersonPhone() {
        return industryDepartmentPersonPhone;
    }

    public void setIndustryDepartmentPersonPhone(String industryDepartmentPersonPhone) {
        this.industryDepartmentPersonPhone = industryDepartmentPersonPhone;
    }

    public String getBlockPerson() {
        return blockPerson;
    }

    public void setBlockPerson(String blockPerson) {
        this.blockPerson = blockPerson;
    }

    public String getBlockPersonPhone() {
        return blockPersonPhone;
    }

    public void setBlockPersonPhone(String blockPersonPhone) {
        this.blockPersonPhone = blockPersonPhone;
    }

    public String getBlockPolicePerson() {
        return blockPolicePerson;
    }

    public void setBlockPolicePerson(String blockPolicePerson) {
        this.blockPolicePerson = blockPolicePerson;
    }

    public String getBlockPolicePersonPhone() {
        return blockPolicePersonPhone;
    }

    public void setBlockPolicePersonPhone(String blockPolicePersonPhone) {
        this.blockPolicePersonPhone = blockPolicePersonPhone;
    }
}