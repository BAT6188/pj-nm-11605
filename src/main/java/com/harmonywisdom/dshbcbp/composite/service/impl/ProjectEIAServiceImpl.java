package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.dao.ProjectEIADAO;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.dshbcbp.composite.service.ProjectEIAService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("projectEIAService")
public class ProjectEIAServiceImpl extends BaseService<ProjectEIA, String> implements ProjectEIAService {
    @Autowired
    private ProjectEIADAO projectEIADAO;

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
    public List<Object[]> findByRatio(String startdate, String lastdate, String enterpriseId) {
        String whereSql = " where 1=1 ";
        whereSql += "AND  t1.IS_EIA_LICENSE = '1'";
        if(startdate != null && "".equals(startdate)){
            whereSql += "AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d') <= '"+lastdate+"'";
        }else if(lastdate !=null && "".equals(lastdate)){
            whereSql += "AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_EIA_TIME,'%Y-%m-%d') <= '"+lastdate+"'";
        }else if(enterpriseId != null && "".equals("enterpriseId")){
            whereSql += "AND a1.ENTERPRISE_ID = '"+enterpriseId+"'";
        }
        whereSql +="GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(REPLY_EIA_TIME,'%Y-%m')AS MONTH,COUNT(*) FROM HW_PROJECT_EIA t1 " +
                "LEFT JOIN HW_BUILD_PROJECT a1 ON t1.project_id=a1.id" + whereSql);

        String whereSql2 = " where 1=1 ";
        whereSql2 += "AND  t1.IS_ACC_LICENSE = '1'";
        if(startdate != null && "".equals(startdate)){
            whereSql2 += "AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d') <= '"+lastdate+"'";
        }else if(lastdate !=null && "".equals(lastdate)){
            whereSql2 += "AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d') >='"+startdate+"' AND DATE_FORMAT(t1.REPLY_ACC_TIME,'%Y-%m-%d') <= '"+lastdate+"'";
        }else if(enterpriseId != null && "".equals("enterpriseId")){
            whereSql2 += "AND a1.ENTERPRISE_ID = '"+enterpriseId+"'";
        }
        whereSql2 +="GROUP BY MONTH";

        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT DATE_FORMAT(REPLY_ACC_TIME,'%Y-%m')AS MONTH,COUNT(*) FROM HW_PROJECT_ACCEPTANCE t1 " +
                "LEFT JOIN HW_BUILD_PROJECT a1 ON t1.project_id=a1.id" + whereSql2);

        List<Object[]> temp = new ArrayList<>();

        do {
            if (list2.size() < list.size()) {
                temp = list2;
                list2 = list;
                list = temp;
            }
            for (int i = 0; i < list2.size(); i++) {
                Object[] a = list2.get(i);
                String month = a[0].toString();
                boolean flag = false;
                for (int j = 0; j < list.size(); j++) {
                    Object[] b = list.get(j);
                    String month2 = b[0].toString();
                    if (month.equals(month2)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.add(i,new Object[]{month,"0"});
                }
            }
        } while (list.size() != list2.size());

        List<Object[]> sList = new ArrayList<>();
        for (int i =0; i < list.size(); i++) {
            Object Obj[] = list.get(i);
            Object Obj2[] = list2.get(i);
            Object [] cc = {Obj[0],Obj[1],Obj2[1]};
            sList.add(cc);
        }

        return sList;
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
        projectEIADAO.executeJPQL("delete from ProjectEIA entity where entity.projectId in ?1",projectId);
    }

}