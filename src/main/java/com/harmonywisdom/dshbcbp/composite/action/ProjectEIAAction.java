package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.service.ProjectEIAService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectEIAAction extends BaseAction<ProjectEIA, ProjectEIAService> {
    @AutoService
    private ProjectEIAService projectEIAService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected ProjectEIAService getService() {
        return projectEIAService;
    }

    /**
     * highchart环评验收统计获取数据
     */
    public void getColRatio(){
        String startdate = request.getParameter("startdate");
        String lastdate = request.getParameter("lastdate");
        String enterpriseId = request.getParameter("enterpriseId");
        Map<String,Object> result = new HashMap<>();
        List<Object[]> list = projectEIAService.findByRatio(startdate,lastdate,enterpriseId);

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
    public void findByBuildProjectId(){
        String buildProjectId = request.getParameter("buildProjectId");
        ProjectEIA eia = getService().findByBuildProjectId(buildProjectId);
        write(eia);
    }

    @Override
    public void save() {
        String replyTime=request.getParameter("replyEIATime");
        String projectId=request.getParameter("projectId");

        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }

        if(replyTime!=null && projectId!=null){
                projectEIAService.updateBuildProject(DateUtil.strToDate(replyTime,"yyyy-MM-dd"),DateUtil.strToDate(replyTime,"yyyy-MM-dd"),projectId);
        }

        super.save();

        if(StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
    }
}