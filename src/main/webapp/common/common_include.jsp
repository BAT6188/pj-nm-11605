<%@ page import="com.harmonywisdom.apportal.common.configuration.ConfigureManager" %>
<%@ page import="com.harmonywisdom.apportal.sdk.person.IPerson" %>
<%@ page import="com.harmonywisdom.dshbcbp.utils.ApportalUtil" %>
<%@ page import="com.harmonywisdom.apportal.sdk.org.IOrg" %>
<%@ page import="com.harmonywisdom.apportal.sdk.org.OrgServiceUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%
    IPerson person = ApportalUtil.getPerson(request);
    String userID = "";
    String userName = "";
    String orgId="";
    String orgCode="";
    if (person != null) {
        userID = person.getUserId();
        userName = person.getUserName();
        orgId=person.getOrgId();

        IOrg org = OrgServiceUtil.getOrgByOrgId(orgId);
        orgCode = org.getOrgCode();

    }
    ConfigureManager manager = ConfigureManager.getInstance();
    String apportalRootPath = "";
    if (manager != null && manager.getSsoConfig() != null) {
        apportalRootPath = manager.getSsoConfig().getSsoGateWaySite();
    }


%>
<script type="text/javascript" >
    var rootPath = '<%=request.getContextPath()%>';
    var apportalRootPath = '<%=apportalRootPath%>';
    var SToken = '${param.SToken}';
    var userId = '<%=userID%>';
    var userName = '<%=userName%>';

    var orgId='<%=orgId%>';
    var orgCode='<%=orgCode%>';

</script>

<!--[if lt IE 9]>
<meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<link rel='icon' href='<%=request.getContextPath()%>/common/images/company.ico ' type='image/x-ico' />
<link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/common/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/bootstrap-table.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/common/scripts/fine-uploader-5.11.8/fine-uploader-gallery.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/common/scripts/fine-uploader-5.11.8/fine-uploader-new.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/demo/easyform/js/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/demo/easyform/js/easyform/easyform.css">
<link href="<%=request.getContextPath()%>/common/css/pageStyle.css" rel="stylesheet">

<%--<script src="<%=request.getContextPath()%>/common/scripts/jquery1.12.4/jquery.js"></script>--%>
<script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/bootstrap-table.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.js"></script>

<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/x-editable/bootstrap-editable.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/extensions/editable/bootstrap-table-editable.js"></script>

<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/table-export/tableExport.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/extensions/export/bootstrap-table-export.js"></script>

<script src="<%=request.getContextPath()%>/common/scripts/fine-uploader-5.11.8/fine-uploader.core.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/fine-uploader-5.11.8/fine-uploader.jquery.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/fine-uploader-5.11.8/fine-uploader.js"></script>

<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="<%=request.getContextPath()%>/common/scripts/easyform/easyform.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/common/scripts/userDefined.js"></script>
<script src="${pageContext.request.contextPath}/common/scripts/highcharts/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/common/scripts/highcharts/modules/exporting.js"></script>
<script src="${pageContext.request.contextPath}/demo/easyform/js/jquery-ui.js"></script>


