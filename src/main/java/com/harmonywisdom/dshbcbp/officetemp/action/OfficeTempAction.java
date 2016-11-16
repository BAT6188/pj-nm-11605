package com.harmonywisdom.dshbcbp.officetemp.action;

import com.harmonywisdom.dshbcbp.attachment.bean.Attachment;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.officetemp.bean.OfficeTemp;
import com.harmonywisdom.dshbcbp.officetemp.service.OfficeTempService;
import com.harmonywisdom.dshbcbp.utils.FileUtil;
import com.harmonywisdom.dshbcbp.utils.OfficeUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import com.hazelcast.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by sunzuoquan on 15-7-20.
 */
public class OfficeTempAction extends BaseAction<OfficeTemp, OfficeTempService> {
    @AutoService
    private OfficeTempService officeTempService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected OfficeTempService getService() {
        return officeTempService;
    }



    @Override
    public void save() {
        //获取删除的附件IDS
        String removeId = request.getParameter("removeId");
        if(!StringUtil.isNullOrEmpty(removeId)){
            String[] removeIds = removeId.split(",");
            //删除附件
            attachmentService.removeByIds(removeIds);
        }
        entity.setFilePath("doc"+ File.separator);
        String id = officeTempService.saveOrUpdate(entity);

        if (!"".equals(entity.getAttachmentId()) && entity.getAttachmentId() != null){
            String[] attachmentIds = entity.getAttachmentId().split(",");
            attachmentService.updateBusinessId(entity.getId(),attachmentIds);
        }
        List<Attachment> list = attachmentService.getByBusinessId(entity.getId());
        boolean isHaveDoc = false;
        boolean isHaveData = false;
        for(Attachment attachment : list) {
            // 获取所有附件，将doc的附件放入doc目录
            if("doc".equals(attachment.getExt().toLowerCase())){
                entity.setFileName(entity.getId()+".doc");
                FileUtil.copyFileNotOverwrite(attachment.getPath(), request.getSession().getServletContext().getRealPath("/")+entity.getFilePath() + entity.getFileName());
                isHaveDoc = true;
            }
            // 将json附件
            if("json".equals(attachment.getExt().toLowerCase())){
                entity.setDataFileName( entity.getId() + ".json");
                FileUtil.copyFileNotOverwrite(attachment.getPath(), request.getSession().getServletContext().getRealPath("/") + entity.getFilePath() + entity.getDataFileName());
                isHaveData = true;
            }
        }
        // 根据附件情况更新模板数据
        if(!isHaveDoc){
            entity.setFileName("");
        }
        if(!isHaveData){
            entity.setDataFileName("");
        }
        officeTempService.update(entity);

        write(true, "id", id);
    }
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(!StringUtil.isNullOrEmpty(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }

    /**
     * 编辑模板
     */
    public void editTemplate(){
        try {
            String templateId = entity.getId();
            OfficeTemp template = officeTempService.findById(templateId);
            if(StringUtils.isBlank(template.getFileName())){
                request.setAttribute("message","没有找到模板！");
                response.sendRedirect(request.getContextPath()+"/gov/officetemp/error_message.jsp");
            }
            if( StringUtils.isBlank(template.getDataFileName())){
                request.setAttribute("message","没有找到模板数据！");
                response.sendRedirect(request.getContextPath()+"/gov/officetemp/error_message.jsp");
            }
//            request.setAttribute("filePath",template.getFilePath() + template.getFileName());
//            request.setAttribute("dataFilePath", template.getFilePath() + template.getDataFileName());
//            RequestDispatcher rd = request.getRequestDispatcher("/gov/officetemp/template_editor.jsp");
//            rd.forward(request, response);
            response.sendRedirect(request.getContextPath()+"/gov/officetemp/template_editor.jsp?filePath="+template.getFilePath() + template.getFileName()+"&dataFilePath="+template.getFilePath() + template.getDataFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 编辑模板
     */
    public void showTemplate(){
        try {
            String templateId = entity.getId();
            OfficeTemp template = officeTempService.findById(templateId);
            if(StringUtils.isBlank(template.getFileName())){
                request.setAttribute("message","没有找到模板！");
                response.sendRedirect(request.getContextPath()+"/container/gov/officetemp/error_message.jsp");
            }
            if( StringUtils.isBlank(template.getDataFileName())){
                request.setAttribute("message","没有找到模板数据！");
                response.sendRedirect(request.getContextPath()+"/container/gov/officetemp/error_message.jsp");
            }
//            request.setAttribute("filePath",template.getFilePath() + template.getFileName());
//            request.setAttribute("dataFilePath", template.getFilePath() + template.getDataFileName());
//            RequestDispatcher rd = request.getRequestDispatcher("/gov/officetemp/template_editor.jsp");
//            rd.forward(request, response);
            String url = request.getContextPath() + "/container/gov/officetemp/template_show.jsp" +
                    "?filePath=" + template.getFilePath() + template.getFileName() +
                    "&dataFilePath=" + template.getFilePath() + template.getDataFileName() +
                    "&beanName=" + request.getParameter("beanName") +
                    "&bussinessId=" + request.getParameter("bussinessId");
            String pageOfficeLinke = OfficeUtil.getPageOfficeLink(request, url);
            response.sendRedirect(pageOfficeLinke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
