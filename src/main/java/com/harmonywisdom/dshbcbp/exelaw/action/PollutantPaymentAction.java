package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
import com.harmonywisdom.dshbcbp.utils.EntityUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class PollutantPaymentAction extends BaseAction<PollutantPayment, PollutantPaymentService> {
    @AutoService
    private PollutantPaymentService pollutantPaymentService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected PollutantPaymentService getService() {
        return pollutantPaymentService;
    }


    @Override
    protected QueryCondition getQueryCondition() {
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");
        String StrStatus = request.getParameter("StrStatus");

        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }

        if (StringUtils.isNotBlank(entity.getPaymentStatus())) {
            params.andParam(new QueryParam("paymentStatus", QueryOperator.EQ,entity.getPaymentStatus()));
        }
        if(StringUtils.isNotBlank(StrStatus)){
            params.andParam(new QueryParam("paymentStatus", QueryOperator.EQ,StrStatus));
        }

        if (StringUtils.isNotBlank(startYdate)) {
            startYdate+=" 00:00:00";
            params.andParam(new QueryParam("payDate", QueryOperator.GE, DateUtil.strToDate(startYdate,"yyyy-MM-dd")));
        }
        if (StringUtils.isNotBlank(lastYdate)) {
            lastYdate+=" 23:59:59";
            params.andParam(new QueryParam("payDate", QueryOperator.LE, DateUtil.strToDate(lastYdate,"yyyy-MM-dd")));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("registDate", Direction.DESC);
        return condition;
    }

    public void sewagelist(){

        Map<String,String> params = new HashMap<>();

        String firstTime = request.getParameter("firstTime");
        if(StringUtils.isNotBlank(firstTime)){
            params.put("firstTime",firstTime);
        }

        String lastTime = request.getParameter("lastTime");
        if(StringUtils.isNotBlank(lastTime)){
            params.put("lastTime",lastTime);
        }

        String paymentStatus = request.getParameter("paymentStatus");
        if(StringUtils.isNotBlank(paymentStatus)){
            params.put("paymentStatus",paymentStatus);
        }

        String unpaidStatus = request.getParameter("unpaidStatus");
        if(StringUtils.isNotBlank(unpaidStatus)){
            params.put("unpaidStatus",unpaidStatus);
        }
        QueryResult<PollutantPayment> result = null;
        result = pollutantPaymentService.findSewagelist(params,getPaging());

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
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
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
     * 计算距离（应）缴费日期
     * @param payDate （应）缴费日期
     * @return
     */
    private static String getRangeDays(Date payDate){
        Calendar now=Calendar.getInstance();
        Calendar payDateCalender=Calendar.getInstance();
        payDateCalender.setTime(payDate);

        Long days=(payDateCalender.getTimeInMillis()-now.getTimeInMillis())/ (1000 * 60 * 60 * 24);
        if (days<0){
            days=0L;
        }
        return days+"天";
    }
    @Override
    public void list() {
        QueryResult<PollutantPayment> queryResult = this.query();
        List<PollutantPayment> rows = queryResult.getRows();
        for (PollutantPayment row : rows) {
            row.setRangeDays(getRangeDays(row.getPayDate()));
        }

        this.write(queryResult);
    }

    /**
     * 排污统计
     * highchart获取柱状图数据,折线图数据
     */
    public void getSewageColumn()  {
        String name = request.getParameter("name");
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        Map<String, Object[]> result = pollutantPaymentService.findByColumnChart(name,startYdate,lastYdate);
        write(result);
    }

    /**
     * 排污统计
     * highchart获取饼状图数据
     */
    public void getSewagePie()  {
        String name = request.getParameter("name");
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        Map<String,Object> result = new HashMap<String,Object>();

        List<Object[]> list = pollutantPaymentService.findByPieChart(name,startYdate,lastYdate);
        write(EntityUtil.transHightchartsMapObj(list,false));

    }




}