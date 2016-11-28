package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.dao.BuildProjectDAO;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("buildProjectService")
public class BuildProjectServiceImpl extends BaseService<BuildProject, String> implements BuildProjectService {
    @Autowired
    private BuildProjectDAO buildProjectDAO;

    @Override
    protected BaseDAO<BuildProject, String> getDAO() {
        return buildProjectDAO;
    }

    @Override
    public List<BuildProject> getAll() {
        String sql="SELECT * FROM HW_BUILD_PROJECT a INNER JOIN  HW_PROJECT_EIA t ON a.id=t.PROJECT_ID";
        List<BuildProject> priList=getDAO().queryNativeSQL(sql);
        return priList;
    }

    @Override
    public List<BuildProject> findByName(String name) {
        List<BuildProject> list=getDAO().queryJPQL("from BuildProject where  name LIKE '%" + name + "%'");
        return list;
    }

    @Override
    public List<ProjectEIA> findEIAById(String projectId) {
        List<ProjectEIA> list=getDAO().queryJPQL("from ProjectEIA where projectId=? ",projectId);
        return list;
    }

    @Override
    public List<ProjectAcceptance> findAcceptanceById(String projectId) {
        List<ProjectAcceptance> list=getDAO().queryJPQL("from ProjectAcceptance where projectId=? ",projectId);
        return list;
    }

    /**
     * 建项环评验收查询建设项目环评及验收信息表
     * @param params
     * @param paging
     * @return
     */
    @Override
    public QueryResult<BuildProject> findBulidProList(Map<String, String> params, Paging paging) {

        QueryResult<BuildProject> result = new QueryResult<>();
        List<BuildProject> rows = new ArrayList<>();

        //分页条件
        int startIndex = paging.getStartIndex();
        int endIndex = paging.getStartIndex() + paging.getPageSize();

        StringBuilder whereSql = new StringBuilder(" where 1=1 ");

        if(StringUtils.isNotBlank(params.get("firstTime")) || StringUtils.isNotBlank(params.get("lastTime"))){
            whereSql.append(" and ( t.REPLY_ACC_TIME > '").append(params.get("firstTime")).append("' and t.REPLY_ACC_TIME < '").append(params.get("lastTime")+"'");
        }
        if(StringUtils.isNotBlank(params.get("isAcceptance"))){
            whereSql.append(" and IS_ACCEPTANCE = '").append(params.get("isAcceptance")+"')");
        }
        if(StringUtils.isNotBlank(params.get("firstTime")) || StringUtils.isNotBlank(params.get("lastTime"))){
            whereSql.append(" OR ( t.REPLY_EIA_TIME > '").append(params.get("firstTime")).append("' and t.REPLY_EIA_TIME < '").append(params.get("lastTime")+"'");
        }
        if(StringUtils.isNotBlank(params.get("isEIA"))){
            whereSql.append(" and IS_EIA = '").append(params.get("isEIA")+"')");

        }
        String countSql = "select count(*) from HW_BUILD_PROJECT t" +whereSql.toString();
        String querySql = "select t.* from HW_BUILD_PROJECT t " +whereSql.toString()+"limit " + startIndex+","+endIndex;

        long total = buildProjectDAO.getCount(countSql);

        List<Object[]> list = buildProjectDAO.queryNativeSQL(querySql);

        if(list != null && list.size() > 0){
            BuildProject buildPro = null;
            for(Object[] obj : list){
                buildPro = new BuildProject();
                buildPro.setId(obj[0]==null ? "" :String.valueOf(obj[0]));
                buildPro.setName(obj[20]==null ? "" :String.valueOf(obj[20]));
                buildPro.setArea(obj[3]==null ? "" :String.valueOf(obj[3]));
                Date date = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    date = sdf.parse(obj[24]==null ? "" :String.valueOf(obj[24]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                buildPro.setReplyTime(date);
                buildPro.setBuildNature(obj[5]==null ? "" :String.valueOf(obj[5]));
                buildPro.setEnvManagType(obj[15]==null ? "" :String.valueOf(obj[15]));
                buildPro.setIsAcceptance(obj[18]==null ? "" :String.valueOf(obj[18]));
                buildPro.setIsEIA(obj[19]==null ? "" :String.valueOf(obj[19]));

                rows.add(buildPro);
            }
        }
        result.setRows(rows);
        result.setTotal(total);
        return result;
    }
}