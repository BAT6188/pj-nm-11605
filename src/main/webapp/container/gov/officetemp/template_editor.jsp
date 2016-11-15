<%@ page language="java"
         import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*"
         pageEncoding="utf-8" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.harmonywisdom.dshbcbp.utils.FileUtil" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    String path = request.getSession().getServletContext().getRealPath("/");
    String fileName = path+request.getParameter("filePath");
    if(request.getAttribute("filePath") != null) {
        fileName = path + (String) request.getAttribute("filePath");
    }
    String dataFileName = path + request.getParameter("dataFilePath");
    if(request.getAttribute("dataFilePath") != null) {
        dataFileName = path + (String) request.getAttribute("dataFilePath");
    }
    String jsonData = FileUtil.readFile(dataFileName);
    Map<String,Object> map = (Map<String,Object>)JSON.parse(jsonData);
    Set<String> set = map.keySet();
    WordDocument doc = new WordDocument();
    for(String keyString : set ){
        if("tables".equals(keyString)){
            continue;
        }
        doc.getTemplate().defineDataRegion(keyString, (String)map.get(keyString));
    }

    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
    poCtrl.addCustomToolButton("保存", "Save()", 1);
    poCtrl.addCustomToolButton("定义数据区域", "ShowDefineDataRegions()", 3);

    poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
    poCtrl.setSaveFilePage(request.getContextPath()+"/doc/SaveFile.jsp");

    poCtrl.setTheme(ThemeType.Office2007);
    poCtrl.setBorderStyle(BorderStyleType.BorderThin);
    poCtrl.setWriter(doc);
    poCtrl.webOpen(fileName, OpenModeType.docNormalEdit, "zhangsan");
    poCtrl.setTagId("PageOfficeCtrl1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript">
        var rootPath = "<%=request.getContextPath()%>";
        //获取后台添加的书签名称字符串
        function getBkNames() {
            var bkNames = document.getElementById("PageOfficeCtrl1").DataRegionList.DefineNames;
            return bkNames;
        }

        //获取后台添加的书签文本字符串
        function getBkConts() {
            var bkConts = document.getElementById("PageOfficeCtrl1").DataRegionList.DefineCaptions;
            return bkConts;
        }

        //定位书签
        function locateBK(bkName) {
            var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
            drlist.GetDataRegionByName(bkName).Locate();
            window.focus();

        }

        //添加书签
        function addBookMark(param) {
            var tmpArr = param.split("=");
            var bkName = tmpArr[0];
            var content = tmpArr[1];
            var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
            drlist.Refresh();
            try {
                document.getElementById("PageOfficeCtrl1").Document.Application.Selection.Collapse(0);
                drlist.Add(bkName, content);
                return "true";
            } catch (e) {
                return "false";
            }
        }
        //删除书签
        function delBookMark(bkName) {
            var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
            try {
                drlist.Delete(bkName);
                return "true";
            } catch (e) {
                return "false";
            }
        }

        //遍历当前Word中已存在的书签名称
        function checkBkNames() {
            var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
            drlist.Refresh();
            var bkName = "";
            var bkNames = "";
            for (var i = 0; i < drlist.Count; i++) {
                bkName = drlist.Item(i).Name;
                bkNames += bkName.substr(3) + ",";
            }
            return bkNames.substr(0, bkNames.length - 1);
        }

        //遍历当前Word中已存在的书签文本
        function checkBkConts() {
            var drlist = document.getElementById("PageOfficeCtrl1").DataRegionList;
            drlist.Refresh();
            var bkCont = "";
            var bkConts = "";
            for (var i = 0; i < drlist.Count; i++) {
                bkCont = drlist.Item(i).Value;
                if(bkCont.length>10){
                    bkCont = bkCont.substr(0,3);
                }
                bkConts += bkCont + ",";
            }
            return bkConts.substr(0, bkConts.length - 1);
        }
    </script>

    <script type="text/javascript">
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();
        }
        function ShowDefineDataRegions() {
            document.getElementById("PageOfficeCtrl1").ShowHtmlModelessDialog(rootPath+"/doc/DataRegionDlg.htm", "parameter=xx", "left=300px;top=390px;width=850px;height=410px;frame:no;");

        }

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
