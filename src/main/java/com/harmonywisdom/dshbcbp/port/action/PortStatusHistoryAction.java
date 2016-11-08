package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortStatusHistoryAction extends BaseAction<PortStatusHistory, PortStatusHistoryService> {
    @AutoService
    private PortStatusHistoryService portStatusHistoryService;

    @Override
    protected PortStatusHistoryService getService() {
        return portStatusHistoryService;
    }

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            param.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            param.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,"%"+entity.getEnterpriseName()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getPortNumber())) {
            param.andParam(new QueryParam("portNumber", QueryOperator.LIKE,"%"+entity.getPortNumber()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getPortName())) {
            param.andParam(new QueryParam("portName", QueryOperator.LIKE,"%"+entity.getPortName()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseType())) {
            param.andParam(new QueryParam("enterpriseType", QueryOperator.LIKE,entity.getEnterpriseType()));
        }
        if (StringUtils.isNotBlank(entity.getBlockLevelName())) {
            param.andParam(new QueryParam("blockLevelName", QueryOperator.EQ,entity.getBlockLevelName()));
        }
        if (StringUtils.isNotBlank(entity.getBlockName())) {
            param.andParam(new QueryParam("blockName", QueryOperator.EQ,entity.getBlockName()));
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("startTime", Direction.DESC);
        return condition;
    }

    @Override
    public void save() {
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        super.save();

    }

    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }

    /**
     * highchart获取超标数据
     */
    public void getColumnHighChart() {
        Map<String,Object> result = new HashMap<String,Object>();
        String name = request.getParameter("sName");
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");


        List<Object[]> list = portStatusHistoryService.findColumnData(name,startYdate,lastYdate);
        if(list != null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];
            for(int i =0 ;i<list.size();i++){
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                ylist[i] = String.valueOf(oo[1]);
            }
            result.put("x",xlist);
            result.put("y",ylist);
        }
        write(result);

//        List<Object[]> result = new ArrayList<Object[]>();
//
//        String[] categories = new String[] {"1月","2月","3月","4月","5月","6月"};
//        Double[] serie1 = new Double[] {1d,2d,3d,3d,4d,3.55};
//
//
//        result.add(categories);
//        result.add(serie1);
//
//        write(result);
    }

    /**
     * 超标同期对比分析获取后台数据
     */
    public void getColumnRatio(){
        String name = request.getParameter("name");
        String startXdate = request.getParameter("startXdate");
        String lastXdate = request.getParameter("lastXdate");
        String startSdate = request.getParameter("startSdate");
        String lastSdate = request.getParameter("lastSdate");

        Map<String,Object> result = new HashMap<>();

        List<Object[]> list = portStatusHistoryService.findColumnRatio(name,startXdate,lastXdate,startSdate,lastSdate);
        if (list != null && list.size() > 0) {
            Object[] xlist = new Object[list.size()];
            Object[] y1list = new Object[list.size()];
            Object[] y21list = new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                y1list[i] = String.valueOf(oo[1]);
                y21list[i] = String.valueOf(oo[2]);

            }
            result.put("x", xlist);
            result.put("y1", y1list);
            result.put("y2", y21list);
        }
        write(result);

    }

    /**
     * 获取设备最新状态
     */
    public void getLastByPortId(){
        String portId = request.getParameter("portId");
        QueryResult<PortStatusHistory> result = getService().find("portId = ?1", new Paging(1),portId);
        if (result.getRows() != null && result.getRows().size() > 0) {
            PortStatusHistory lastStatus = result.getRows().get(0);
            write(lastStatus);
        }else {
            write(false);
        }

    }

}