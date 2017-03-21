package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.dao.ProjectEIADAO;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.dshbcbp.composite.service.ProjectEIAService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("projectEIAService")
public class ProjectEIAServiceImpl extends BaseService<ProjectEIA, String> implements ProjectEIAService {
    @Autowired
    private ProjectEIADAO projectEIADAO;
    @AutoService
    private AttachmentService attachmentService;
    @Autowired
    private BuildProjectService buildProjectService;

    @Override
    protected BaseDAO<ProjectEIA, String> getDAO() {
        return projectEIADAO;
    }

    /**
     * highchart
     * 环评验收获取后台数据
     * @param startdate
     * @param lastdate
     * @param enterpriseId
     * @return
     */
    @Override
    public Map<Object,String[]> findByRatio(String startdate, String lastdate, String enterpriseId) {
        startdate = startdate.length()==10?startdate+" 00:00:00":startdate;
        lastdate = lastdate.length()==10?lastdate+" 23:59:59":lastdate;
        String whereSql = " where 1=1 ";
        if(startdate != null && !"".equals(startdate)){
            whereSql += "AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d %h:%i:%s') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d %h:%i:%s') <= '"+lastdate+"'";
        }else if(lastdate !=null && !"".equals(lastdate)){
            whereSql += "AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d %h:%i:%s') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d %h:%i:%s') <= '"+lastdate+"'";
        }else if(enterpriseId != null && "".equals("enterpriseId")){
            whereSql += "AND a1.ENTERPRISE_ID = '"+enterpriseId+"'";
        }
        whereSql += "AND  t1.IS_EIA_LICENSE = '1'";
        whereSql +="GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m')AS MONTH,COUNT(*) FROM HW_PROJECT_EIA t1 " +
                "LEFT JOIN HW_BUILD_PROJECT a1 ON t1.project_id=a1.id" + whereSql);

        String whereSql2 = " where 1=1 ";
        whereSql2 += "AND  t1.IS_ACC_LICENSE = '1'";
        if(startdate != null && !"".equals(startdate)){
            whereSql2 += "AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d %h:%i:%s') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d %h:%i:%s') <= '"+lastdate+"'";
        }else if(lastdate !=null && !"".equals(lastdate)){
            whereSql2 += "AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d %h:%i:%s') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d %h:%i:%s') <= '"+lastdate+"'";
        }else if(enterpriseId != null && "".equals("enterpriseId")){
            whereSql2 += "AND a1.ENTERPRISE_ID = '"+enterpriseId+"'";
        }
        whereSql2 +="GROUP BY MONTH";

        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m')AS MONTH,COUNT(*) FROM HW_PROJECT_ACCEPTANCE t1 " +
                "LEFT JOIN HW_BUILD_PROJECT a1 ON t1.project_id=a1.id" + whereSql2);

        List<Object[]> temp = new ArrayList<>();
        int mNumber = MyDateUtils.getMonthNumber(startdate,lastdate);
        String sDateStr = startdate;
        Map<Object,String[]> returnMap = new HashMap<>();
        String[] x = new String[mNumber],y1= new String[mNumber],y2 = new String[mNumber];
        for(int i=0;i<mNumber;i++){
            String queryedTimeStr = sDateStr.substring(0,7);
            x[i] = queryedTimeStr;
            y1[i] = checkValue(list,queryedTimeStr);
            y2[i] = checkValue(list2,queryedTimeStr);
            sDateStr = MyDateUtils.getNextMonth(sDateStr,"yyyy-MM-dd","yyyy-MM-dd");
        }
        returnMap.put("x",x);
        returnMap.put("y1",y1);
        returnMap.put("y2",y2);
        return returnMap;
    }

    public String checkValue(List<Object[]> list,String m){
        String rValue = "0";
        for(int i = 0; i < list.size(); i++){
            Object[] a = list.get(i);
            String month = a[0].toString();
            if(month.equals(m)){
                rValue = a[1].toString();
                break;
            }
        }
        return rValue;
    }
    @Override
    public ProjectEIA findByBuildProjectId(String buildProjectId) {
        BuildProject project = buildProjectService.findById(buildProjectId);
        List<ProjectEIA> eias=getDAO().find("projectId=?1", buildProjectId);
        if (eias != null && eias.size() > 0) {
            ProjectEIA eia = eias.get(0);
            eia.setBuildProject(project);
            return eia;
        }
        return null;
    }

    @Override
    public void deleteProjectEIABuildProjectId(String projectId) {
        ProjectEIA projectEIA = new ProjectEIA();
        projectEIA.setProjectId(projectId);
        List<ProjectEIA> list = projectEIADAO.findBySample(projectEIA);
        if (list.size()>0){
            for (ProjectEIA p:list){
                attachmentService.removeByBusinessIds(p.getId());
                projectEIADAO.remove(p.getId());
            }
        }
        projectEIADAO.executeJPQL("delete from ProjectEIA entity where entity.projectId in ?1",projectId);
    }

    @Override
    public void updateBuildProject(Date replyTime,Date replyEIATime,String projectId) {
        buildProjectService.executeJPQL("update BuildProject set replyTime=?,replyEIATime=?,isEIA=1 where id=?",replyTime,replyEIATime,projectId);
    }

}