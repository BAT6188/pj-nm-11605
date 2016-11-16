<%@ page language="java"
         import="com.alibaba.fastjson.JSON,com.harmonywisdom.dshbcbp.utils.FileUtil,com.harmonywisdom.dshbcbp.utils.OfficeUtil"
         pageEncoding="utf-8" %>
<%@ page import="com.harmonywisdom.framework.service.BaseService" %>
<%@ page import="com.harmonywisdom.framework.service.SpringUtil" %>
<%@ page import="com.zhuozhengsoft.pageoffice.BorderStyleType" %>
<%@ page import="com.zhuozhengsoft.pageoffice.OpenModeType" %>
<%@ page import="com.zhuozhengsoft.pageoffice.PageOfficeCtrl" %>
<%@ page import="com.zhuozhengsoft.pageoffice.ThemeType" %>
<%@ page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    String beanName = request.getParameter("beanName");
    String bussinessId = request.getParameter("bussinessId");
    BaseService service = SpringUtil.getBean(beanName);
    Object object = service.findById(bussinessId);
    if(object == null){
        return;
    }
    String path = request.getSession().getServletContext().getRealPath("/");
    String fileName = path+request.getParameter("filePath");
    if(request.getAttribute("filePath") != null){
        fileName = path+(String)request.getAttribute("filePath");
    }
    String dataFileName = path + request.getParameter("dataFilePath");
    if(request.getAttribute("dataFilePath") != null) {
        dataFileName = path + (String) request.getAttribute("dataFilePath");
    }
    String jsonData = FileUtil.readFile(dataFileName);
    WordDocument doc = new WordDocument();
    Map<String,Object> map = (Map<String,Object>) JSON.parse(jsonData);
    OfficeUtil.fillSingleDataRegion(doc,object,map);
    OfficeUtil.fillTableDataRegion(doc,object,map);
    
    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
    poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
    poCtrl.setTheme(ThemeType.Office2007);
    poCtrl.setBorderStyle(BorderStyleType.BorderThin);

    //隐藏菜单栏
    poCtrl.setMenubar(true);
    poCtrl.setOfficeToolbars(false);
    poCtrl.setCustomToolbar(false);
    poCtrl.setTitlebar(false);

    poCtrl.setWriter(doc);
    poCtrl.webOpen(fileName, OpenModeType.docReadOnly, "zhangsan");
    poCtrl.setTagId("PageOfficeCtrl1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript">
        var rootPath = "<%=request.getContextPath()%>";

        function initPage(){
            document.getElementById("pageOfficeDiv").style.height = document.body.clientHeight;
//            document.getElementById("PageOfficeCtrl1").height = document.body.clientHeight;
        }

    </script>


</head>
<body onload="initPage()">
<form action="">
    <div id="pageOfficeDiv" style="width:100%; height: 800px;">
        <po:PageOfficeCtrl id="PageOfficeCtrl1" >
        </po:PageOfficeCtrl>
    </div>
</form>
</body>
</html>
