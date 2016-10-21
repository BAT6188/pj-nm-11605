<%@ page language="java" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>HM_GIS</title>
    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        /*<!--解决样式冲突问题-->*/
        .titlebar>.hide{
            display: block !important;
        }
    </style>
    <script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>
    <script type="text/javascript" src="script/minmap/init.js" ></script>
	<script type="text/javascript">
		init('<%=request.getContextPath()%>/common/gis/script/minmap','<%=basePath%>/common/gis/3.11');
	</script>
    <script type="text/javascript" src="script/hwmapCommon.js" ></script>
</head>
<body class="tundra" onload="HwmapCommon.initmap()" style="margin: 0;">
	<div id="mapContainer"></div>
</body>
</html>