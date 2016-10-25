package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }

        if (StringUtils.isNotBlank(entity.getPaymentStatus())) {
            params.andParam(new QueryParam("paymentStatus", QueryOperator.EQ,entity.getPaymentStatus()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
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
    public void getSewageColumn() throws ParseException {
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstTime = sdf.parse(startYdate);
        Date lastTime = sdf.parse(lastYdate);
        Map<String, Object> result = new HashMap<String, Object>();


        List<Object[]> list = pollutantPaymentService.findByColumnChart(firstTime,lastTime);
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
     * 排污统计
     * highchart获取饼状图数据
     */
    public void getSewagePie() throws ParseException {
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstTime = sdf.parse(startYdate);
        Date endTime = sdf.parse(lastYdate);
        Map<String,Object> result = new HashMap<String,Object>();

        List<Object[]> list = pollutantPaymentService.findByPieChart(firstTime,endTime);
        if(list !=null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];
            for(int i=0;i<list.size();i++){
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                ylist[i] = String.valueOf(oo[1]);
            }
            result.put("x",xlist);
            result.put("y",ylist);
        }
        write(result);

    }




}