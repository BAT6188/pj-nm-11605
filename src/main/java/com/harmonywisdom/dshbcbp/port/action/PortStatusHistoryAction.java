package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
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
            param.andParam(new QueryParam("enterpriseType", QueryOperator.LIKE,"%"+entity.getEnterpriseType()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getBlockLevelId())) {
            param.andParam(new QueryParam("blockLevelId", QueryOperator.EQ,entity.getBlockLevelId()));
        }
        if (StringUtils.isNotBlank(entity.getBlockId())) {
            param.andParam(new QueryParam("blockId", QueryOperator.EQ,entity.getBlockId()));
        }

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String strStatus = request.getParameter("strStatus");
        if(StringUtils.isNotBlank(startTime)){
            param.andParam(new QueryParam("time", QueryOperator.GE, MyDateUtils.getFullDate(startTime,true)));
        }
        if(StringUtils.isNotBlank(endTime)){
            param.andParam(new QueryParam("time", QueryOperator.LE,MyDateUtils.getFullDate(endTime,false)));
        }
        if(strStatus != null && !"".equals(strStatus)){
            param.andParam(new QueryParam("portStatus",QueryOperator.EQ,strStatus));
        }
        if(StringUtils.isNotBlank(entity.getRes_title())){
            param.andParam(new QueryParam("res_title",QueryOperator.LIKE,entity.getRes_title()));
        }
//        String starCreateTime = request.getParameter("start_createTime");
//        String endCreateTime = request.getParameter("end_createTime");
//        if(StringUtils.isNotBlank(starCreateTime)){
//            param.andParam(new QueryParam("release_time",QueryOperator.GE,DateUtil.strToDate(starCreateTime,"yyyy-MM-dd HH:mm")));
//        }
//        if(StringUtils.isNotBlank(endCreateTime)){
//            param.andParam(new QueryParam("release_time",QueryOperator.LE,DateUtil.strToDate(endCreateTime,"yyyy-MM-dd HH:mm")));
//        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("time", Direction.DESC);
        return condition;
    }

    /**
     * 超标同期对比查询列表
     */
    public void excessiveRatiolist(){
        Map<String,String> params = new HashMap<>();

        String lastStartTime = request.getParameter("lastStartTime");
        if(StringUtils.isNotBlank(lastStartTime)){
            params.put("lastStartTime",lastStartTime);
        }

        String lastEndTime = request.getParameter("lastEndTime");
        if(StringUtils.isNotBlank(lastEndTime)){
            params.put("lastEndTime",lastEndTime);
        }

        String firstTime = request.getParameter("firstTime");
        if(StringUtils.isNotBlank(firstTime)){
            params.put("firstTime",firstTime);
        }

        String lastTime = request.getParameter("lastTime");
        if(StringUtils.isNotBlank(lastTime)){
            params.put("lastTime",lastTime);
        }

        String strStatus = request.getParameter("strStatus");
        if(StringUtils.isNotBlank(strStatus)){
            params.put("strStatus",strStatus);
        }

        QueryResult<PortStatusHistory> result = null;
        result = portStatusHistoryService.excessiveRatiolist(params, getPaging());

        write(result);

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
        if(StringUtils.isNotBlank(entity.getAttachmentId())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentId().split(","));

        }

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

        List<Object[]> list = portStatusHistoryService.findColumnRatio(name,startSdate,lastSdate);
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

    /**
     * 企业反馈状态
     */
    public void updateSendStatus(){
        String id = request.getParameter("id");
        if(id != null && !"".equals(id)){
            this.getService().updateStatus(id);
        }
        write(true);

    }
    /**
     * 企业超标异常信息
     */
    public void excessiveInformation(){
        String enterpriseId = request.getParameter("enterpriseId");
        List<PortStatusHistory> pubInfoList = portStatusHistoryService.companyByExcessive(enterpriseId);
        write(pubInfoList);
    }


}